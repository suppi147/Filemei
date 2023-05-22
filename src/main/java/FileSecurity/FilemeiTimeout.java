package FileSecurity;
import java.io.File;

import javax.servlet.http.HttpServletResponse;

import FileController.FileController;

public class FilemeiTimeout implements SecurityController {
    private static final int CROSSTIME=20;
    private String filepath;
    private File deletefile;
    private long lastModified;
    private long now;
    private long timeDifferent;
    private long mins;
    public FilemeiTimeout(){
        this.filepath=FileController.defaultPath+"/";
        this.deletefile=null;
        this.lastModified=0;
        this.now=0;
        this.timeDifferent=0;
        this.mins=0;
    }


    public void RemoveFileByLimit(HttpServletResponse response){
        try {
            File[] files = new File(SecurityController.defaultPath).listFiles();
            for (File file : files) {
                if (file.isFile()) {
                    this.filepath+=file.getName();                    
                    this.lastModified=file.lastModified();
                    this.now = System.currentTimeMillis();
                    this.timeDifferent= now -lastModified;
                    this.mins=this.timeDifferent/(1000*60);
                    this.deletefile = new File(this.filepath);
                    if(this.mins>CROSSTIME){    
                        this.deletefile.delete();
                    }
                    this.filepath=SecurityController.defaultPath+"/";
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
