package FileSecurity;

import java.io.File;

public class FIlemeiSizeout implements SecurityController {
    public static final long maxSize= 1024 * 1024 * 1024;
    public static final long allMaxSize= 1024 * 1024 * 1024*10;
    protected String filepath;
    protected long allMaxFileSize;
    protected long deletefilesize;

    public FIlemeiSizeout(){
        this.filepath="";
        this.allMaxFileSize=0;
        this.deletefilesize=0;
    }

    public boolean FileSizeLimitter(){
        File[] files = new File(SecurityController.defaultPath).listFiles();
            for (File file : files) {
                if (file.isFile()) {
                    this.allMaxFileSize+= file.length();
                    this.deletefilesize=file.length()/maxSize;
                    if(this.deletefilesize>1){
                        file.delete();
                    }
                }                
            }
            if(this.allMaxFileSize>allMaxSize){
                return true;
            }
        return false;
    }
}
