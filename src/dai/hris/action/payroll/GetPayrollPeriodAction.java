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

import dai.hris.model.PayrollPeriod;
import dai.hris.service.payroll.IPayrollPeriodService;
import dai.hris.service.payroll.impl.PayrollPeriodService;

/**
 * Servlet implementation class GetPayrollPeriodAction
 */
@WebServlet("/GetPayrollPeriodAction")
public class GetPayrollPeriodAction extends HttpServlet {
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
		try {
			List<PayrollPeriod> ppList = svc.getAll();
			String json = gson.toJson(ppList);
            System.out.println("json: " + json);
			String jsonData = "{\"Result\":\"OK\",\"Records\":" + json + "}";
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
