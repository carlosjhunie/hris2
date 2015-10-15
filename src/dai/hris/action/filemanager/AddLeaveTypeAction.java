/**
 * 
 */
package dai.hris.action.filemanager;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dai.hris.model.LeaveType;
import dai.hris.service.filemanager.leavetype.LeaveTypeService;
import dai.hris.service.filemanager.leavetype.ILeaveTypeService;


/**
 * @author rj
 *
 */
@WebServlet("/AddLeaveTypeAction")
public class AddLeaveTypeAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Gson gson = new Gson();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		LeaveType leaveType = new LeaveType();
		leaveType.setLeaveTypeName(request.getParameter("leaveTypeName"));

		ILeaveTypeService service = new LeaveTypeService();

		try {
			service.add(leaveType);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String json = gson.toJson(leaveType);
		System.out.println("json: " + json);
		String jsonData = "{\"Result\":\"OK\",\"Record\":" + json + "}";
		response.getWriter().print(jsonData);

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
