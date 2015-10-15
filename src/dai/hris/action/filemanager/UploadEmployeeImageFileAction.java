package dai.hris.action.filemanager;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;

import dai.hris.service.payroll.PayrollConstants;

@WebServlet("/UploadEmployeeImageFileAction")
public class UploadEmployeeImageFileAction extends HttpServlet {
	private Gson gson = new Gson();
	private static final long serialVersionUID = 1L;
	 /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     * 
     */
 @Override
 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

     
     if (request.getParameter("getfile") != null && !request.getParameter("getfile").isEmpty()) {
         File file = new File(request.getServletContext().getRealPath("/")+"imge/"+request.getParameter("getfile"));
         if (file.exists()) {
             int bytes = 0;
             ServletOutputStream op = response.getOutputStream();

          

             byte[] bbuf = new byte[1024];
             DataInputStream in = new DataInputStream(new FileInputStream(file));

             while ((in != null) && ((bytes = in.read(bbuf)) != -1)) {
                 op.write(bbuf, 0, bytes);
             }

             in.close();
             op.flush();
             op.close();
         }
     } else if (request.getParameter("delfile") != null && !request.getParameter("delfile").isEmpty()) {
         File file = new File(request.getServletContext().getRealPath("/")+"imge/"+ request.getParameter("delfile"));
         if (file.exists()) {
             file.delete(); // TODO:check and report success
         } 
     } else {
         PrintWriter writer = response.getWriter();
         writer.write("call POST with multipart form data");
     }
 }
 
 /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     * 
     */
 @SuppressWarnings("unchecked")
 @Override
 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 int empId = Integer.parseInt(request.getParameter("empId"));
	 
	 
	 String jsonString = null;
     if (!ServletFileUpload.isMultipartContent(request)) {
         throw new IllegalArgumentException("Request is not multipart, please 'multipart/form-data' enctype for your form.");
     }

     ServletFileUpload uploadHandler = new ServletFileUpload(new DiskFileItemFactory(DiskFileItemFactory.DEFAULT_SIZE_THRESHOLD, new File(PayrollConstants.picLocDirectory)));
     
     response.setContentType("application/json");
     
 	 //HttpSession session = request.getSession(true);
 	 //String departmentId = session.getAttribute("departmentId").toString();
     
     
 	// String fileName = "department"+ departmentId + ".mp4";
     String fileName = null;
     try {
         List<FileItem> items = uploadHandler.parseRequest(request);
         for (FileItem item : items) {
             if (!item.isFormField()) {
                     //File file = new File(request.getServletContext().getRealPath("/")+"imge/"+departmentId, item.getName());
                     //File fileDirecotry = new File(request.getServletContext().getRealPath("/")+"imge/"+departmentId);
            	 
        	 		 System.out.println(item.getName());
                     //System.out.println(request.getServletContext().getRealPath("/")+"employeeImages/");
            	 
            	 
            	 	 //uploads to here:  workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\hris\employeeImages
                     //File file = new File(request.getServletContext().getRealPath("/")+"employeeImages/", item.getName());  
                     if (StringUtils.isEmpty(item.getName())){
                    	 throw new FileUploadException("No image file specified!");
                     }
                     
                     
                     fileName = empId + "." + FilenameUtils.getExtension(item.getName());
                     File file = new File(PayrollConstants.picLocDirectory, fileName);  //FilenameUtils.getExtension(filename)
                     File fileDirecotry = new File(PayrollConstants.picLocDirectory);
                     
                     if(!fileDirecotry.exists()) {
                    	 fileDirecotry.mkdir();
                     }
                     
                     item.write(file);
                     
                     Map<String, Object> jsono = new HashMap<String, Object>();
                     jsono.put("name", fileName);
                     jsono.put("size", item.getSize());
                     jsono.put("uploaded", "OK");
                     
                     gson = new Gson();
                     jsonString = gson.toJson(jsono);
                     System.out.println(jsonString);
                     

             }
         }
     } catch (FileUploadException e) {
             throw new RuntimeException(e);
     } catch (Exception e) {
             throw new RuntimeException(e);
     } finally {
 		response.getWriter().print(jsonString);
     }

 }



}
