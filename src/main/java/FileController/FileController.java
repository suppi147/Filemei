package FileController;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.*;


public class FileController{

    public static final String defaultPath = "/var/lib/tomcat9/webapps/Uploads";
    public static final String downloadPath = "/downloads/index.jsp";
    public static final String homeLink = "http://localhost:8080/filemei-1.0/home/";
    public static final String notFoundLink = "http://localhost:8080/filemei-1.0/SomethingGoesWrong/404/";
    protected String filename;
    protected String absolutePath;
    protected String zipname;
    protected File downloadFile;


    public FileController(){
        this.filename="";
        this.absolutePath="NULL PATH";
        this.zipname="";
        this.downloadFile=null;
    }

    public String GetFilename(){
        return this.filename;
    }
    public String GetAbsolutePath(){
        return this.absolutePath;
    }
    public void SetFilename(String filename){
      this.filename=filename;
  }
  
    
    public void extractFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
		  if (s.trim().startsWith("filename")) {
			this.filename= s.substring(s.indexOf("=") + 2, s.length() - 1);
		  }
		}
	  }

	  public File getFolderUpload() {
		File folderUpload = new File(defaultPath);
		if (!folderUpload.exists()) {
		  folderUpload.mkdirs();
		}
		return folderUpload;
	  }

      public boolean FilemeiUpload(Part part){
        if(!this.filename.isEmpty()){
            this.absolutePath=this.getFolderUpload().getAbsolutePath() + File.separator + this.filename;
            try {
              part.write(this.absolutePath);
            } catch (IOException e) {
              e.printStackTrace();
            }
            return true;
        }
        else{    
            return false;
        }
      }

    public void FilemeiDownload(HttpServletResponse response,String mimeType) throws IOException{  
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
    
