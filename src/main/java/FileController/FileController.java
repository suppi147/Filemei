package FileController;
import java.io.File;
import java.io.IOException;

import javax.servlet.http.*;


public class FileController{

    public static final String defaultPath = "/var/lib/tomcat9/webapps/Uploads";
    public static final String homePath = "http://localhost:8080/filemei-1.0/home/";
    public static final String downloadPath = "/downloads/index.jsp";
    protected String filename;
    protected String absolutePath;
    protected String zipname;

    public FileController(){
        this.filename="";
        this.absolutePath="NULL PATH";
        this.zipname="";
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
}
    
