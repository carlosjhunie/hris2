package dai.hris.action.reports;


import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.net.URISyntaxException;
import java.net.URL;

import com.google.gson.Gson;

import dai.hris.model.Employee;
import dai.hris.dao.DBConstants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;



/**
 * Servlet implementation class Counter
 */
@WebServlet("/ExportEmployeeLeaveHistoryReport")
public class ExportEmployeeLeaveHistoryReport extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Gson gson = new Gson();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExportEmployeeLeaveHistoryReport() {
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
		
		String empId = "";
		if (null != request.getSession().getAttribute("employeeLoggedIn")) {
			empId = String.valueOf(((Employee)request.getSession().getAttribute("employeeLoggedIn")).getEmpId());
		} else {
			empId = request.getParameter("empId");
		}
		String dateFrom = request.getParameter("dateFrom");
		String dateTo = request.getParameter("dateTo");
		
		String imageSrc = request.getServletContext().getRealPath(File.separator) + ReportConstant.COMPANY_LOGO_URL;
		
		 
		Map<String, Object> map = new HashMap<String, Object>();		
		
			
		URL sourceFileName = getClass().getResource("/dai/hris/reports/EmployeeLeaveHistory.jasper");		

		String printFileName = null;			
		
		Connection connection;				
		
		map.put("IMAGE_SRC", imageSrc);
		map.put("HOSPITAL_NAME", ReportConstant.COMPANY_NAME);
		map.put("empId", empId);
		map.put("dateFrom", dateFrom + " 00:59:59 AM");
		map.put("dateTo", dateTo + " 11:59:59 PM");
		

		response.setContentType("application/pdf");
		
		
		try {			
			
			Class.forName(DBConstants.DB_DRIVER_CLASS_NAME);
			connection = DriverManager.getConnection(DBConstants.MS_SQL_DB_URL); 			
			
			printFileName = JasperFillManager.fillReportToFile(sourceFileName.toURI().getPath(),
		            map, connection);
			
			new File("C://reports//").mkdirs();	
			
			if (printFileName != null) {
				JRXlsxExporter exporter = new JRXlsxExporter();

	            exporter.setParameter(JRExporterParameter.INPUT_FILE_NAME,
	                  printFileName);
	            exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
	                  "C://reports//leave_history_report.xls");

	            exporter.exportReport();
			}
			
			connection.close();
			
			ServletOutputStream out = response.getOutputStream();
			response.setContentType("application/json");	
			String jsonObject = gson.toJson("SUCCESS");
			out.print(jsonObject);
			out.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
