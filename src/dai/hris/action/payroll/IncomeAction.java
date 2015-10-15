package dai.hris.action.payroll;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dai.hris.model.Income;
import dai.hris.service.payroll.IIncomeService;
import dai.hris.service.payroll.impl.IncomeService;

/**
 * Servlet implementation class IncomeAction
 */
@WebServlet("/IncomeAction")
public class IncomeAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IIncomeService svc = new IncomeService();
	Gson gson = new Gson();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		if (id == null) {
			try {
				List<Income> list = svc.getAll();
				String json = gson.toJson(list);
				json = "{\"Result\":\"OK\",\"Records\":"+ json + "}";
				response.getWriter().print(json);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			try {
				Income in = svc.getIncomeById(Integer.parseInt(id));
				String json = gson.toJson(in);
				json = "{\"Result\":\"OK\",\"Records\":"+ json + "}";
				response.getWriter().print(json);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Income> list = null;
		try {
			list = svc.getAll();
			Income in = new Income();
			in.setIncomeId(1);
			in.setIncomeName("Test Income");
			in.setAmount(new BigDecimal("100000"));
			
			list.add(in);
			
			String json = gson.toJson(list);
		    json = "{\"Result\":\"OK\",\"Records\":"+ json + "}";
	        response.getWriter().print(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
