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

import dai.hris.model.Country;
import dai.hris.service.filemanager.country.CountryService;
import dai.hris.service.filemanager.country.ICountryService;


/**
 * @author rj
 *
 */
@WebServlet("/AddCountryAction")
public class AddCountryAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Gson gson = new Gson();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		Country country = new Country();
		country.setCountryName(request.getParameter("countryName"));

		ICountryService service = new CountryService();

		try {
			service.add(country);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String json = gson.toJson(country);
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
