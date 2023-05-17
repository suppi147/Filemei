package FileController;
import java.io.IOException;

import javax.servlet.http.Part;

public class FilemeiUpload extends FileController{
    public boolean Upload(Part part){
        if(!super.filename.isEmpty()){
            this.FindAbsolutePath();
          try {
              part.write(super.absolutePath);
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
