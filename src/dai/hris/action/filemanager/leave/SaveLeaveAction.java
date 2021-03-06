/**
 * 
 */
package dai.hris.action.filemanager.leave;

import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;

import dai.hris.action.filemanager.util.EmployeeUtil;
import dai.hris.model.Leave;
import dai.hris.service.filemanager.leave.ILeaveService;
import dai.hris.service.filemanager.leave.LeaveService;


@WebServlet("/SaveLeaveAction")
public class SaveLeaveAction extends HttpServlet {

	private static final long serialVersionUID = 1L;
	ILeaveService service = new LeaveService();
	Gson gson = new Gson();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		System.out.println("doPost SaveLeaveAction");

		/*
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String json = "";
        if(br != null){
            json = br.readLine();
        }
        
		System.out.println("here is the json output: ");
		System.out.println("=========================");
		System.out.println(json);
		System.out.println("=========================");
 
        // 2. initiate jackson mapper
        ObjectMapper mapper = new ObjectMapper();
 
        // 3. Convert received JSON to Article
        Leave leave = mapper.readValue(json, Leave.class);
        
       */
		
		//cannot use jackson when inputstream is not of json format
		//so need to get the request attributes manually.
		
		
		//so how to populate createdBy? who created the leave? - for now set as the empId logging the request
		/*
		Leave leave = new Leave();
		leave.setApprovedBy(StringUtils.isNotBlank(request.getParameter("approvedBy"))?Integer.parseInt(request.getParameter("approvedBy")):0);  //if 0, no approvedBy yet
		leave.setCreatedBy(StringUtils.isNotBlank(request.getParameter("empId"))?Integer.parseInt(request.getParameter("empId")):0);    //need to determine created by based from login
		leave.setCreatedDate(new java.sql.Date((new java.util.Date()).getTime()));
		leave.setDateFiled(new java.sql.Date((new java.util.Date()).getTime()));
		leave.setDateFrom(StringUtils.isNotEmpty(request.getParameter("dateFrom")) ? (new EmployeeUtil()).convertToSqlDate(request.getParameter("dateFrom")): null);
		leave.setDateTo(StringUtils.isNotEmpty(request.getParameter("dateTo")) ? (new EmployeeUtil()).convertToSqlDate(request.getParameter("dateTo")): null);
		leave.setEmpId(StringUtils.isNotBlank(request.getParameter("empId"))?Integer.parseInt(request.getParameter("empId")):0); //why 0 - since if add, you dont know the new empId yet
		leave.setLeaveTypeId(Integer.parseInt(request.getParameter("leaveTypeId")));
		leave.setNeed2Approvers(request.getParameter("need2Approvers"));
		leave.setNoDays(StringUtils.isNotBlank(request.getParameter("noDays"))?Integer.parseInt(request.getParameter("noDays")):0);
		leave.setRemarks(request.getParameter("remarks"));
		leave.setSecondaryApprover(StringUtils.isNotEmpty(request.getParameter("secondaryApprover"))?Integer.parseInt(request.getParameter("secondaryApprover")):0);
		*/
		
		
		//instead of getting all attributes manually, try to use Beanutils
		Leave leave = new Leave();
		HashMap<String, Object> map = new HashMap<String, Object>();
		Enumeration<String> names = request.getParameterNames();
		while (names.hasMoreElements()) {
		  String name = (String) names.nextElement();
		  map.put(name, request.getParameterValues(name));
		}
		try {
			 ConvertUtilsBean convertUtilsBean = BeanUtilsBean.getInstance().getConvertUtils();
			    convertUtilsBean.register(false, true, -1);
			    
			BeanUtils.populate(leave, map);
			
			//now since we have populated already, we need to set the values again for the fields that are not straightly mapped from request parameters
			leave.setCreatedBy(leave.getEmpId());  //created by emplId
			leave.setDateFiled(EmployeeUtil.getCurrentSystemDateSqlFormat());
			leave.setCreatedDate(EmployeeUtil.getCurrentSystemDateSqlFormat());
			leave.setStatus(0);
			
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		try {
			int leaveId = service.add(leave);
			//TODO We need to set the leaveId generated to the Leave object, before returning model object as json data
			leave.setLeaveId(leaveId);
			String json = gson.toJson(leave);
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
