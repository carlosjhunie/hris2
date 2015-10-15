package dai.hris.action.filemanager.employeefamilymember;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import dai.hris.model.EducationalBackground;
import dai.hris.model.EmployeeFamilyMember;
import dai.hris.service.filemanager.employeefamilymember.EmployeeFamilyMemberService;
import dai.hris.service.filemanager.employeefamilymember.IEmployeeFamilyMemberService;

/**
 * Servlet implementation class SaveEmployeeFamilyMember
 */
@WebServlet("/SaveEmployeeFamilyMemberAction")
public class SaveEmployeeFamilyMemberAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IEmployeeFamilyMemberService employeeFamilyMemberService = new EmployeeFamilyMemberService();
	Gson gson = new Gson();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveEmployeeFamilyMemberAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		EmployeeFamilyMember efm = new EmployeeFamilyMember();
		efm.setEmpId(Integer.parseInt(request.getParameter("empId")));
		efm.setContactNum(request.getParameter("contactNum"));
		efm.setAge(Integer.parseInt(request.getParameter("age")));
		efm.setGender(request.getParameter("gender"));
		efm.setName(request.getParameter("name"));
		efm.setRelation(request.getParameter("relation"));
		efm.setRemarks(request.getParameter("remarks"));	

		try {
			int empFamilyMemberId = employeeFamilyMemberService.add(efm);
			
			String json = gson.toJson(efm);
			System.out.println("json: " + json);
			String jsonData = "{\"Result\":\"OK\",\"Record\":" + json + "}";
			response.getWriter().print(jsonData);	
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
	}

}
