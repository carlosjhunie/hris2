package dai.hris.action.payroll;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dai.hris.model.ProfessionalFee;
import dai.hris.service.payroll.IProfessionalFeeService;
import dai.hris.service.payroll.impl.ProfessionalFeeService;

/**
 * Servlet implementation class GetProfessionalFeeAction
 */
@WebServlet("/GetProfessionalFeeAction")
public class GetProfessionalFeeAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	IProfessionalFeeService svc = new ProfessionalFeeService();
	Gson gson = new Gson();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int empId = Integer.parseInt(request.getParameter("empId"));
		String sFromOrDate = request.getParameter("fromOrDate");
		String sToOrDate = request.getParameter("toOrDate");
		try {
			List<ProfessionalFee> pfList = null;
			if (sFromOrDate == null || "".equalsIgnoreCase(sFromOrDate)) {
				pfList = svc.getAllByEmployeeId(empId);
			} else {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				Date from = new Date(formatter.parse(sFromOrDate).getTime());
				Date to = null;
				if (sToOrDate == null || sToOrDate.isEmpty()) {
					to = new Date(System.currentTimeMillis());
				} else {
					to = (Date) formatter.parse(sToOrDate);
				}
				pfList = svc.getAllByEmpIdAndOrDateRange(empId, from, to);
			}
			String json = gson.toJson(pfList);
            System.out.println("json: " + json);
			String jsonData = "{\"Result\":\"OK\",\"Records\":" + json + "}";
			response.getWriter().print(jsonData);
		} catch (SQLException e) {
			e.printStackTrace();
			String error = "{\"Result\":\"ERROR\"}";
			 response.getWriter().print(error);
		} catch (ParseException e) {
			e.printStackTrace();
			String error = "{\"Result\":\"ERROR\",\"Message\":\""+e.getMessage()+" (Date format should be yyyy-MM-dd)\"}";
			 response.getWriter().print(error);
		}catch (Exception e) {
			e.printStackTrace();
			String error = "{\"Result\":\"ERROR\"}";
			 response.getWriter().print(error);
		}
	}

}
