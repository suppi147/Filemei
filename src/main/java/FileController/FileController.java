package FileController;
import java.io.File;
import javax.servlet.http.*;


public class FileController{

    public static final String defaultPath = "/var/lib/tomcat9/webapps/zRBtlJtqyyJnb3KvHjKO";
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

    public void FindAbsolutePath(){
      this.absolutePath=this.getFolderUpload().getAbsolutePath() + File.separator + this.filename;
    }
}
    
