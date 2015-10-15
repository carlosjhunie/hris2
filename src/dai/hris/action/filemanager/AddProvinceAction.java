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

import dai.hris.model.Province;
import dai.hris.service.filemanager.province.ProvinceService;
import dai.hris.service.filemanager.province.IProvinceService;


/**
 * @author rj
 *
 */
@WebServlet("/AddProvinceAction")
public class AddProvinceAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Gson gson = new Gson();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		Province Province = new Province();
		Province.setProvinceName(request.getParameter("provinceName"));

		IProvinceService service = new ProvinceService();

		try {
			service.add(Province);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String json = gson.toJson(Province);
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
