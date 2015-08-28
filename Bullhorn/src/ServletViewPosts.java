import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Bloguser;
import model.Bullhorn;

/**
 * Servlet implementation class ServletViewPosts
 */
@WebServlet("/ServletViewPosts")
public class ServletViewPosts extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletViewPosts() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		Bloguser bUser = (Bloguser) session.getAttribute("bloguser");
		String guest = (String) request.getParameter("guestUser");
		if (guest == null)
			guest = "no";
		List<Bullhorn> blogList = Database.getAllPost();
		String name = "";
		if (bUser != null) {
			name = bUser.getName();
		}
		request.setAttribute("user_name", name);
		String tableData = "";
		if (blogList != null) {
			tableData = "<br><br>";
			tableData += "\r<table class=table border=1 align=center>";
			tableData += "<tr style=background-color:green>";
			tableData += "<th>";
			tableData += "User";
			tableData += "</th>";
			tableData += "<th>";
			tableData += "Post";
			tableData += "</th>";
			tableData += "</tr>\r";

			for (Bullhorn b : blogList) {
				tableData += "<tr class='info'>";
				tableData += "<td>";
				tableData += "<a href= 'ServletProfile?linkuser="
						+ b.getBloguser().getUserId() + "'>"
						+ b.getBloguser().getName() + "</a>";
				tableData += "</td>";
				tableData += "<td>";
				tableData += b.getPost();
				tableData += "</td>";
				tableData += "</tr>\r";
			}
			tableData += "</table>\r";
		}

		String jsp = "";
		if (guest.equalsIgnoreCase("no")) {
			jsp = "/ViewPosts.jsp";
			tableData += "<br><br><a href= 'ServletBullhorn' class='pull-right btn btn-success'>Home</a>";
		} else if (guest.equalsIgnoreCase("yes")) {
			tableData += "<br><br><a href= 'LoginForm?logout=yes' class='pull-right btn btn-success'>Home</a>";
			jsp = "/GuestAccount.jsp";
			session.setAttribute("guestUser", guest);
		}

		request.setAttribute("message", tableData);
		getServletContext().getRequestDispatcher(jsp)
				.forward(request, response);

	}

}
