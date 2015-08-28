

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import customTools.DBUtil;
import model.Bloguser;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("dopost");
		Bloguser bUser = null;
		String linkUser =request.getParameter("linkuser");
		HttpSession session = request.getSession();
		bUser= (Bloguser) session.getAttribute("bloguser");
		String name="";
		if(bUser!=null){
			name=bUser.getName();
		}
		request.setAttribute("user_name",name);
		if (linkUser!=null){
			EntityManager em =  DBUtil.getEmFactory().createEntityManager();;
			String  sql = "select b from Bloguser b where b.userId = :id";
			bUser = em.createQuery(sql, Bloguser.class).setParameter("id", Integer.parseInt(linkUser)).getSingleResult();
		}
		System.out.println("reviewer = " + bUser.getUserId());

		String userData = "";
		userData+= "<div class='container'><div class='jumbotron' align=center style='background-color: grey'> ";
		userData+= "<h1 align=center style='color: black'><b>User Profile</b></h1><br></br></div>";
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

		request.setAttribute("userData", userData);
		getServletContext().getRequestDispatcher("/Profile.jsp").forward(request, response);
	}

}
