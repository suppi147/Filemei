package FileController;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.Part;

public class FIlemeiZipper extends FileController {
    List<String> filesToZip;
    String zipfilename;
    public FIlemeiZipper(){
        this.filesToZip=new ArrayList<String>();
        this.zipfilename="/"+UUID.randomUUID().toString()+".zip";
    }
    public void Zipper(Collection<Part> fileContainers) throws IOException{
        if(!fileContainers.isEmpty()){
            for (Part FileDock : fileContainers) {
                if(FileDock!=null){
                    super.extractFileName(FileDock);
                    super.FindAbsolutePath();
                    filesToZip.add(this.absolutePath);
                }
            }
            FileOutputStream fos = null;
            ZipOutputStream zipOut = null;
            FileInputStream fis = null;
            try {
                fos = new FileOutputStream(defaultPath+zipfilename);
                zipOut = new ZipOutputStream(new BufferedOutputStream(fos));
                for(String filePath:filesToZip){
                    File input = new File(filePath);
                    fis = new FileInputStream(input);
                    ZipEntry ze = new ZipEntry(input.getName());
                    zipOut.putNextEntry(ze);
                    byte[] tmp = new byte[4*1024];
                    int size = 0;
                    while((size = fis.read(tmp)) != -1){
                        zipOut.write(tmp, 0, size);
                    }
                    zipOut.flush();
                    fis.close();
                }
                zipOut.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally{
                try{
                    if(fos != null) fos.close();
                } catch(Exception ex){
                    
                }
            }
        
        }
    }
}
