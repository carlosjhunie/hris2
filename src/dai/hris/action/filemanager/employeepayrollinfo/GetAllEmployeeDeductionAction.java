package dai.hris.action.filemanager.employeepayrollinfo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dai.hris.model.EmployeeDeduction;
import dai.hris.service.filemanager.employeepayrollinfo.EmployeeIncomeDeductionService;
import dai.hris.service.filemanager.employeepayrollinfo.IEmployeeIncomeDeductionService;

@WebServlet("/GetAllEmployeeDeductionAction")
public class GetAllEmployeeDeductionAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IEmployeeIncomeDeductionService service = new EmployeeIncomeDeductionService();
	Gson gson = new Gson();

    public GetAllEmployeeDeductionAction() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost GetAllEmployeeDeductionAction");
		ArrayList<EmployeeDeduction> list = new ArrayList<EmployeeDeduction>();
		int empId = Integer.parseInt(request.getParameter("empId"));
		try {
			list = service.getEmployeeDeductionByEmpId(empId);
		} catch (SQLException sqle) {
			// TODO Auto-generated catch block
			sqle.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//ServletOutputStream out = response.getOutputStream();
		response.setContentType("application/json");
		String json = gson.toJson(list);
        
	    json = "{\"Result\":\"OK\",\"Records\":"+ json + "}";
	    response.getWriter().print(json);
	}

}
