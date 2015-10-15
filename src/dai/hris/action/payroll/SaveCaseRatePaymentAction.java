package dai.hris.action.payroll;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import dai.hris.model.CaseRatePayment;
import dai.hris.service.payroll.impl.CaseRatePaymentService;

@WebServlet("/SaveCaseRatePaymentAction")
public class SaveCaseRatePaymentAction extends HttpServlet {
	private static final long serialVersionUID = 5398739944589430743L;

	private CaseRatePaymentService service = new CaseRatePaymentService();
	Gson gson = new Gson();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String json_param = "";
        if(br != null){
            json_param = br.readLine();
        }
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> param = mapper.readValue(json_param, Map.class);*/
        
		int empId = Integer.parseInt(request.getParameter("empId"));
		String sOfficialReceiptDate = request.getParameter("officialReceiptDate");
		BigDecimal grossAmount = new BigDecimal(request.getParameter("grossAmount"));
		BigDecimal withHoldingTax = new BigDecimal(request.getParameter("withHoldingTax"));
		BigDecimal finalTax = new BigDecimal(request.getParameter("finalTax"));
		BigDecimal netAmountDue = new BigDecimal(request.getParameter("netAmountDue"));
		int year = Integer.parseInt(request.getParameter("year"));
		int month = Integer.parseInt(request.getParameter("month"));
		String batch = request.getParameter("batch");
		String patientId = request.getParameter("patientId");
		String patientName = request.getParameter("patientName");
		String remarks = request.getParameter("remarks");
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date officialReceiptDate = new Date(sdf.parse(sOfficialReceiptDate).getTime());
			
			CaseRatePayment crp = new CaseRatePayment();
			crp.setOfficialReceiptDate(officialReceiptDate);
			crp.setGrossAmount(grossAmount);
			crp.setWithHoldingTax(withHoldingTax);
			crp.setFinalTax(finalTax);
			crp.setNetAmountDue(netAmountDue);
			crp.setYear(year);
			crp.setMonth(month);
			crp.setBatch(batch);
			crp.setPatientId(patientId);
			crp.setPatientName(patientName);
			crp.setRemarks(remarks);
			crp.setEmpId(empId);
			service.saveOrUpdate(crp);
			String json = gson.toJson(crp);
			System.out.println("json: " + json);
			String jsonData = "{\"Result\":\"OK\",\"Record\":" + json + "}";
			response.getWriter().print(jsonData);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 
}
