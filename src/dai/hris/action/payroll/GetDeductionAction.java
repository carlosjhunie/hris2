package dai.hris.action.payroll;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dai.hris.model.Deduction;
import dai.hris.service.payroll.IDeductionService;
import dai.hris.service.payroll.impl.DeductionService;

/**
 * Servlet implementation class GetDeductionAction
 */
@WebServlet("/GetDeductionAction")
public class GetDeductionAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IDeductionService svc = new DeductionService();
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
		String id = request.getParameter("id");
		if (id == null) {
			try {
				List<Deduction> list = svc.getAll();
				String json = gson.toJson(list);
				json = "{\"Result\":\"OK\",\"Records\":"+ json + "}";
				response.getWriter().print(json);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			try {
				Deduction in = svc.getDeductionById(Integer.parseInt(id));
				String json = gson.toJson(in);
				json = "{\"Result\":\"OK\",\"Records\":"+ json + "}";
				response.getWriter().print(json);
			} catch (Exception e) {
				e.printStackTrace();
				response.getWriter().print("{\"Result\":\"ERROR\",\"Message\":\"ERROR: GetDeductionAction: "+e.getMessage()+"\"}");
			}
		}
	}

}
