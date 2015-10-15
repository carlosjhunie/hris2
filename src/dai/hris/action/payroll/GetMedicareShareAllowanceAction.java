package dai.hris.action.payroll;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dai.hris.model.MedicareShareAllowance;
import dai.hris.service.payroll.IMedicareShareAllowanceService;
import dai.hris.service.payroll.impl.MedicareShareAllowanceService;

/**
 * Servlet implementation class GetMedicareShareAllowanceAction
 */
@WebServlet("/GetMedicareShareAllowanceAction")
public class GetMedicareShareAllowanceAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	IMedicareShareAllowanceService svc = new MedicareShareAllowanceService();
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
		try {
			List<MedicareShareAllowance> msaList = svc.getAllByEmployeeId(empId);
			String json = gson.toJson(msaList);
            System.out.println("json: " + json);
			String jsonData = "{\"Result\":\"OK\",\"Record\":" + json + "}";
			response.getWriter().print(jsonData);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
