/**
 * 
 */
package dai.hris.action.loan;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.google.gson.Gson;

import dai.hris.model.Country;
import dai.hris.model.LoanEntry;
import dai.hris.service.filemanager.country.CountryService;
import dai.hris.service.filemanager.country.ICountryService;
import dai.hris.service.loan.ILoanEntryService;
import dai.hris.service.loan.LoanEntryService;


/**
 * @author rj
 *
 */
@WebServlet("/AddLoanEntryAction")
public class AddLoanEntryAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Gson gson = new Gson();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		LoanEntry loanEntry = new LoanEntry();		
		try {
			BeanUtils.populate(loanEntry, request.getParameterMap());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		ILoanEntryService service = new LoanEntryService();

		try {
			service.add(loanEntry);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String json = gson.toJson(loanEntry);
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
