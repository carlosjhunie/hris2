package dai.hris.action.reports;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.InputStream;

import org.apache.log4j.Logger;

import dai.hris.dao.DBConstants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;

@WebServlet("/ViewEmployeeAttendanceSummaryReport")
public class ViewEmployeeAttendanceSummaryReport extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(ViewEmployeeAttendanceSummaryReport.class);
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewEmployeeAttendanceSummaryReport() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost( request,  response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String dateFrom = request.getParameter("dateFrom");
		String dateTo = request.getParameter("dateTo");
		
		InputStream reportStream = null; 
		//http://localhost:8080/hris/ViewOvertimeSummaryReport?dateFrom=01-01-2015&dateTo=10-10-2015
		reportStream = getClass().getClassLoader().getResourceAsStream( "dai/hris/reports/EmployeeAttendanceSummary.jasper");
		Connection connection;
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(reportStream == null){
			logger.debug("reportStream is NULL");
		}

		
		map.put("dateFrom", dateFrom + " 00:59:59 AM");
		map.put("dateTo", dateTo + " 11:59:59 PM");
		

		response.setContentType("application/pdf");
		
		
		try {
			Class.forName(DBConstants.DB_DRIVER_CLASS_NAME);
			connection = DriverManager.getConnection(DBConstants.MS_SQL_DB_URL); 			
			JasperRunManager.runReportToPdfStream(reportStream,	response.getOutputStream(), map, connection);
			connection.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
