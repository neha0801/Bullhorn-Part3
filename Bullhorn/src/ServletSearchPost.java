

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
 * Servlet implementation class ServletSearchPost
 */
@WebServlet("/ServletSearchPost")
public class ServletSearchPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletSearchPost() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Bloguser bUser = (Bloguser) session.getAttribute("bloguser");
		String searchText = request.getParameter("search");
		String tableData = "";
		List<Bullhorn> blogList = null;
		if(searchText.equalsIgnoreCase("")){
			tableData="<p>No text enetered so</p>";
		}else
			blogList= Database.searchPost(searchText);
		String name = "";
		if(bUser!=null){
			name = bUser.getName();
		}
		request.setAttribute("user_name", name);

		
		if(blogList!=null){
			tableData="<br><br>";
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
				tableData += "<a href= 'ServletProfile?linkuser="+b.getBloguser().getUserId() +"'>" +b.getBloguser().getName() + "</a>";
				tableData += "</td>";
				tableData += "<td>";
				tableData += b.getPost();
				tableData += "</td>";
				tableData += "</tr>\r";
			}
			tableData += "</table>\r";
		} else
			tableData+=" <p>No data found!!</p>";

		
		
		request.setAttribute("message", tableData);
		getServletContext().getRequestDispatcher("/UserAccount.jsp").forward(
					request, response);
	}

}
