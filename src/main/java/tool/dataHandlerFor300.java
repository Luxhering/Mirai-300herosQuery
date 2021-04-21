package tool;

import data.role;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class dataHandlerFor300 {
    httpTool test = new httpTool();
    JSONArray MatchArray=null;
    /**
     * 获取300战绩角色数据并进行初步处理，以便展示
     * @param name String
     * @return role role
     * **/
    public role dataHandleRole  (String name) {
        role role = new role();
    try {
        String RoleStr= test.doGet(name,"getrole","name"); // 获取角色信息
        JSONObject obj = new JSONObject().fromObject(RoleStr);
        if(obj.containsKey("Role")){
            //获取Role的信息
            JSONObject Role = obj.getJSONObject("Role");
            role.setRoleName(Role.getString("RoleName"));
            role.setRoleLevel(Role.getInt("RoleLevel"));
            role.setMatchCount(Role.getInt("MatchCount"));
            role.setWinCount(Role.getInt("WinCount"));
            role.setUpdateTime(Role.getString("UpdateTime"));
            if(obj.get("Rank").toString()!="null"){
                JSONArray Rank = obj.getJSONArray("Rank");
                for(int i = 0;i<Rank.size();i++){
                    JSONObject tmp = Rank.getJSONObject(i);
                    if(tmp.getInt("Type")==role.getType()){
                        role.setRankValue(tmp.getString("Value"));
                    }
                }
            } else {
                role.setInfo("太久没打啦，没有团分\n");
            }
        } else {
        role.setInfo("ty表示查无此人");
        }
    }
    catch(Exception e) {
        e.printStackTrace();
    }
        return role;
    }
    /**
     * 获取300战绩比赛数据
     * @param name String
     * @return MatchArray JSONArray
     * **/
    public JSONArray dataHandleMatch(String name) {

        try {
            String MatchStr = test.doGet(name, "getlist","name");
            JSONObject MatchObj = new JSONObject().fromObject(MatchStr);
             MatchArray = MatchObj.getJSONArray("List");
            return MatchArray;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return MatchArray;
    }
}
