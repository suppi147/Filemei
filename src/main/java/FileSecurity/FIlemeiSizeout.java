package FileSecurity;

import java.io.File;

public class FIlemeiSizeout implements SecurityController {
    public static final long maxSize= 1024 * 1024 * 1024;
    

    public void FileSizeLimitter(String filename){
        File deletefile = new File(SecurityController.defaultPath+"/"+filename);
        long deletefilesize=deletefile.length()/maxSize;
        if(deletefilesize>1){
            deletefile.delete();
        }     
    }
}
