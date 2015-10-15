package dai.hris.action.filemanager;

import java.io.IOException;
import java.util.ArrayList;

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

@WebServlet("/GetAllLeaveTypeAction")
public class GetAllLeaveTypeAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Gson gson = new Gson();
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String displayOption = request.getParameter("displayOption");
		
		ILeaveTypeService service = new LeaveTypeService();
		ArrayList<LeaveType> list = null;
		ArrayList <GenericDisplayOptionModel> genericDisplayOptionList = null;
		
		try {
			list = service.getAll();
		}
		catch(Exception e ){
			e.printStackTrace();
		}
		
		
		if ("true".equalsIgnoreCase(displayOption)) {	//if need to be displayed in jTable	
			genericDisplayOptionList = new ArrayList <GenericDisplayOptionModel>();
		    for (LeaveType leaveType: list) {
		    	GenericDisplayOptionModel genericDisplayOptionModel = new GenericDisplayOptionModel();
		    	genericDisplayOptionModel.setValue(leaveType.getLeaveTypeId());
		    	genericDisplayOptionModel.setDisplayText(leaveType.getLeaveTypeName());
		    	genericDisplayOptionList.add(genericDisplayOptionModel);
		    }			
			
			String json = gson.toJson(genericDisplayOptionList);
		    System.out.println("json: " + json);
	 
		    json = "{\"Result\":\"OK\",\"Options\":"+ json + "}";   ///note the change from Records to Options
	        response.getWriter().print(json);
		
		} else {	

		    String json = gson.toJson(list);
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
