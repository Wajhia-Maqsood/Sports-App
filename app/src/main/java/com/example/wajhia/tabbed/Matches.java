package com.example.wajhia.tabbed;

/**
 * Created by Fizza on 5/8/2016.
 */
public class Matches {
    String date;
    String time;
    String location;
    String house;
    String sport;
    String type;

    String getdate(){
        return date;
    }
    String gettype(){
        return type;
    }
    String gettime(){
        return time;
    }
    String getlocation(){
        return location;
    }
    String getsport(){
        return sport;
    }
    String gethouse(){
        return house;
    }
    void setdate(String date){
        this.date=date;
    }
    void settime(String time){
        this.time=time;
    }
    void setlocation(String location){
        this.location=location;
    }
    void setsport(String sport){
        this.sport=sport;
    }
    void sethouse(String house){
        this.house=house;
    }
    void settype(String type){
        this.type=type;
    }
}
