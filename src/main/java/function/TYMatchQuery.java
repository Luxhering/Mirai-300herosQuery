package function;

import data.role;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.message.code.MiraiCode;
import net.mamoe.mirai.message.data.*;
import net.mamoe.mirai.utils.ExternalResource;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import tool.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class TYMatchQuery {
    String path = "./data/Mirai-300query/300headPicture/";
    tyPictureSave tyPictureSave = new tyPictureSave();
    resizePicture resizePicture = new resizePicture();
    httpTool httpTool = new httpTool();
    dataBind bind = new dataBind();
    String prx = "[mirai:at:2504637063]";
    String command = "战绩查询：";
    String fastQuery = "";
    dataHandlerFor300 dataHandlerFor300 = new dataHandlerFor300();
    public void tyMatch (GroupMessageEvent event) throws IOException {
        String str =event.getMessage().serializeToMiraiCode(); // 序列化
        str.replace(" ",""); //去除所有空格，包括首尾、中间
        int index = str.indexOf(prx);
        if(str.contains(prx)){
            if(str.length()>=(index+22)){
                fastQuery = str.substring(index+22);
            }
            JSONObject object1 = new JSONObject().fromObject(bind.getDatafromFile("bind"));
            System.out.println(object1);
            if(str.contains(command)){
                int num = str.indexOf("：");
                String name = str.substring(num+1);
                MessageChain chain = MatchInfo(event,name);
                event.getSource().getSubject().sendMessage(chain);
            } else if (object1.containsKey(fastQuery)) {
                MessageChain chain = MatchInfo(event,object1.getString(fastQuery));
                event.getSource().getSubject().sendMessage(chain);
            }
        }
    }
    private MessageChain MatchInfo(GroupMessageEvent event,String name) throws IOException {
        MessageChain chain = null;
        JSONArray MatchArray= dataHandlerFor300.dataHandleMatch(name);
        String INFO="";
        String Icon="";
        String imgUrl = "";
        role role= dataHandlerFor300.dataHandleRole(name);
        INFO = role.toString();
        if(MatchArray.size()>0){
            for(int i =0;i<MatchArray.size();i++){
                JSONObject tmpArray = MatchArray.getJSONObject(i);
                JSONObject tmpObject = tmpArray.getJSONObject("Hero");
                Icon = tmpObject.getString("IconFile");
                File file = new File(path+Icon);
                if(file.exists()){
                    FileInputStream inputStream = new FileInputStream(file);
                    ExternalResource resource = ExternalResource.create(inputStream);
                    imgUrl = event.getSource().getSubject().uploadImage(resource).getImageId();
                } else {
                    InputStream inputStream = httpTool.getInputStream(Icon);
                    if(tyPictureSave.downloadImg(inputStream,Icon)) {
                        if(resizePicture.resizeImage(path+Icon,path+Icon,32,32,false)){
                            System.out.println("图片修改存储成功");
                            File fileA = new File(path+Icon);
                            FileInputStream fileInputStream = new FileInputStream(file);
                            ExternalResource resourceA = ExternalResource.create(fileInputStream);
                            imgUrl = event.getSource().getSubject().uploadImage(resourceA).getImageId();
                        }
                    }
                    else {
                        System.out.println("图片修改失败，采用原大小");
                        ExternalResource resource = ExternalResource.create(inputStream);
                        imgUrl = event.getSource().getSubject().uploadImage(resource).getImageId();
                    }
                    inputStream.close();
                }
                INFO += "[mirai:image:"+imgUrl+"]";
                if(tmpArray.getInt("MatchType")==1)
                    INFO += "\t比赛类型：竞技场";
                else {
                    INFO += "\t比赛类型：战场";
                }
                INFO += "\t角色等级："+tmpArray.getString("HeroLevel");
                int Result = tmpArray.getInt("Result");
                if(Result==1)
                    INFO += "\t比赛结果：胜利";
                else if(Result==2)
                    INFO += "\t比赛结果：失败\uD83E\uDD6C\uD83C\uDD71";
                else if (Result==3)
                    INFO += "\t比赛结果：在？为什么逃跑？";
                INFO += "\t"+tmpArray.getString("MatchDate")+"\n";

            }
        }
        chain = MiraiCode.deserializeMiraiCode(INFO);
        return chain;
    }
}
