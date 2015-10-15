package dai.hris.action.filemanager;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dai.hris.model.GenericDisplayOptionModel;

import com.google.gson.Gson;

import dai.hris.model.LeaveType;
import dai.hris.service.filemanager.leavetype.LeaveTypeService;
import dai.hris.service.filemanager.leavetype.ILeaveTypeService;

@WebServlet("/GetLeaveTypeAction")
public class GetLeaveTypeAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Gson gson = new Gson();
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String displayOption = request.getParameter("displayOption");
		int leaveTypeId = Integer.parseInt(request.getParameter("leaveTypeId"));
		
		ILeaveTypeService service = new LeaveTypeService();
		LeaveType leaveType = new LeaveType();
		
		try {
			leaveType = service.getLeaveTypeByLeaveTypeId(leaveTypeId);
		}
		catch(Exception e ){
			e.printStackTrace();
		}
		
		/**
		 * README - in order to display option in jtable from JSON
		 * use GenericDisplayOptionModel - variable names should be Value and DisplayText (same case only)
		 * and the output json should become Options, and need to add brackets
		 * 
		 * this will cause the leave entry displayed in jtable to display also the text associated to the leaveTypeId TG
		 * 
		 */
		if ("true".equalsIgnoreCase(displayOption)) {	
			//if need to be displayed in jTable,use generic model
		    	GenericDisplayOptionModel genericDisplayOptionModel = new GenericDisplayOptionModel();
		    	genericDisplayOptionModel.setValue(leaveType.getLeaveTypeId());
		    	genericDisplayOptionModel.setDisplayText(leaveType.getLeaveTypeName());
			
			String json = gson.toJson(genericDisplayOptionModel);
		    System.out.println("json: " + json);
	 
		    json = "{\"Result\":\"OK\",\"Options\":"+ json + "}";   ///note the change from Records to Option, added bracket
	        response.getWriter().print(json);
		
		} else {	

			//normal model
		    String json = gson.toJson(leaveType);
		    System.out.println("json: " + json);
	 
		    json = "{\"Result\":\"OK\",\"Records\":"+ json + "}";
	        response.getWriter().print(json);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		doGet(request, response);
	}
	

}
