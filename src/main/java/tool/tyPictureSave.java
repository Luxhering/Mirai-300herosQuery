package tool;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class tyPictureSave {
    /**
     * 将传入的输入流（300官网的头像图片），保存到300headPicture下
     * @param inputStream InputStream
     * @param name String
     * @return flag boolean
     * **/
    public boolean downloadImg(InputStream inputStream,String name) throws IOException {
        boolean flag = true;
        File file = new File(name);
        if (file.exists()){
            return flag;
        }
        File fileParent = file.getParentFile();
        if (!fileParent.exists()){
            fileParent.mkdirs();//创建路径
        }
        try {
            FileUtils.copyToFile(inputStream,file);
        }catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }
}
