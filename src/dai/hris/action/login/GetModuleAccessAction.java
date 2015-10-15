package dai.hris.action.login;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dai.hris.model.ModuleAccess;
import dai.hris.service.login.IModuleAccessService;
import dai.hris.service.login.ModuleAccessService;

/**
 * Servlet implementation class GetModuleAccessAction
 */
@WebServlet("/GetModuleAccessAction")
public class GetModuleAccessAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IModuleAccessService svc = new ModuleAccessService();
	Gson gson = new Gson();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int empId = Integer.parseInt(request.getParameter("empId"));
		try {
			ModuleAccess ma = svc.getModuleAccessByEmpId(empId);
			String json = gson.toJson(ma);
			System.out.println("json: " + json);
			String jsonData = "{\"Result\":\"OK\",\"Record\":" + json + "}";
			response.getWriter().print(jsonData);
		} catch (SQLException e) {
			e.printStackTrace();
			String error = "{\"Result\":\"ERROR\"}";
			response.getWriter().print(error);
		} catch (Exception e) {
			e.printStackTrace();
			String error = "{\"Result\":\"ERROR\"}";
			response.getWriter().print(error);
		}
	}

}
