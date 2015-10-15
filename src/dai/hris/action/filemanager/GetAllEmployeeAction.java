/**
 * 
 */
package dai.hris.action.filemanager;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dai.hris.model.Employee;
import dai.hris.model.GenericDisplayOptionModel;
import dai.hris.service.filemanager.employee.EmployeeService;
import dai.hris.service.filemanager.employee.IEmployeeService;


@WebServlet("/GetAllEmployeeAction")
public class GetAllEmployeeAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IEmployeeService employeeService = new EmployeeService();	
	
	Gson gson = new Gson();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		ArrayList<Employee> list = new ArrayList<Employee>();
		String displayOption = request.getParameter("displayOption");		
		ArrayList <GenericDisplayOptionModel> genericDisplayOptionList = null;
		
		
		try {			

			list = employeeService.getAll();		
			
		} catch (SQLException sqe1) {
			//TODO add proper logging
			System.err.println(sqe1.getMessage());
			sqe1.printStackTrace();
		} catch (Exception e) {
			//TODO add proper logging
			System.err.println(e.getMessage());
			e.printStackTrace();			
		}
		
		if ("true".equalsIgnoreCase(displayOption)) {	
			//if need to be displayed in jTable,use generic model
			genericDisplayOptionList = new ArrayList <GenericDisplayOptionModel>() ;
				for (Employee employee:list){				
		    	GenericDisplayOptionModel genericDisplayOptionModel = new GenericDisplayOptionModel();
		    	genericDisplayOptionModel.setValue(employee.getEmpId());
		    	genericDisplayOptionModel.setDisplayText(employee.getEmpNo() + " " + employee.getFirstname() + " "+employee.getLastname());
		    	genericDisplayOptionList.add(genericDisplayOptionModel);
		    	
				}
				
			//now also add a not applicable (empId=0 option)
			GenericDisplayOptionModel dummyModel = new GenericDisplayOptionModel();
			dummyModel.setValue(0);
			dummyModel.setDisplayText("N/A");
			genericDisplayOptionList.add(dummyModel);
			
			String json = gson.toJson(genericDisplayOptionList);
		    System.out.println("json: " + json);
	 
		    json = "{\"Result\":\"OK\",\"Options\":"+ json + "}";   ///note the change from Records to Option
	        response.getWriter().print(json);
		
		} else {	
		
		
		String json = gson.toJson(list);		
		 
		json = "{\"Employee\":"+json+"}";
	    response.getWriter().print(json);	
		}
		
	}

}
