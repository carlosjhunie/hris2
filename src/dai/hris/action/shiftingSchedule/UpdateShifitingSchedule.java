package dai.hris.action.shiftingSchedule;

import java.io.IOException;



import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.google.gson.Gson;





import dai.hris.model.ShiftingSchedule;
import dai.hris.services.shiftingSchedule.IShiftingScheduleService;
import dai.hris.services.shiftingSchedule.ShiftingScheduleService;

@WebServlet("/UpdateShifitingSchedule")
public class UpdateShifitingSchedule extends HttpServlet {

	private static final long serialVersionUID = 1L;
	Gson gson = new Gson();
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	
		ShiftingSchedule model = new ShiftingSchedule();		
		try {
			BeanUtils.populate(model, request.getParameterMap());
			
//			Date s = new SimpleDateFormat("HH:mm").parse(model.getTimeIn());
//			System.out.print(new SimpleDateFormat("HH:mm").format(s));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		


		IShiftingScheduleService service = new ShiftingScheduleService();

		try {
			service.update(model);
			response.getWriter().print("{\"Result\":\"OK\"}");
		} catch (Exception e) {
			e.printStackTrace();
			String error = "{\"Result\":\"ERROR\"}";
			response.getWriter().print(error);
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
