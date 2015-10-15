package dai.hris.action.kiosk;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dai.hris.model.DisplayKioskTimeEntry;

import com.google.gson.Gson;

import dai.hris.service.timeentry.TimeEntryService;
@WebServlet("/DisplayKioskTimeEntryAction")
public class DisplayKioskTimeEntryAction extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	Gson gson = new Gson();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String sn = request.getParameter("sn");
		DisplayKioskTimeEntry display = new DisplayKioskTimeEntry();
		TimeEntryService service = new TimeEntryService();

		//get latest from biometric by SN(Serial Number)
		
		try {
			service.displayKioskTimeEntry(sn,display);
		}
		catch(Exception e) {
			display.setErrorMessage(e.getMessage());
			e.printStackTrace();
		}

		ServletOutputStream out = response.getOutputStream();
		response.setContentType("application/json");
		String jsonObject = gson.toJson(display);
		out.print(jsonObject);
		out.close();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		doGet( request,  response);
	}

}
