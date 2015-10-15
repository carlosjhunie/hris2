package dai.hris.action.filemanager.employeefamilymember;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dai.hris.model.EducationalBackground;
import dai.hris.model.EmployeeFamilyMember;
import dai.hris.service.filemanager.educationalbackground.EducationalBackgroundService;
import dai.hris.service.filemanager.educationalbackground.IEducationalBackgroundService;
import dai.hris.service.filemanager.employeefamilymember.EmployeeFamilyMemberService;
import dai.hris.service.filemanager.employeefamilymember.IEmployeeFamilyMemberService;

/**
 * Servlet implementation class GetEmployeeFamilyMember
 */
@WebServlet("/GetEmployeeFamilyMemberAction")
public class GetEmployeeFamilyMemberAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	IEmployeeFamilyMemberService employeeFamilyMemberService = new EmployeeFamilyMemberService();
	Gson gson = new Gson();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetEmployeeFamilyMemberAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet GetEmployeeFamilyMemberAction");
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost GetEmployeeFamilyMemberAction");
		List<EmployeeFamilyMember> employeeFamilyMemberList = new ArrayList<EmployeeFamilyMember>();
		int empId = Integer.parseInt(request.getParameter("empId"));
		try {
			employeeFamilyMemberList = employeeFamilyMemberService.getEmployeeFamilyMemberListByEmpId(empId);
		} catch (SQLException sqle) {
			// TODO Auto-generated catch block
			sqle.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String json = gson.toJson(employeeFamilyMemberList);
	    System.out.println("json: " + json);
 
	    json = "{\"Result\":\"OK\",\"Records\":"+ json + "}";
        response.getWriter().print(json);
		
		
	}

}
