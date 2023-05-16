package FileInteraction;
 
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import FileController.Multifile;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "download", urlPatterns = { "/download" })
public class download extends HttpServlet {
 
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // reads input file from an absolute path
		Multifile multifiler= new Multifile();
        String filename = request.getParameter("filename");
		multifiler.MultifileDownload(response, filename, filename);
    }
}