package dai.hris.action.payroll;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dai.hris.model.HazardPay;
import dai.hris.service.payroll.IHazardPayService;
import dai.hris.service.payroll.impl.HazardPayService;

/**
 * Servlet implementation class GetHazardPayAction
 */
@WebServlet("/GetHazardPayAction")
public class GetHazardPayAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	IHazardPayService svc = new HazardPayService();
	Gson gson = new Gson();
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int empId = Integer.parseInt(request.getParameter("empId"));
		try {
			List<HazardPay> hpList = svc.getAllByEmployeeId(empId);
			String json = gson.toJson(hpList);
            System.out.println("json: " + json);
			String jsonData = "{\"Result\":\"OK\",\"Record\":" + json + "}";
			response.getWriter().print(jsonData);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
