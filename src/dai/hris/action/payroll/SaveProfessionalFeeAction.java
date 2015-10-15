package dai.hris.action.payroll;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dai.hris.action.filemanager.util.EmployeeUtil;
import dai.hris.model.ProfessionalFee;
import dai.hris.service.payroll.IProfessionalFeeService;
import dai.hris.service.payroll.impl.ProfessionalFeeService;

/**
 * Servlet implementation class AddProfessionalPeriodAction
 */
@WebServlet("/SaveProfessionalFeeAction")
public class SaveProfessionalFeeAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private IProfessionalFeeService service = new ProfessionalFeeService();
	private Gson gson = new Gson();
       
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String json_param = "";
        if(br != null){
            json_param = br.readLine();
        }
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> param = mapper.readValue(json_param, Map.class);*/
        
		int empId = Integer.parseInt(request.getParameter("empId"));
		String officialReceiptNumber = request.getParameter("officialReceiptNumber");
		java.sql.Date officialReceiptDate = new EmployeeUtil().convertToSqlDate(request.getParameter("officialReceiptDate"));
		BigDecimal grossAmount = new BigDecimal(request.getParameter("grossAmount"));
		BigDecimal withHoldingTax = new BigDecimal(request.getParameter("withHoldingTax"));
		BigDecimal finalTax = new BigDecimal(request.getParameter("finalTax"));
		BigDecimal netAmountDue = new BigDecimal(request.getParameter("netAmountDue"));
		String patientId = request.getParameter("patientId");
		String patientName = request.getParameter("patientName");
		String remarks = request.getParameter("remarks");
        
        try {
        	ProfessionalFee pf = new ProfessionalFee();
        	pf.setOfficialReceiptNumber(officialReceiptNumber);
        	pf.setOfficialReceiptDate(officialReceiptDate);
        	pf.setGrossAmount(grossAmount);
        	pf.setWithHoldingTax(withHoldingTax);
        	pf.setFinalTax(finalTax);
        	pf.setNetAmountDue(netAmountDue);
        	pf.setPatientId(patientId);
			pf.setPatientName(patientName);
        	pf.setRemarks(remarks);
        	pf.setEmpId(empId);

        	service.saveOrUpdate(pf);
        	String json = gson.toJson(pf);
        	System.out.println("json: " + json);
        	String jsonData = "{\"Result\":\"OK\",\"Record\":" + json + "}";
        	response.getWriter().print(jsonData);
		} catch (SQLException e) {
			e.printStackTrace();
			String error = "{\"Result\":\"ERROR\",\"Message\":\"DB ERROR!\"}";
			 response.getWriter().print(error);
		} catch (Exception e) {
			e.printStackTrace();
			String error = "{\"Result\":\"ERROR\"}";
			 response.getWriter().print(error);
		}
	}
}
