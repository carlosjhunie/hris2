package dai.hris.action.cba;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dai.hris.model.EmployeeCBA;
import dai.hris.service.cba.EmployeeCBAService;
import dai.hris.service.cba.IEmployeeCBAService;

/**
 * Servlet implementation class GetAllEmployeeCBAAction
 */
@WebServlet("/GetAllEmployeeCBAAction")
public class GetAllEmployeeCBAAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IEmployeeCBAService service = new EmployeeCBAService();
	Gson gson = new Gson();


    public GetAllEmployeeCBAAction() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost GetAllEmployeeCBAAction");
		ArrayList<EmployeeCBA> list = new ArrayList<EmployeeCBA>();
		int empId = Integer.parseInt(request.getParameter("empId"));
		try {
			list = service.getEmployeeCBAByEmpId(empId);			
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
