package function;

import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.message.code.MiraiCode;
import net.mamoe.mirai.message.data.Message;
import net.mamoe.mirai.message.data.MessageChain;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import tool.dataBind;

import java.io.File;

public class qinYe {
    String path = "./data/Mirai-300query/300ICUData/";
    String pathData = "./data/Mirai-300query/300ICUData/";
    String fileForBind = "Bind.json";
    dataBind bind = new dataBind();
    String fileForZuan = "ZuAn.txt";
    String fileForAnswer = "Answer.txt";
    String fileFOrSensitive = "Sensitive.txt";
    String prx = "[mirai:at:746154378]";
    String sux = "[mirai:at:2504637063]";
    String command1 = "添加诗句：";
    String command2 = "添加祖安：";
    String command3 = "添加敏感词：";
    String command4 = "添加骚话：";
    dataBind dataBind = new dataBind();
    String sendID = "";
    MessageChain chain;
    int flag = 1;
    public void Fuck(GroupMessageEvent event){
        String str =event.getMessage().serializeToMiraiCode(); // 序列化
        sendID=getMemberid(event);
        if (str.contains(sux) && sendID.equals("746154378")) {
            if(str.contains(command3)){
                try {
                    int num = str.indexOf("：");
                    String word = str.substring(num+1);
                    setSensitiveWord(word);
                    event.getSource().getSubject().sendMessage("敏感词添加成功！");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (str.contains(command2)) {
                int num = str.indexOf("：");
                String word = str.substring(num+1);
                setZUAN(word);
                event.getSource().getSubject().sendMessage("祖安添加成功！");
            } else if(str.contains(command4)) {
                int num = str.indexOf("：");
                String word = str.substring(num+1);
                setAnswer(word);
                event.getSource().getSubject().sendMessage("骚话添加成功！");
            } else if(str.length()<22){
                event.getSource().getSubject().sendMessage("车头sama何故@我？给你整个乐子吧：");
                event.getSource().getSubject().sendMessage(getAnswer(sendID));
            }
        } else if(str.contains(sux)){
            if (getSensitiveWord(str)) {
                for (int i = 0; i < flag; i++) {
                    event.getSource().getSubject().sendMessage(getZUAN(sendID));
                }
            } else if(str.contains(command4)) {
                int num = str.indexOf("：");
                String word = str.substring(num+1);
                setAnswer(word);
                event.getSource().getSubject().sendMessage("骚话添加成功！");
            }
            else if(str.length()<22){
                event.getSource().getSubject().sendMessage(getAnswer(sendID));
            }
            else {
                JSONObject object1 = new JSONObject().fromObject(bind.getDatafromFile(pathData+fileForBind));
                String fastQuery = str.substring(22);
                if(!object1.containsKey(fastQuery))
                event.getSource().getSubject().sendMessage("nmsl?你会发个🔨的指令");
            }
            flag ++;
            if(flag ==4)
                flag = 1;
        } else if (getSensitiveWord(str)) {
                for (int i = 0; i < flag; i++) {
                    event.getSource().getSubject().sendMessage(getZUAN(sendID));
                }
                flag ++;
                if(flag ==4)
                    flag = 1;
        } else if (str.contains(prx)&&dogQingYe(sendID)) {
            for (int i = 0; i < flag; i++) {
                event.getSource().getSubject().sendMessage(getZUAN(sendID));
            }
            flag ++;
            if(flag ==4)
                flag = 1;
        }
    }
    private String getMemberid (GroupMessageEvent event) {
        String str = event.getSender().toString();
        int M = str.indexOf("(");
        int N = str.indexOf(")");
        return str.substring(M+1,N);
    }
    private MessageChain getAnswer (String id) {
        String data = dataBind.getDatafromFile(path+fileForAnswer);
        String[] strings = data.split(";;");
        String str = "[mirai:at:"+id+"] ";
        str += strings[(int)(Math.random()*strings.length)];
        chain = MiraiCode.deserializeMiraiCode(str);
        return chain;
    }
    private void setZUAN (String word) {
        File file = new File(path+fileForZuan);
        if (file.exists()){
            String data = dataBind.getDatafromFile(path+fileForZuan);
            String[] arr = data.split(";;");
            data= data + ";;" + word;
            dataBind.saveDataToFile(path+fileForZuan,data);
        } else {
            dataBind.saveDataToFile(path+fileForZuan,word);
        }
    }
    private MessageChain getZUAN (String id) {
        String data = dataBind.getDatafromFile(path+fileForZuan);
        String[] strings = data.split(";;");
        String str = "[mirai:at:"+id+"] ";
        str += strings[(int)(Math.random()*strings.length)];
        chain = MiraiCode.deserializeMiraiCode(str);
        return chain;
    }
    private void setAnswer(String word) {
        File file = new File(path+fileForAnswer);
        if (file.exists()){
            String data = dataBind.getDatafromFile(path+fileForAnswer);
            String[] arr = data.split(";;");
            data= data + ";;" + word;
            dataBind.saveDataToFile(path+fileForAnswer,data);
        } else {
            dataBind.saveDataToFile(path+fileForAnswer,word);
        }
    }
    private void setSensitiveWord(String word) {
        File file = new File(path+fileFOrSensitive);
        if (file.exists()){
            String data = dataBind.getDatafromFile(path+fileFOrSensitive);
            String[] arr = data.split(";;");
           data= data + ";;" + word;
            dataBind.saveDataToFile(path+fileFOrSensitive,data);
        } else {
            dataBind.saveDataToFile(path+fileFOrSensitive,word);
        }
    }
    private boolean getSensitiveWord (String str) {
        String data = dataBind.getDatafromFile(path+fileFOrSensitive);
        String[] strings = data.split(";;");
        for (int i = 0;i<strings.length;i++){
            if(str.contains(strings[i]))
                return true;
        }
        return false;
    }
    private  boolean dogQingYe (String senderId) {
        if(senderId.equals("1315364970"))
            return true;
        else if (senderId.equals("519652964"))
            return true;
        return false;
    }
}
