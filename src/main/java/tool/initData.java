package tool;

import kotlin.reflect.jvm.internal.calls.CallerImpl;

import java.io.File;

public class initData {
    static String answer = "山有木兮木有枝，心悦君兮君不知。";
    static String bind = "{\"车头战绩\":\"初心ˇleader\",\"六花战绩\":\"六花ˇEvileye\",\"小沫战绩\": \"优子ˇMomoka\",\"索尼克战绩\": \"神棍ˇSonic\",\"蒋哥战绩\": \"生死看淡大茜雅\"}";
    static String Sensitive = "nmsl";
    static String ZuAn = "nmsl";
    static String path = "./data/Mirai-300query/300ICUData/";
    static String answerName = "Answer.txt";
    static String bindName = "Bind.json";
    static String SensitiveName = "Sensitive.txt";
    static String ZuAnName = "ZuAn.txt";
    public static void init(){
        dataBind dataBind = new dataBind();
        File answerFile = new File(path+answerName);
        File bindFile = new File(path+bindName);
        File SensitiveFile = new File(path+SensitiveName);
        File ZuAnFile = new File(path+ZuAnName);
        if(!answerFile.exists()){
            dataBind.saveDataToFile(path+answerName,answer);
            System.out.println("初始化answerFile成功！");
        }
        if (!bindFile.exists()){
            dataBind.saveDataToFile(path+bindName,bind);
            System.out.println("初始化bindFile成功！");
        }
        if (!SensitiveFile.exists()){
            dataBind.saveDataToFile(path+SensitiveName,Sensitive);
            System.out.println("初始化SensitiveFile成功！");
        }
        if (!ZuAnFile.exists()){
            dataBind.saveDataToFile(path+ZuAnName,ZuAn);
            System.out.println("初始化ZuAnFile成功！");
        }
    }
}
