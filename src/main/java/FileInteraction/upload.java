package FileInteraction;

import javax.servlet.http.HttpServlet;
import java.io.*;
import java.util.Collection;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "upload", urlPatterns = { "/upload" })
@MultipartConfig(
  maxFileSize = 1024 * 1024 * 1024,      // 1 GB
  maxRequestSize = 1024 * 1024 * 1024   // 1 GB
)

/**
 * Servlet implementation class FilemeiUpload
 */
public class upload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/* Receive file uploaded to the Servlet from the HTML5 form */
        
		try {
			for (Part part : request.getParts()) {
                
				String fileName = extractFileName(part);
				// refines the fileName in case it is an absolute path
				fileName = new File(fileName).getName();
				if(!fileName.isEmpty()){
					part.write(this.getFolderUpload().getAbsolutePath() + File.separator + fileName);
					response.getWriter().print("upload success.");
					request.setAttribute("filename", fileName);
					getServletContext().getRequestDispatcher("/downloads/index.jsp").forward(request, response);
				}
                else{
                    response.sendRedirect("http://localhost:8080/filemei-1.0/home/");
                    return;
                }
			  }
		} catch (Exception e) {
			response.getWriter().print("upload failed.");
		}
		
		  
	  }
	  private String extractFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
		  if (s.trim().startsWith("filename")) {
			return s.substring(s.indexOf("=") + 2, s.length() - 1);
		  }
		}
		return "";
	  }
	  public File getFolderUpload() {
		File folderUpload = new File("/var/lib/tomcat9/webapps/Uploads");
		if (!folderUpload.exists()) {
		  folderUpload.mkdirs();
		  
		}
		return folderUpload;
	  }
}
