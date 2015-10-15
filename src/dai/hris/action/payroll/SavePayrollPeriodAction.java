package dai.hris.action.payroll;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;

import com.google.gson.Gson;

import dai.hris.model.PayrollPeriod;
import dai.hris.service.payroll.IPayrollPeriodService;
import dai.hris.service.payroll.impl.PayrollPeriodService;

/**
 * Servlet implementation class AddPayrollPeriodAction
 */
@WebServlet("/SavePayrollPeriodAction")
public class SavePayrollPeriodAction extends HttpServlet {
    private static final long serialVersionUID = 6409657062816464696L;

    private IPayrollPeriodService service = new PayrollPeriodService();
	private Gson gson = new Gson();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String json_param = "";
        if(br != null){
            json_param = br.readLine();
        }
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> param = mapper.readValue(json_param, Map.class);
        
		int empId = Integer.parseInt(param.get("empId"));
        String updateOption = param.get("updateOption");
        
		int payYear = Integer.parseInt(param.get("payYear"));
		int payMonth = Integer.parseInt(param.get("payMonth"));
		String payrollType = param.get("payrollType");
		String payPeriod = param.get("payPeriod");
		int numWorkDays = Integer.parseInt(param.get("numWorkDays"));
		java.sql.Date fromDate = new EmployeeUtil().convertToSqlDate(param.get("fromDate"));
		java.sql.Date toDate = new EmployeeUtil().convertToSqlDate(param.get("toDate"));
		java.sql.Date payDate = new EmployeeUtil().convertToSqlDate(param.get("payDate"));
		String payrollCode = param.get("payrollCode");*/
		
		PayrollPeriod pp = new PayrollPeriod();
		Map<String, Object> map = new HashMap<String, Object>();
		Enumeration<String> names = request.getParameterNames();
		while (names.hasMoreElements()) {
		  String name = (String) names.nextElement();
		  map.put(name, request.getParameterValues(name));
		}
		try {
			 ConvertUtilsBean convertUtilsBean = BeanUtilsBean.getInstance().getConvertUtils();
			 convertUtilsBean.register(false, true, -1);
			    
			 BeanUtils.populate(pp, map);
		}catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			/*if ("jobTitle".equalsIgnoreCase(updateOption)) {
				int jtId = Integer.parseInt(param.get("jobTitleId"));
				List<PayrollPeriod> ppInsertList = buildInsertList(jtId);
				for (int i = 0; i < ppInsertList.size(); i++) {
					PayrollPeriod pp = ppInsertList.get(i);
					pp.setPayYear(payYear);
					pp.setPayMonth(payMonth);
					pp.setPayrollType(payrollType);
					pp.setPayPeriod(payPeriod);
					pp.setNumWorkDays(numWorkingDays);
					pp.setFromDate(fromDate);
					pp.setToDate(toDate);
					pp.setPayDate(payDate);
					pp.setPayrollCode(payrollCode);
				}
				service.batchInsert(ppInsertList);
				List<PayrollPeriod> ppUpdateList = service.getAllByJobTitleId(jtId);
				for (int i = 0; i < ppUpdateList.size(); i++) {
					PayrollPeriod pp = ppUpdateList.get(i);
					pp.setPayYear(payYear);
					pp.setPayMonth(payMonth);
					pp.setPayrollType(payrollType);
					pp.setPayPeriod(payPeriod);
					pp.setNumWorkDays(numWorkingDays);
					pp.setFromDate(fromDate);
					pp.setToDate(toDate);
					pp.setPayDate(payDate);
					pp.setPayrollCode(payrollCode);
				}
				service.batchUpdate(ppUpdateList);
				String json = gson.toJson(ppUpdateList.addAll(ppInsertList));
				System.out.println("json: " + json);
				String jsonData = "{\"Result\":\"OK\",\"Record\":" + json + "}";
				response.getWriter().print(jsonData);
			} else {*/
				/*PayrollPeriod pp = new PayrollPeriod();
				pp.setPayYear(payYear);
				pp.setPayMonth(payMonth);
				pp.setPayrollType(payrollType);
				pp.setPayPeriod(payPeriod);
				pp.setNumWorkDays(numWorkDays);
				pp.setFromDate(fromDate);
				pp.setToDate(toDate);
				pp.setPayDate(payDate);
				pp.setPayrollCode(payrollCode);*/
				
				service.saveOrUpdate(pp);
				String json = gson.toJson(pp);
				System.out.println("json: " + json);
				String jsonData = "{\"Result\":\"OK\",\"Record\":" + json + "}";
				response.getWriter().print(jsonData);
			//}
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

	/*private List<PayrollPeriod> buildInsertList(int jtId) throws SQLException, Exception {
		List<PayrollPeriod> resultList = new ArrayList<PayrollPeriod>();
		List<PayrollPeriod> empWithExistingHP = service.getAllByJobTitleId(jtId);
	    //change this code to Employee service call
	    List<Integer> empIdList = new CaseRatePaymentService().getAllEmpIdByJobTitleId(jtId);
	    for (int i = 0; i < empIdList.size(); i++) {
			for (int j = 0; j < empWithExistingHP.size(); j++) {
				if (empIdList.get(i) == empWithExistingHP.get(i).getEmpId()) {
					continue;
				} else {
					PayrollPeriod pp = new PayrollPeriod();
					//pp.setEmpId(empIdList.get(i));
					resultList.add(pp);
				}
			}
		}
	    return resultList;
	}*/
}
