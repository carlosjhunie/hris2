package dai.hris.action.loan;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dai.hris.model.LoanPayments;
import dai.hris.service.loan.ILoanPaymentsService;
import dai.hris.service.loan.LoanPaymentsService;

@WebServlet("/GetAllLoanPaymentsByLoanEntryId")
public class GetAllLoanPaymentsByLoanEntryId extends HttpServlet {
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

		int loanEntryId = Integer.parseInt(request.getParameter("loanEntryId"));
		System.out.println("GetAllLoanPaymentsByLoanEntryId loanEntryId is " +loanEntryId);
		ILoanPaymentsService service = new LoanPaymentsService();
		ArrayList<LoanPayments> list = null;
		try {
			list = service.getAllLoanPaymentsByLoanEntryId(loanEntryId);
			System.out.println("GetAllLoanPaymentsByLoanEntryId with loanEntryId " + loanEntryId);
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
