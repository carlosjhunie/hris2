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

import dai.hris.model.City;
import dai.hris.service.filemanager.city.CityService;
import dai.hris.service.filemanager.city.ICityService;


/**
 * @author rj
 *
 */
@WebServlet("/AddCityAction")
public class AddCityAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Gson gson = new Gson();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		City City = new City();
		City.setCityName(request.getParameter("cityName"));

		ICityService service = new CityService();

		try {
			service.add(City);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String json = gson.toJson(City);
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
