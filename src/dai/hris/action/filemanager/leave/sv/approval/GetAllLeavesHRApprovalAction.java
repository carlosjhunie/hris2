package dai.hris.action.filemanager.leave.sv.approval;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dai.hris.model.Leave;
import dai.hris.service.filemanager.leave.ILeaveService;
import dai.hris.service.filemanager.leave.LeaveService;

@WebServlet("/GetAllLeavesHRApprovalAction")
public class GetAllLeavesHRApprovalAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Gson gson = new Gson();
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		
		ILeaveService service = new LeaveService();
		ArrayList<Leave> list = null;
		try {
			list = service.getAllLeavesForHRApproval();
		}
		catch(Exception e ){
			e.printStackTrace();
		}	

	    String json = gson.toJson(list);
 
	    json = "{\"Result\":\"OK\",\"Records\":"+ json + "}";
        response.getWriter().print(json);

	}
	

}
