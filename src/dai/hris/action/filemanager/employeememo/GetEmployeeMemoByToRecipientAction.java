package dai.hris.action.filemanager.employeememo;

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

import dai.hris.model.EmployeeMemo;
import dai.hris.model.EmployeeWorkHistory;
import dai.hris.service.filemanager.employeememo.EmployeeMemoService;
import dai.hris.service.filemanager.employeememo.IEmployeeMemoService;

/**
 * Servlet implementation class GetEmployeeMemoAction
 */

//get all of the employee's received memos (employee is the recipient)
@WebServlet("/GetEmployeeMemoByToRecipientAction")
public class GetEmployeeMemoByToRecipientAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IEmployeeMemoService employeeMemoService = new EmployeeMemoService();
	Gson gson = new Gson();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetEmployeeMemoByToRecipientAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet GetEmployeeMemoByToRecipientAction");
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost GetEmployeeMemoByToRecipientAction");
		List<EmployeeMemo> employeeMemoList = new ArrayList<EmployeeMemo>();
		int toRecipientEmpId = Integer.parseInt(request.getParameter("toRecipientEmpId"));
		try {
			employeeMemoList = employeeMemoService.getEmployeeMemoListByToRecipientEmpId(toRecipientEmpId);
		} catch (SQLException sqle) {
			// TODO Auto-generated catch block
			sqle.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    String json = gson.toJson(employeeMemoList);
	    System.out.println("json: " + json);
 
	    json = "{\"Result\":\"OK\",\"Records\":"+ json + "}";
        response.getWriter().print(json);
        
       
		
	}

}
