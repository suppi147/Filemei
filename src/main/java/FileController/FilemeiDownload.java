package FileController;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.http.HttpServletResponse;

public class FilemeiDownload extends FileController{
    public void Download(HttpServletResponse response,String mimeType) throws IOException{  
        if(!this.filename.isEmpty()){
          this.absolutePath=this.getFolderUpload().getAbsolutePath() + File.separator + this.filename;
            downloadFile=new File(absolutePath);
           
  
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
