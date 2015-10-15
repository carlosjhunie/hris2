/**
 * 
 */
package dai.hris.action.filemanager;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dai.hris.model.JobTitle;
import dai.hris.service.filemanager.jobtitle.JobTitleService;
import dai.hris.service.filemanager.jobtitle.IJobTitleService;


/**
 * @author rj
 *
 */
@WebServlet("/UpdateJobTitleAction")
public class UpdateJobTitleAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Gson gson = new Gson();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		JobTitle jobTitle = new JobTitle();
		
		jobTitle.setJobTitleId(Integer.parseInt(request.getParameter("jobTitleId")));
		jobTitle.setJobTitle(request.getParameter("jobTitle"));

		IJobTitleService service = new JobTitleService();
		
		try {
			service.update(jobTitle);
			response.getWriter().print("{\"Result\":\"OK\"}");
		}
		catch(Exception e ){
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
