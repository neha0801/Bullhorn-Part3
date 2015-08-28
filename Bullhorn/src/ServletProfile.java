
import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import customTools.DBUtil;
import model.Bloguser;
import model.Bullhorn;

/**
 * Servlet implementation class ServletProfile
 */
@WebServlet("/ServletProfile")
public class ServletProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletProfile() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("dopost");
		Bloguser bUser = null;
		EntityManager em =  DBUtil.getEmFactory().createEntityManager();
		String linkUser =request.getParameter("linkuser");
		HttpSession session = request.getSession();
		bUser= (Bloguser) session.getAttribute("bloguser");
		String name="";
		if(bUser!=null){
			name=bUser.getName();
		}
		request.setAttribute("user_name",name);
		if (linkUser!=null){
			String  sql = "select b from Bloguser b where b.userId = :id";
			bUser = em.createQuery(sql, Bloguser.class).setParameter("id", Integer.parseInt(linkUser)).getSingleResult();
		}
		System.out.println("user = " + bUser.getUserId());

		String userData = "";
		userData+= "<div class='container'>";
		userData+= "<h1 align=center style='color: black'><b>User Profile</b></h1><br></br>";
		userData += "<div class='panel panel-primary col-sm-8'>";
		userData += "<div class='panel-heading'>";	
		userData +=	"Name: " + bUser.getName();
		System.out.println(bUser.getName());
		userData += "</div>";
		
		userData += "<div class='panel-body'>";	
		userData +=	"Email: " + bUser.getEmail();
		userData += "<br>";
		userData +=	"Motto: " + bUser.getMotto();
		userData += "<br>";
		userData += "<br>";
		userData +=	"Join Date: " + bUser.getJoindate();
		userData += "<br>";
		//reviewerData += "<a href='EditProfile'><span class='glyphicon glyphicon-pencil'></span>Edit</a>";
		userData += "</div>";		
		userData += "</div>";
		userData += "</div>";	
		String  sql = "select b from Bullhorn b where b.bloguser.userId = :id order by b.postId desc";
		List<Bullhorn> bullHornPost = em.createQuery(sql, model.Bullhorn.class).setParameter("id",bUser.getUserId()).getResultList();
		
		userData+="<br><br>";
		userData += "\r<table class=table border=1 align=center>";
		userData += "<tr style=background-color:green>";
		userData += "<th>";
		userData += "Post";
		userData += "</th>";
		userData += "</tr>\r";
		for(model.Bullhorn b : bullHornPost){
		userData += "<tr class='info'>";
		userData += "<td>";
		userData += b.getPost();
		userData += "</td>";
		userData += "</tr>\r";
		System.out.println("post = " +  b.getPost());
		}

		request.setAttribute("userData", userData);
		getServletContext().getRequestDispatcher("/Profile.jsp").forward(request, response);
	}
}
