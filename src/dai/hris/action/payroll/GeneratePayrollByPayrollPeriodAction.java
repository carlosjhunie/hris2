package dai.hris.action.payroll;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dai.hris.model.Employee;
import dai.hris.model.EmployeePayrollRun;
import dai.hris.service.payroll.IPayrollRunService;
import dai.hris.service.payroll.PayrollRunService;

@WebServlet("/GeneratePayrollByPayrollPeriodAction")
public class GeneratePayrollByPayrollPeriodAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IPayrollRunService svc = new PayrollRunService();
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
		Employee loggedInEmployee = (Employee) request.getSession().getAttribute("employeeLoggedIn");	
		int payrollPeriodId = Integer.valueOf(request.getParameter("payrollPeriodId"));
		String payrollType = request.getParameter("payrollType");
		System.out.println("payrollPeriodId: " + payrollPeriodId);
		ArrayList<EmployeePayrollRun> list = new ArrayList<EmployeePayrollRun>();
		try {
			
			IPayrollRunService service = new PayrollRunService();
			
			service.generatePayrollByPayrollPeriod(payrollPeriodId, payrollType, loggedInEmployee.getEmpId());
			RequestDispatcher dispatcher = null;
	        dispatcher = getServletContext().getRequestDispatcher("/generateAndLockPayroll.jsp");
	        

	        if (dispatcher != null) {
	        	response.setContentType("text/html");
	            dispatcher.include(request, response);
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
