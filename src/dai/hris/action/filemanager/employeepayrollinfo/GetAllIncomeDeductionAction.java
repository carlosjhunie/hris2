package dai.hris.action.filemanager.employeepayrollinfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dai.hris.model.Deduction;
import dai.hris.model.GenericDisplayOptionModel;
import dai.hris.model.Income;

import com.google.gson.Gson;

import dai.hris.service.payroll.IDeductionService;
import dai.hris.service.payroll.IIncomeService;
import dai.hris.service.payroll.impl.DeductionService;
import dai.hris.service.payroll.impl.IncomeService;

@WebServlet("/GetAllIncomeDeductionAction")
public class GetAllIncomeDeductionAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IIncomeService incomeService = new IncomeService();
	IDeductionService deductionService = new DeductionService();
	Gson gson = new Gson();
	
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
			doPost(request, response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String displayOption = request.getParameter("displayOption");
		String pInfoType = request.getParameter("pInfoType");
		

		List<Income> incomeList = null;
		List<Deduction> deductionList = null;
		ArrayList <GenericDisplayOptionModel> genericDisplayOptionList = null;
		
		if ("income".equalsIgnoreCase(pInfoType)) {
			try {
				incomeList = incomeService.getAll();
			}
			catch(Exception e ){
				e.printStackTrace();
			}
		}
		
		if ("deduction".equalsIgnoreCase(pInfoType)) {		
			try {
				deductionList = deductionService.getAll();
			}
			catch(Exception e ){
				e.printStackTrace();
			}
		}
		
		
		if ("true".equalsIgnoreCase(displayOption)) {	//if need to be displayed in jTable	
			genericDisplayOptionList = new ArrayList <GenericDisplayOptionModel>();
			if ("income".equalsIgnoreCase(pInfoType)) {
			    for (Income income: incomeList) {
			    	GenericDisplayOptionModel genericDisplayOptionModel = new GenericDisplayOptionModel();
			    	genericDisplayOptionModel.setValue(income.getIncomeId());
			    	genericDisplayOptionModel.setDisplayText(income.getIncomeName());
			    	genericDisplayOptionList.add(genericDisplayOptionModel);
			    }
			}
			
			if ("deduction".equalsIgnoreCase(pInfoType)) {
			    for (Deduction deduction: deductionList) {
			    	GenericDisplayOptionModel genericDisplayOptionModel = new GenericDisplayOptionModel();
			    	genericDisplayOptionModel.setValue(deduction.getDeductionId());
			    	genericDisplayOptionModel.setDisplayText(deduction.getDeductionName());
			    	genericDisplayOptionList.add(genericDisplayOptionModel);
			    }
			}
			
			String json = gson.toJson(genericDisplayOptionList);
		    System.out.println("json: " + json);
	 
		    json = "{\"Result\":\"OK\",\"Options\":"+ json + "}";   ///note the change from Records to Options
	        response.getWriter().print(json);
		
		} else {
			String json = null;
			if ("income".equalsIgnoreCase(pInfoType)) {
				json = gson.toJson(incomeList);
			}
			if ("deduction".equalsIgnoreCase(pInfoType)) {
				json = gson.toJson(deductionList);
			}
		    
		    System.out.println("json: " + json);
	 
		    json = "{\"Result\":\"OK\",\"Records\":"+ json + "}";
	        response.getWriter().print(json);
		}
	}
	

}
