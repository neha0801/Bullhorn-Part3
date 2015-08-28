

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Bloguser;

/**
 * Servlet implementation class ServletBullhorn
 */
@WebServlet("/ServletBullhorn")
public class ServletBullhorn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletBullhorn() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Bloguser bUser= (Bloguser) session.getAttribute("bloguser");
		request.setAttribute("user_name", bUser.getName());
		getServletContext().getRequestDispatcher("/UserAccount.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String post = request.getParameter("post");
		HttpSession session = request.getSession();
		Bloguser bUser= (Bloguser) session.getAttribute("bloguser");
		request.setAttribute("user_name", bUser.getName());
		model.Bullhorn postObj = new model.Bullhorn();
		System.out.println(post);
		postObj.setPost(post);
		postObj.setBloguser(bUser);
		System.out.println(bUser.getEmail());
		System.out.println(postObj.getPost());
		System.out.println(postObj.getPostId());
		Database.insert(postObj);
		
		getServletContext().getRequestDispatcher("/UserAccount.jsp").forward(request, response);
		
		
	}

}
