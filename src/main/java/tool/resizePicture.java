package tool;

import net.coobird.thumbnailator.Thumbnails;

import java.io.IOException;

public class resizePicture {
    /**
     * 将图片变化指定大小后存入指定位置
     * forceSize为是否强制变化图片大小
     * @param srcPath String
     * @param destPath String
     * @param newHeight int
     * @param newWith int
     * @param forceSize boolean
     * @return boolean
     * **/
    public  boolean resizeImage (String srcPath, String destPath, int newWith, int newHeight, boolean forceSize) throws IOException {
        if (forceSize) {
            Thumbnails.of(srcPath).forceSize(newWith, newHeight).toFile(destPath);
        } else {
            Thumbnails.of(srcPath).width(newWith).height(newHeight).toFile(destPath);
        }
        return true;
    }

}
