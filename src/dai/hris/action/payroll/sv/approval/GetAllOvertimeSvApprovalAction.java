package dai.hris.action.payroll.sv.approval;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dai.hris.model.EmployeeOvertime;
import dai.hris.service.overtime.EmployeeOvertimeService;
import dai.hris.service.overtime.IEmployeeOvertimeService;

@WebServlet("/GetAllOvertimeSvApprovalAction")
public class GetAllOvertimeSvApprovalAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Gson gson = new Gson();
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		int superVisorId = Integer.parseInt(request.getParameter("superVisorId"));
		IEmployeeOvertimeService service = new EmployeeOvertimeService();
		ArrayList<EmployeeOvertime> list = null;
		try {
			list = service.getAllOvertimeForSvApprovalBySuperVisorId(superVisorId);
		}
		catch(Exception e ){
			e.printStackTrace();
		}	

	    String json = gson.toJson(list);
 
	    json = "{\"Result\":\"OK\",\"Records\":"+ json + "}";
        response.getWriter().print(json);

	}
	

}
