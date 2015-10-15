package dai.hris.action.reports;


import java.io.File;
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

/**
 * Servlet implementation class Counter
 */
@WebServlet("/ViewPayrollRegisterReport")
public class ViewPayrollRegisterReport extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(ViewPayrollRegisterReport.class);
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewPayrollRegisterReport() {
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
		
		String payrollPeriodId = request.getParameter("payrollPeriodId");
		
		String imageSrc = request.getServletContext().getRealPath(File.separator) + ReportConstant.COMPANY_LOGO_URL;
		
		
		InputStream reportStream = null; 
		
		reportStream = getClass().getClassLoader().getResourceAsStream( "dai/hris/reports/PayrollRegister.jasper");
		
		/**
		 * TODO
		 * set also the name of creator and the approver of payroll register
		 */
		Connection connection;
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(reportStream == null){
			logger.debug("reportStream is NULL");
		}

		
		map.put("IMAGE_SRC", imageSrc);
		map.put("HOSPITAL_NAME", ReportConstant.COMPANY_NAME);
		map.put("payrollPeriodId", Integer.parseInt(payrollPeriodId));
		

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
