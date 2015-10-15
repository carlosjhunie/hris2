package dai.hris.action.filemanager;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dai.hris.model.Employee;
import dai.hris.model.GenericDisplayOptionModel;
import dai.hris.model.LoanType;
import dai.hris.service.filemanager.loantype.LoanTypeService;
import dai.hris.service.filemanager.loantype.ILoanTypeService;

@WebServlet("/GetAllLoanTypeAction")
public class GetAllLoanTypeAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Gson gson = new Gson();
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String displayOption = request.getParameter("displayOption");		
		ArrayList <GenericDisplayOptionModel> genericDisplayOptionList = null;
		

	
		
		ILoanTypeService service = new LoanTypeService();
		ArrayList<LoanType> list = null;
		try {
			list = service.getAll();
		}
		catch(Exception e ){
			e.printStackTrace();
		}

		if ("true".equalsIgnoreCase(displayOption)) {	
			//if need to be displayed in jTable,use generic model
			genericDisplayOptionList = new ArrayList <GenericDisplayOptionModel>() ;
				for (LoanType loanType:list){				
		    	GenericDisplayOptionModel genericDisplayOptionModel = new GenericDisplayOptionModel();
		    	genericDisplayOptionModel.setValue(loanType.getLoanTypeId());
		    	genericDisplayOptionModel.setDisplayText(loanType.getLoanTypeName());
		    	genericDisplayOptionList.add(genericDisplayOptionModel);
		    	
				}
			
			String json = gson.toJson(genericDisplayOptionList);
		    System.out.println("json: " + json);
	 
		    json = "{\"Result\":\"OK\",\"Options\":"+ json + "}";   ///note the change from Records to Option
	        response.getWriter().print(json);
		
		}
		else {
		    String json = gson.toJson(list);
		    System.out.println("json: " + json);
	 
		    json = "{\"Result\":\"OK\",\"Records\":"+ json + "}";
	        response.getWriter().print(json);
		}
	



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
