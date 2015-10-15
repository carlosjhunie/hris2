package dai.hris.action.loan;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;


import dai.hris.model.LoanEntry;
import dai.hris.service.loan.ILoanEntryService;
import dai.hris.service.loan.LoanEntryService;

@WebServlet("/GetAllLoanEntryByEmpIdAction")
public class GetAllLoanEntryByEmpIdAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Gson gson = new Gson();
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		
		// TODO Auto-generated method stub

		int empId = Integer.parseInt(request.getParameter("empId"));
		System.out.println("GetAllLoanEntryByEmpIdAction empId is " +empId);
		ILoanEntryService service = new LoanEntryService();
		ArrayList<LoanEntry> list = null;
		try {
			list = service.getAllLoanEntryByEmpId(empId);
			System.out.println("GetAllLoanEntryByEmpIdAction with empId " + empId);
		}
		catch(Exception e ){
			e.printStackTrace();
		}
	    String json = gson.toJson(list);
	    System.out.println("json: " + json);
 
	    json = "{\"Result\":\"OK\",\"Records\":"+ json + "}";
        response.getWriter().print(json);

	}
	

}
