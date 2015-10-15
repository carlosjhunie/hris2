/**
 * 
 */
package dai.hris.action.loan;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.google.gson.Gson;



import dai.hris.model.LoanPayments;
import dai.hris.service.loan.ILoanPaymentsService;
import dai.hris.service.loan.LoanPaymentsService;



/**
 * @author rj
 *
 */
@WebServlet("/AddLoanPaymentAction")
public class AddLoanPaymentAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Gson gson = new Gson();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		LoanPayments loanPayment = new LoanPayments();		
		try {
			BeanUtils.populate(loanPayment, request.getParameterMap());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		ILoanPaymentsService service = new LoanPaymentsService();

		try {
			service.add(loanPayment);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String json = gson.toJson(loanPayment);
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
