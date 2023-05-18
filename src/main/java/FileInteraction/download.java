package FileInteraction;

import java.io.IOException;
import FileController.Multifile;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import FileSecurity.FIlemeiSizeout;
import FileSecurity.FilemeiObfuscation;
import FileSecurity.FilemeiTimeout;


@WebServlet(name = "download", urlPatterns = { "/download" })
public class download extends HttpServlet {
 
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // reads input file from an absolute path
		Multifile multifiler= new Multifile();
		
		FilemeiTimeout timeLimitControl=new FilemeiTimeout();
        timeLimitControl.RemoveFileByLimit(response);
        
		String encryptBlock = request.getParameter("filename");
		String filename = FilemeiObfuscation.decrypt(encryptBlock);


		ServletContext context = getServletContext();
		String mimeType = context.getMimeType(filename);
		multifiler.MultifileDownload(response, mimeType, filename);
    }
}