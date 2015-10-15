package dai.hris.action.payroll;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dai.hris.model.PayrollPeriod;
import dai.hris.service.payroll.IPayrollPeriodService;
import dai.hris.service.payroll.impl.PayrollPeriodService;

@WebServlet("/GetAllPayrollPeriodAction")
public class GetAllPayrollPeriodAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IPayrollPeriodService svc = new PayrollPeriodService();
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
		/*
		 * use only either includeLocked (true/false) or includeGenerated (true/false)
		 */
		Boolean includeLocked = Boolean.valueOf(request.getParameter("includeLocked"));
		Boolean includeGenerated = Boolean.valueOf(request.getParameter("includeGenerated"));  //boolean
		ArrayList<PayrollPeriod> list = new ArrayList<PayrollPeriod>();
		try {
			if (true == includeLocked) {
				list = svc.getAll(includeLocked);
			}
			else if (true == includeGenerated) {
				list = svc.getAllGenerated();
			}
			String json = gson.toJson(list);
			json = "{\"Result\":\"OK\",\"Records\":"+ json + "}";
			response.getWriter().print(json);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
