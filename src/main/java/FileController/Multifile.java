package FileController;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import javax.servlet.http.*;

public class Multifile {
    protected ArrayList<String> filenamelist;
    protected int numberOfFile;
    protected Part oneFileDock;
    FilemeiDownload download;
    FilemeiUpload upload;
    
    public Multifile(){
        this.numberOfFile=0;
        this.filenamelist=new ArrayList<String>();
        this.oneFileDock=null;
        this.download=new FilemeiDownload();
        this.upload=new FilemeiUpload();
    }
    public String GetDownloadFilename(){
        return this.upload.GetFilename();
    }

    public boolean MultifileUpload(Collection<Part> fileContainers,PrintWriter out){        
        if(!fileContainers.isEmpty()){
            for (Part fileDock : fileContainers) {
                upload.extractFileName(fileDock);
                if(!(upload.GetFilename().isEmpty()))
                    numberOfFile++;
                else 
                    break;
            }
            if(numberOfFile>0){
                for (Part FileDock : fileContainers) {
                    if(FileDock!=null){
                        upload.extractFileName(FileDock);
                        upload.Upload(FileDock);        
                    }
                }
                return true;
            }     
        }
        return false;
    }
    public void MultifileDownload(HttpServletResponse response,String mimeType, String filename){
        download.SetFilename(filename);
        try {
            download.Download(response, mimeType);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
