package dai.hris.action.payroll;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dai.hris.model.PhicContributionTable;
import dai.hris.service.payroll.IPhicContributionTableService;
import dai.hris.service.payroll.impl.PhicContributionTableService;

/**
 * Servlet implementation class SaveTaxTableAction
 */
@WebServlet("/SavePhicContributionTableAction")
public class SavePhicContributionTableAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IPhicContributionTableService svc = new PhicContributionTableService();
	private Gson gson = new Gson();

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
		String s_tableId = request.getParameter("phicContributionTableId");
		int taxTableId = 0;
		if (null != s_tableId) {
			taxTableId = Integer.parseInt(s_tableId);
		}
		PhicContributionTable tt = new PhicContributionTable();
		tt.setPhicContributionTableId(taxTableId);
		tt.setSalaryBase(new BigDecimal(request.getParameter("salaryBase")));
		tt.setEmployeeShare(new BigDecimal(request.getParameter("employeeShare")));
		tt.setEmployerShare(new BigDecimal(request.getParameter("employerShare")));
		tt.setTotalMonthlyPremium(new BigDecimal(request.getParameter("totalMonthlyPremium")));
		
		try {
			svc.saveOrUpdate(tt);
			String json = gson.toJson(tt);
			String jsonData = "{\"Result\":\"OK\",\"Record\":" + json + "}";
			response.getWriter().print(jsonData);
		} catch (SQLException e) {
			e.printStackTrace();
			String error = "{\"Result\":\"ERROR\", \"Message\":\"DB Error!\"}";
			response.getWriter().print(error);
		} catch (Exception e) {
			e.printStackTrace();
			String error = "{\"Result\":\"ERROR\"}";
			 response.getWriter().print(error);
		}
	}

}
