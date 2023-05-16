package FileController;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.servlet.http.*;
import javax.swing.text.html.HTMLDocument.Iterator;

public class Multifile extends FileController {
    protected ArrayList<String> filenamelist;
    protected int numberOfFile;
    protected Part oneFileDock;
    
    public Multifile(){
        this.numberOfFile=0;
        this.filenamelist=new ArrayList<String>();
        this.oneFileDock=null;
    }

    public boolean MultifileUpload(Collection<Part> fileContainers){
        
        if(!fileContainers.isEmpty()){
            for (Part fileDock : fileContainers) {
                super.extractFileName(fileDock);
                if(!(super.GetFilename().isEmpty()))
                    numberOfFile++;
                else 
                    break;
            }
            if(numberOfFile==1){
                this.oneFileDock= fileContainers.iterator().next();
                super.extractFileName(oneFileDock);
                super.FilemeiUpload(oneFileDock);
                return true;
            }
        }
        return false;
    }
}
