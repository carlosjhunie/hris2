package dai.hris.action.shiftingSchedule;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dai.hris.model.ShiftingSchedule;
import dai.hris.services.shiftingSchedule.IShiftingScheduleService;
import dai.hris.services.shiftingSchedule.ShiftingScheduleService;

@WebServlet("/GetAllShiftingSchedule")
public class GetAllShiftingSchedule extends HttpServlet {

	private static final long serialVersionUID = 1L;
	Gson gson = new Gson();
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	
		
		IShiftingScheduleService service = new ShiftingScheduleService();
		ArrayList<ShiftingSchedule> list = null;
		try {
			list = service.getAll();
		}
		catch(Exception e ){
			e.printStackTrace();
		}
	

	    String json = gson.toJson(list);
	    System.out.println("json: " + json);
 
	    json = "{\"Result\":\"OK\",\"Records\":"+ json + "}";
        response.getWriter().print(json);

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
