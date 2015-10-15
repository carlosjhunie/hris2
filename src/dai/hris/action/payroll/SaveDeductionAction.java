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

import dai.hris.model.Deduction;
import dai.hris.service.payroll.IDeductionService;
import dai.hris.service.payroll.impl.DeductionService;

/**
 * Servlet implementation class SaveDeductionAction
 */
@WebServlet("/SaveDeductionAction")
public class SaveDeductionAction extends HttpServlet {
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
		String s_deductionId = request.getParameter("deductionId");
		int deductionId = 0;
		if (null != s_deductionId) {
			deductionId = Integer.parseInt(s_deductionId);
		}
		Deduction dd = new Deduction();
		dd.setDeductionId(deductionId);
		dd.setDeductionName(request.getParameter("deductionName"));
		dd.setAmount(new BigDecimal(request.getParameter("amount")));
		dd.setEmployeeType(request.getParameter("employeeType"));
		dd.setPayrollType(request.getParameter("payrollType"));
		dd.setIsForSecondHalf(request.getParameter("isForSecondHalf"));
		
		/*Map<String, Object> map = new HashMap<String, Object>();
		Enumeration<String> names = request.getParameterNames();
		while (names.hasMoreElements()) {
		  String name = (String) names.nextElement();
		  map.put(name, request.getParameterValues(name));
		}
		try {
			 ConvertUtilsBean convertUtilsBean = BeanUtilsBean.getInstance().getConvertUtils();
			 convertUtilsBean.register(false, true, -1);
			 BeanUtils.populate(dd, map);
		}catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		
		try {
			svc.saveOrUpdate(dd);
			String json = gson.toJson(dd);
			String jsonData = "{\"Result\":\"OK\",\"Record\":" + json + "}";
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
