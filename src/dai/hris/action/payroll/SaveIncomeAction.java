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

import dai.hris.model.Income;
import dai.hris.service.payroll.IIncomeService;
import dai.hris.service.payroll.impl.IncomeService;

/**
 * Servlet implementation class SaveIncomeAction
 */
@WebServlet("/SaveIncomeAction")
public class SaveIncomeAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IIncomeService svc = new IncomeService();
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
		String s_incomeId = request.getParameter("incomeId");
		int incomeId = 0;
		if (null != s_incomeId) {
			incomeId = Integer.parseInt(s_incomeId);
		}
		Income in = new Income();
		in.setIncomeId(incomeId);
		in.setIncomeName(request.getParameter("incomeName"));
		in.setAmount(new BigDecimal(request.getParameter("amount")));
		in.setIsTaxable(request.getParameter("isTaxable"));
		in.setEmployeeType(request.getParameter("employeeType"));
		in.setPayrollType(request.getParameter("payrollType"));
		in.setIsForSecondHalf(request.getParameter("isForSecondHalf"));
		
		/*Map<String, Object> map = new HashMap<String, Object>();
		Enumeration<String> names = request.getParameterNames();
		while (names.hasMoreElements()) {
		  String name = (String) names.nextElement();
		  map.put(name, request.getParameterValues(name));
		}
		try {
			 ConvertUtilsBean convertUtilsBean = BeanUtilsBean.getInstance().getConvertUtils();
			 convertUtilsBean.register(false, true, -1);
			    
			 BeanUtils.populate(in, map);
			 in.setTaxable(Boolean.valueOf("0"));
		}catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		
		try {
			svc.saveOrUpdate(in);
			String json = gson.toJson(in);
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
