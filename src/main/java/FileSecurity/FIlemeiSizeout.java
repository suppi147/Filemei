package FileSecurity;

import java.io.File;

public class FIlemeiSizeout implements SecurityController {
    public static final float maxSize= 1024 * 1024 * 1024;
    public static final float TOTAL_SIZE_LIMIT= 5;
    protected String filepath;
    protected float fileLenSum;
    

    public FIlemeiSizeout(){
        this.filepath="";
        this.fileLenSum=0;
    }

    public boolean FileSizeLimitter(){
        float diff=0;
        File[] files = new File(SecurityController.defaultPath).listFiles();
            for (File file : files) {
                if (file.isFile()) {
                    diff=file.length()/maxSize;
                    this.fileLenSum+= diff;
                    if(diff>1){
                        file.delete();
                    }
                }                
            }
            if(this.fileLenSum>TOTAL_SIZE_LIMIT){
                return true;
            }
        return false;
    }
}
