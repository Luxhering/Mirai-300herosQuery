package data;

public class role {
    int type =1 ;
    String RoleName = "";
    int RoleLevel;
    int WinCount;
    int MatchCount;
    String RankValue = "太久没打啦";
    String UpdateTime;
    String info = "查询成功\n";

    public int getType() {
        return type;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getRankValue() {
        return RankValue;
    }

    public void setRankValue(String rankValue) {
        RankValue = rankValue;
    }

    public String getRoleName() {
        return RoleName;
    }

    public void setRoleName(String roleName) {
        RoleName = roleName;
    }

    public int getRoleLevel() {
        return RoleLevel;
    }

    public void setRoleLevel(int roleLevel) {
        RoleLevel = roleLevel;
    }

    public int getWinCount() {
        return WinCount;
    }

    public void setWinCount(int winCount) {
        WinCount = winCount;
    }

    public int getMatchCount() {
        return MatchCount;
    }

    public void setMatchCount(int matchCount) {
        MatchCount = matchCount;
    }

    public String getUpdateTime() {
        return UpdateTime;
    }

    public void setUpdateTime(String updateTime) {
        UpdateTime = updateTime;
    }

    @Override
    public String toString() {
        if(RoleName == "")
            return "查不到相应角色的战绩，请检查角色名字是否正确";
        else
            return "查询战绩如下：\n"
                + "角色名字：" + RoleName + "\n"
                + "角色等级：" + RoleLevel +"\n"
                + "胜场：" + WinCount + "\n"
                + "总场次：" + MatchCount + "\n"
                + "团分：" + RankValue + "\n"
                + "战绩更新时间：" + UpdateTime + "\n"
                +"备注：" + info;
    }
}
