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
 * Servlet implementation class ClientServlet
 */
@WebServlet(name="ClientServlet",urlPatterns="/Kiady")
public class ClientServlet extends HttpServlet {
	@EJB
	private HelloWorld h;
	
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: "+h.getSomeDataMongo().toString()).append(request.getContextPath());
		//response.getWriter().append(h.getChart());
		
		if(request.getParameter("idMaladie")==null) {
			request.setAttribute("resultats",h.getChart() );
			response.getWriter().append(h.getChart());
		}else {
			int idMaladie = Integer.parseInt(request.getParameter("idMaladie"));
			request.setAttribute("LaMaladie", h.selectOne(idMaladie,"Maladies"));
			response.getWriter().append(h.selectOne(idMaladie,"Maladies"));
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
