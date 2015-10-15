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

import dai.hris.model.TaxTable;
import dai.hris.service.payroll.ITaxTableService;
import dai.hris.service.payroll.impl.TaxTableService;

/**
 * Servlet implementation class GetTaxTableAction
 */
@WebServlet("/GetTaxTableAction")
public class GetTaxTableAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ITaxTableService svc = new TaxTableService();
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
			List<TaxTable> list = svc.getAll();
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
