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
                    setSpecialWord(word);
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
            if (getSpecialWord(str)) {
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
                event.getSource().getSubject().sendMessage("nmsl?你会发个🔨的指令");
            }
            flag ++;
            if(flag ==4)
                flag = 1;
        } else if (getSpecialWord(str)) {
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
        String data = dataBind.getDatafromFile("Answer.txt");
        String[] strings = data.split(";;");
        String str = "[mirai:at:"+id+"] ";
        str += strings[(int)(Math.random()*strings.length)];
        chain = MiraiCode.deserializeMiraiCode(str);
        return chain;
    }
    private void setZUAN (String word) {
        File file = new File("./300ICUData/ZUAN.json");
        if (file.exists()){
            String data = dataBind.getDatafromFile("ZUAN");
            String[] arr = data.split(";;");
            data= data + ";;" + word;
            dataBind.saveDataToFile("ZUAN",data);
        } else {
            dataBind.saveDataToFile("ZUAN",word);
        }
    }
    private MessageChain getZUAN (String id) {
        String data = dataBind.getDatafromFile("ZUAN");
        String[] strings = data.split(";;");
        String str = "[mirai:at:"+id+"] ";
        str += strings[(int)(Math.random()*strings.length)];
        chain = MiraiCode.deserializeMiraiCode(str);
        return chain;
    }
    private void setAnswer(String word) {
        File file = new File("./300ICUData/Answer.json");
        if (file.exists()){
            String data = dataBind.getDatafromFile("Answer");
            String[] arr = data.split(";;");
            data= data + ";;" + word;
            dataBind.saveDataToFile("Answer",data);
        } else {
            dataBind.saveDataToFile("Answer",word);
        }
    }
    private void setSpecialWord(String word) {
        File file = new File("./300ICUData/specialWord.json");
        if (file.exists()){
            String data = dataBind.getDatafromFile("specialWord");
            String[] arr = data.split(";;");
           data= data + ";;" + word;
            dataBind.saveDataToFile("specialWord",data);
        } else {
            dataBind.saveDataToFile("specialWord",word);
        }
    }
    private boolean getSpecialWord (String str) {
        String data = dataBind.getDatafromFile("specialWord");
        String[] strings = data.split(";;");
        for (int i = 0;i<strings.length;i++){
            if(str.contains(strings[i]))
                return true;
        }
        return false;
    }
}
