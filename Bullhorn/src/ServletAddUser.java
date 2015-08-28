

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
 * Servlet implementation class ServletAddUser
 */
@WebServlet("/AddUser")
public class ServletAddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static List<Bloguser> userList;
	String error="";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAddUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//getServletContext().getRequestDispatcher("/RegisterUser.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		em.getTransaction().begin();
	    userList = em.createQuery("SELECT u FROM Bloguser u").getResultList();
	    em.getTransaction().commit();
	    em.close();
	    if(usedEmail(request)){
	    	System.out.println("user Exists");
			error= "Invalid Credential";
			request.setAttribute("error", error);
			getServletContext().getRequestDispatcher("/RegisterUser.jsp").forward(request, response);
	    }else{
	    	System.out.println("User Created");
	    	String name = request.getParameter("name");
	    	String motto = request.getParameter("motto");
	    	String email = request.getParameter("email");
	    	String password = request.getParameter("confirmPassword");
			model.Bloguser bUser = new model.Bloguser();
			System.out.println(email);
			bUser.setName(name);
			bUser.setMotto(motto);
			bUser.setEmail(email);
			bUser.setUserPassword(password);
			System.out.println("user email : " +bUser.getEmail());
			Database.insertUser(bUser);
			HttpSession session = request.getSession();
			session.setAttribute("bloguser", bUser);
			getServletContext().getRequestDispatcher("/Login.jsp").forward(request, response);
			
	    }

	}
	
	private boolean usedEmail(HttpServletRequest request) {
		String email = request.getParameter("email");
		System.out.println("email = " + email);
		for (Bloguser b : userList) {
			System.out.println(b.getEmail());
			if (b.getEmail().equalsIgnoreCase(email)) {
				return true;
			}
		}
		return false;
	}

}
