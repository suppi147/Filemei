package FileController;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.http.HttpServletResponse;

public class FilemeiDownload extends FileController{
    public void Download(HttpServletResponse response,String mimeType) throws IOException{
        File[] files = new File(FileController.defaultPath).listFiles();
        if(files.length!=0){
            for (File file : files) {
                if (file.isFile()) {
                    if(this.filename.equals(file.getName())){
                        continue;
                    }
                    else{
                        response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    }
                }
                else{
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);

                }
            }
        }
        else{
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
        if(!this.filename.isEmpty()){
          this.FindAbsolutePath();
            downloadFile=new File(this.absolutePath);
           
  
              // modifies response
                response.setContentType(mimeType);
                response.setContentLength((int) downloadFile.length());
                FileInputStream InStream = new FileInputStream(downloadFile);
              if (mimeType == null) {        
                // set to binary type if MIME mapping not found
                mimeType = "application/octet-stream";
              }
              
          String headerKey = "Content-Disposition";
          String headerValue = String.format("attachment; filename="+filename);
          response.setHeader(headerKey, headerValue);
          
          // obtains response's output stream
  
          
          byte[] buffer = new byte[4096];
          int bytesRead = -1;
          
          OutputStream OutStream = response.getOutputStream();
          if(InStream!=null&&OutStream!=null){
            while ((bytesRead = InStream.read(buffer)) != -1) {
              OutStream.write(buffer, 0, bytesRead);
            }
            InStream.close();
            OutStream.close();
          }
          else{
            response.sendRedirect(FileController.notFoundLink);
          }
        }
        else{
          response.sendRedirect(FileController.notFoundLink);
        }
      }
}
