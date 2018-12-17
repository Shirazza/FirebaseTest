package com.example.shira.firebasetest;

public class teams {
    public String id;
    public int teamNum;
    public String Name;

    public teams(String id, int teamNum, String Name) {
        this.id = id;
        this.teamNum = teamNum;
        this.Name = Name;
    }

    public teams() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTeamNum() {
        return teamNum;
    }

    public void setTeamNum(int teamNum) {
        this.teamNum = teamNum;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
