package FileInteraction;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import FileSecurity.FIlemeiSizeout;


@WebServlet(urlPatterns = { "/" })
public class LimitMiddleware extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        FIlemeiSizeout uploadSizer = new FIlemeiSizeout();
        if (uploadSizer.FileSizeLimitter()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            response.sendRedirect("home/"); 
        }

    }
}