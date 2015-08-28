
import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Bloguser;
import customTools.DBUtil;

/**
 * Servlet implementation class ServletLogin
 */
@WebServlet("/LoginForm")
public class ServletLoginForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static List<Bloguser> userList;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletLoginForm() {
		super();
		// TODO Auto-generated constructor stub
	}

	String error = "";

	@Override
	public void init() {
		error = "";
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("logout") == null) {
			getServletContext().getRequestDispatcher("/Login.jsp").forward(
					request, response);
		} else if (request.getParameter("logout").equalsIgnoreCase("yes")) {
			HttpSession session = request.getSession();
			session.invalidate();
			getServletContext().getRequestDispatcher("/Login.jsp").forward(
					request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		System.out.println("dopost");

		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		em.getTransaction().begin();
		userList = em.createQuery("SELECT u FROM Bloguser u").getResultList();
		em.getTransaction().commit();
		em.close();
		if (checkLogin(request)) {
			System.out.println("login success");

			getServletContext().getRequestDispatcher("/UserAccount.jsp")
					.forward(request, response);
		} else {
			System.out.println("login failed");
			error = "Invalid Credential";
			request.setAttribute("error", error);
			getServletContext().getRequestDispatcher("/Login.jsp").forward(
					request, response);

		}

	}

	private boolean checkLogin(HttpServletRequest request) {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		System.out.println("email = " + email);
		for (Bloguser b : userList) {
			System.out.println(b.getEmail());
			System.out.println(b.getUserPassword());
			if (b.getEmail().equalsIgnoreCase(email)
					&& b.getUserPassword().equalsIgnoreCase(password)) {
				HttpSession session = request.getSession();
				session.setAttribute("bloguser", b);
				request.setAttribute("user_name", b.getName());
				return true;
			}
		}
		return false;
	}

}