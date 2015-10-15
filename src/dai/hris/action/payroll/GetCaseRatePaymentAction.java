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

import dai.hris.model.CaseRatePayment;
import dai.hris.service.payroll.ICaseRatePaymentService;
import dai.hris.service.payroll.impl.CaseRatePaymentService;

/**
 * Servlet implementation class GetCaseRatePaymentAction
 */
@WebServlet("/GetCaseRatePaymentAction")
public class GetCaseRatePaymentAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ICaseRatePaymentService service = new CaseRatePaymentService();
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
			List<CaseRatePayment> crpList = service.getAllByEmployeeId(empId);
			String json = gson.toJson(crpList);
            System.out.println("json: " + json);
			String jsonData = "{\"Result\":\"OK\",\"Records\":" + json + "}";
			response.getWriter().print(jsonData);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
