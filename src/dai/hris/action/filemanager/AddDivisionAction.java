/**
 * 
 */
package dai.hris.action.filemanager;

import java.io.IOException;
import java.util.ArrayList;

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
@WebServlet("/AddDivisionAction")
public class AddDivisionAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Gson gson = new Gson();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		Division division = new Division();
		division.setDivisionName(request.getParameter("divisionName"));

		IDivisionService service = new DivisionService();

		try {
			service.add(division);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String json = gson.toJson(division);
		System.out.println("json: " + json);
		String jsonData = "{\"Result\":\"OK\",\"Record\":" + json + "}";
		response.getWriter().print(jsonData);

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
