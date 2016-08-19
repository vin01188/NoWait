package com.vince.nowait;

/**
 * Created by vinka on 8/18/2016.
 */

public class Restaurant {
    private String name;
    private String address;
    private int waitTime;
    private int checkins;

    public Restaurant(){

    }

    public Restaurant(String name, String address){
        this.name = name;
        this.address = address;
        this.waitTime = 0;
        this.checkins = 0;
    }

    public String getName(){
        return name;
    }
    public String getAddress(){
        return address;
    }
    public int getWaitTime(){
        return waitTime;
    }
    public int getCheckins(){
        return checkins;
    }
    public void setCheckins(int checkins){
        this.checkins = checkins;
    }
    public void setWaitTime(int time){
        this.waitTime = time;
    }

    public void setName(String name){
       this.name = name;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public void checkIn(int time){
        this.waitTime = (checkins * waitTime + time) / (checkins + 1);
        this.checkins = checkins + 1;
    }
}
