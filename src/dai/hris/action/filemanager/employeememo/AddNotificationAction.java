package dai.hris.action.filemanager.employeememo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import com.google.gson.Gson;
import dai.hris.model.EmployeeNotification;
import dai.hris.service.filemanager.empnotification.EmpNotificationService;
import dai.hris.service.filemanager.empnotification.IEmpNotificationService;


@WebServlet("/AddNotificationAction")
public class AddNotificationAction extends HttpServlet {

	private static final long serialVersionUID = 1L;
	Gson gson = new Gson();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		EmployeeNotification model = new EmployeeNotification();		
		try {
			BeanUtils.populate(model, request.getParameterMap());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		IEmpNotificationService service = new EmpNotificationService();

		try {
			service.add(model);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String json = gson.toJson(model);
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
