package test;

import function.TYMatchQuery;
import function.qinYe;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.event.GlobalEventChannel;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.utils.BotConfiguration;
import tool.initData;

import java.io.*;

import static net.mamoe.mirai.message.code.MiraiCode.serializeToMiraiCode;


public class test {
    public static  void main(String[] args) throws IOException {
        Bot bot = BotFactory.INSTANCE.newBot(2504637063L, "asd258258*",new BotConfiguration(){{
                fileBasedDeviceInfo("myDeviceInfo.json");
                redirectNetworkLogToFile();
                redirectNetworkLogToDirectory();
            }
            });
            bot.login();
        initData.init();
        TYMatchQuery tyMatchQuery = new TYMatchQuery();
        qinYe qinYe = new qinYe();
                GlobalEventChannel.INSTANCE.subscribeAlways(GroupMessageEvent.class, event -> {
                    try {
                        qinYe.Fuck(event);
                        tyMatchQuery.tyMatch(event);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

//        try {
//                        Bot bot = BotFactory.INSTANCE.newBot(2504637063L, "asd258258*",new BotConfiguration(){{
//                fileBasedDeviceInfo("myDeviceInfo.json");
//                redirectNetworkLogToFile();
//                redirectNetworkLogToDirectory();
//            }
//            });
//            bot.login();
//            dataBind bind = new dataBind();
//            String prx = "[mirai:at:2504637063]";
//            String command = "战绩查询：";
//            getJsonValue getJsonValue = new getJsonValue();
//            GlobalEventChannel.INSTANCE.subscribeAlways(GroupMessageEvent.class, event -> {
//                String str =event.getMessage().serializeToMiraiCode();
//                str.replace(" ",""); //去除所有空格，包括首尾、中间
//                int index = str.indexOf(prx);
//                String fastQuery = str.substring(index+22);
//                System.out.println(fastQuery);
//               if(str.indexOf(prx)>=0){
//                   JSONObject object1 = new JSONObject().fromObject(bind.getDatafromFile("bind"));
//                   System.out.println(object1);
//                   if(str.indexOf(command)>=0){
//                       int num = str.indexOf("：");
//                       String name = str.substring(num+1);
//                       role role=getJsonValue.dataHandleRole(name);
//                       String INFO = getJsonValue.dataHandleMatch(name);
//                       event.getSource().getSubject().sendMessage(role.toString()+INFO);
//                   } else if (object1.containsKey(fastQuery)) {
//                       role role=getJsonValue.dataHandleRole(object1.getString(fastQuery));
//                       String INFO = getJsonValue.dataHandleMatch(object1.getString(fastQuery));
//                       event.getSource().getSubject().sendMessage(role.toString()+INFO);
//                   }
//               }
//
//
//            });
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }


    }
}
