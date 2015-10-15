/**
 * 
 */
package dai.hris.action.filemanager;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dai.hris.model.Division;
import dai.hris.service.filemanager.division.DivisionService;
import dai.hris.service.filemanager.division.IDivisionService;


/**
 * @author rj
 *
 */
@WebServlet("/UpdateDivisionAction")
public class UpdateDivisionAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Gson gson = new Gson();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		Division division = new Division();
		
		division.setDivisionId(Integer.parseInt(request.getParameter("divisionId")));
		division.setDivisionName(request.getParameter("divisionName"));

		IDivisionService service = new DivisionService();
		
		try {
			service.update(division);
			response.getWriter().print("{\"Result\":\"OK\"}");
		}
		catch(Exception e ){
			e.printStackTrace();
			String error = "{\"Result\":\"ERROR\"}";
			 response.getWriter().print(error);
		}

		
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		doGet(request, response);
	}

}
