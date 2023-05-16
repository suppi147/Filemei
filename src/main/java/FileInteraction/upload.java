package FileInteraction;

import FileController.FileController;
import FileController.Multifile;


import javax.servlet.http.HttpServlet;
import java.io.*;

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
        Multifile multifiler= new Multifile();
        PrintWriter out= response.getWriter();
        if(multifiler.MultifileUpload(request.getParts(),out)){
            request.setAttribute("filename", multifiler.GetDownloadFilename());
					getServletContext().getRequestDispatcher(FileController.downloadPath).forward(request, response);
        }
        else{
            response.sendRedirect(FileController.homeLink);
            return;
        }  
	  }
}
