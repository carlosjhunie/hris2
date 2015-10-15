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

import dai.hris.model.LoanType;
import dai.hris.service.filemanager.loantype.LoanTypeService;
import dai.hris.service.filemanager.loantype.ILoanTypeService;


/**
 * @author rj
 *
 */
@WebServlet("/AddLoanTypeAction")
public class AddLoanTypeAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Gson gson = new Gson();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		LoanType loanType = new LoanType();
		loanType.setLoanTypeName(request.getParameter("loanTypeName"));

		ILoanTypeService service = new LoanTypeService();

		try {
			service.add(loanType);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String json = gson.toJson(loanType);
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
