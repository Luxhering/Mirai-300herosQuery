package tool;

import net.sf.json.JSONObject;

import java.io.*;

public class dataBind {
    /**
     * 存储对应表到300ICUData文件下,并同时承担
     * 对应表：为简化查询使用
     * @param fileName String
     * @param data String
     * **/
    public void saveDataToFile(String fileName,String data) {
        BufferedWriter writer = null;
        File file = new File(fileName);
        if(!file.exists()){
            try {
                File fileParent = file.getParentFile();
                if (!fileParent.exists()){
                    fileParent.mkdirs();//创建路径
                }
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,false), "UTF-8"));
            writer.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(writer != null){
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("写入成功");
    }
    public String getDatafromFile(String fileName) {

        String Path= fileName;
        BufferedReader reader = null;
        String laststr = "";
        try {
            FileInputStream fileInputStream = new FileInputStream(Path);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            reader = new BufferedReader(inputStreamReader);
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                laststr += tempString;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return laststr;
    }
}
