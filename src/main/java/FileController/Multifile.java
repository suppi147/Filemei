package FileController;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import javax.servlet.http.*;

import FileSecurity.FilemeiTimeout;

public class Multifile {
    protected ArrayList<String> filenamelist;
    protected int numberOfFile;
    protected Part oneFileDock;
    FilemeiDownload download;
    FilemeiUpload upload;
    FIlemeiZipper zip;
    
    public Multifile(){
        this.numberOfFile=0;
        this.filenamelist=new ArrayList<String>();
        this.oneFileDock=null;
        this.download=new FilemeiDownload();
        this.upload=new FilemeiUpload();
        this.zip=new FIlemeiZipper();
    }
    public String GetDownloadFilename(){
        return this.upload.filename;
    }

    public boolean MultifileUpload(Collection<Part> fileContainers,HttpServletResponse response) throws IOException{        
        
        if(!fileContainers.isEmpty()){
            for (Part fileDock : fileContainers) {
                upload.extractFileName(fileDock);
                if(!(upload.filename.isEmpty()))
                {
                    numberOfFile++;
                }
                    
                else 
                    break;
            }
            if(numberOfFile==0)
                return false;
            for (Part FileDock : fileContainers) {
                if(FileDock!=null){
                        upload.extractFileName(FileDock);
                        upload.Upload(FileDock);        
                    }
                }
                if(numberOfFile>1){
                    try {
                        this.zip.Zipper(fileContainers);
                        this.upload.filename=this.zip.zipfilename;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }    
                return true;
                 
        }
        return false;
    }
    public void MultifileDownload(HttpServletResponse response,String mimeType, String filename){
        download.filename=filename;
        try {
            download.Download(response, mimeType);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
