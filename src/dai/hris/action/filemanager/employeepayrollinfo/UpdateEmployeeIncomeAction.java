package dai.hris.action.filemanager.employeepayrollinfo;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.google.gson.Gson;

import dai.hris.model.EmployeeIncome;
import dai.hris.service.filemanager.employeepayrollinfo.EmployeeIncomeDeductionService;
import dai.hris.service.filemanager.employeepayrollinfo.IEmployeeIncomeDeductionService;

@WebServlet("/UpdateEmployeeIncomeAction")
public class UpdateEmployeeIncomeAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IEmployeeIncomeDeductionService service = new EmployeeIncomeDeductionService();
	Gson gson = new Gson();

    public UpdateEmployeeIncomeAction() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost UpdateEmployeeIncomeAction");
		EmployeeIncome employeeIncome = new EmployeeIncome();
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		Enumeration<String> names = request.getParameterNames();
		while (names.hasMoreElements()) {
		  String name = (String) names.nextElement();
		  map.put(name, request.getParameterValues(name));
		}
		try {
			    
			BeanUtils.populate(employeeIncome, map);
	
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

		try {
			int empIncomeId = service.updateIncome(employeeIncome);
			employeeIncome.setEmpIncomeId(empIncomeId);
			String json = gson.toJson(employeeIncome);
			String jsonData = "{\"Result\":\"OK\",\"Record\":" + json + "}";
			System.out.println(jsonData);
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
