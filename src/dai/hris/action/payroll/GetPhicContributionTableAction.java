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

import dai.hris.model.PhicContributionTable;
import dai.hris.service.payroll.IPhicContributionTableService;
import dai.hris.service.payroll.impl.PhicContributionTableService;

/**
 * Servlet implementation class GetTaxTableAction
 */
@WebServlet("/GetPhicContributionTableAction")
public class GetPhicContributionTableAction extends HttpServlet {
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
		try {
			List<PhicContributionTable> list = svc.getAll();
			String json = gson.toJson(list);
			json = "{\"Result\":\"OK\",\"Records\":"+ json + "}";
			response.getWriter().print(json);
		} catch (SQLException e) {
			e.printStackTrace();
			String error = "{\"Result\":\"ERROR\", \"Message\":\"DB Error!\"}";
			response.getWriter().print(error);
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().print("{\"Result\":\"ERROR\",\"Message\":\"ERROR: GetTaxTableAction: "+e.getMessage()+"\"}");
		}
	}
}
