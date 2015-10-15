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
@WebServlet("/UpdateProvinceAction")
public class UpdateProvinceAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Gson gson = new Gson();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		Province province = new Province();
		
		province.setProvinceId(Integer.parseInt(request.getParameter("provinceId")));
		province.setProvinceName(request.getParameter("provinceName"));

		IProvinceService service = new ProvinceService();
		
		try {
			service.update(province);
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
