package FileInteraction;

import FileController.FileController;
import FileController.Multifile;
import javax.servlet.http.HttpServlet;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import FileSecurity.FIlemeiSizeout;
import FileSecurity.FilemeiObfuscation;
import FileSecurity.FilemeiTimeout;
import FileSecurity.SecurityController;

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
        
        FilemeiTimeout timeLimitControl=new FilemeiTimeout();
        timeLimitControl.RemoveFileByLimit(response);

        FIlemeiSizeout uploadSizer= new FIlemeiSizeout();
        if(uploadSizer.FileSizeLimitter()){
          response.sendError(HttpServletResponse.SC_BAD_REQUEST);
          return;

        }

        if(multifiler.MultifileUpload(request.getParts(),response)){
          String ecryptBlock= FilemeiObfuscation.encrypt(multifiler.GetDownloadFilename());
            request.setAttribute("filename", ecryptBlock);
					  getServletContext().getRequestDispatcher(FileController.downloadPath).forward(request, response);
        }
        else{
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }  
	  }
}
