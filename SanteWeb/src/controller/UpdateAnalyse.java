package controller;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibytecode.business.HelloWorld;

/**
 * Servlet implementation class UpdateAnalyse
 */
@WebServlet("/UpdateAnalyse")
public class UpdateAnalyse extends HttpServlet {
	@EJB
	private HelloWorld h;
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateAnalyse() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
		response.setContentType("application/json");
		String id1=request.getParameter("id");
		//int id,double tension,double glycemie,double bcm,double fer,double magnesium
		String glycemie1=request.getParameter("glycemie");
		String bcm1=request.getParameter("bcm");
		String tension1=request.getParameter("tension");
		String designation = request.getParameter("designation");
		String fer1=request.getParameter("fer");
		String magnesium1=request.getParameter("magnesium");
		int id = Integer.parseInt(id1);
		double glycemie = Double.parseDouble(glycemie1);
		double bcm = Double.parseDouble(bcm1);
		double fer = Double.parseDouble(fer1);
		double tension = Double.parseDouble(tension1);

		double magnesium = Double.parseDouble(magnesium1);
		h.updateAnalyse(designation, tension, glycemie, bcm, fer, magnesium);

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
