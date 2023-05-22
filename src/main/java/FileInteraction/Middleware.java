package FileInteraction;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import FileSecurity.FIlemeiSizeout;
import FileSecurity.FilemeiTimeout;


@WebServlet(urlPatterns = { "/" })
public class Middleware extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                
        FilemeiTimeout timeLimitControl=new FilemeiTimeout();
        timeLimitControl.RemoveFileByLimit(response);

        FIlemeiSizeout uploadSizer = new FIlemeiSizeout();
        if (uploadSizer.FileSizeLimitter()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            response.sendRedirect("home/"); 
        }

    }
}