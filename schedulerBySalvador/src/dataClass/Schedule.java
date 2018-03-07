/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataClass;

/**
 *
 * @author salsa
 */
public class Schedule {

private Time monday;
private Time tuesday;
private Time wednesday;
private Time thursday;
private Time friday;
private Time saturday;
private Time sunday;
private String name = " ";
private int totalMinutes = 0;
private int totalHours = 0;

    public Schedule(Time monday, Time tuesday, Time wednesday,
                  Time thursday, Time friday,Time saturday,
                   Time sunday, String name) {
        
        monday = new Time();
        tuesday = new Time();
        wednesday = new Time();
        thursday = new Time();
        friday = new Time();
        saturday = new Time();
        sunday = new Time();
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday  = wednesday;
        this.thursday = thursday;
        this.friday = friday;
        this.saturday = saturday;
        this.sunday = sunday;
        this.name = name;
        
    }
    
    public Schedule(){
        monday = new Time();
        tuesday = new Time();
        wednesday = new Time();
        thursday = new Time();
        friday = new Time();
        saturday = new Time();
        sunday = new Time();
    }
     // return hours and minutes in a formated string
    public String getTotalHoursOfWeek() {
        addingMinutesAndHours();
        convertToHours();
        return totalHours + " : " + totalMinutes;
    }
    // return total minutes
    public int getTotalMinutesOfWeek(){
        addingMinutesAndHours();
        int totalMinutesPerWeek =  ( (totalHours * 60) + totalMinutes);
        return totalMinutesPerWeek;
    }
    
    // adding minutes and hours very self explanatory
    private void addingMinutesAndHours(){
        totalHours = monday.getTotalHours();
        totalMinutes = monday.getTotalMinutes();
        totalHours = totalHours + tuesday.getTotalHours();
        totalMinutes = totalMinutes + tuesday.getTotalMinutes();
        totalHours = totalHours + wednesday.getTotalHours();
        totalMinutes = totalMinutes + wednesday.getTotalMinutes();
        totalMinutes = totalMinutes + thursday.getTotalMinutes();
        totalHours = totalHours + thursday.getTotalHours();
        totalMinutes = totalMinutes + friday.getTotalMinutes();
        totalHours = totalHours + friday.getTotalHours();
        totalMinutes = totalMinutes + saturday.getTotalMinutes();
        totalHours = totalHours + saturday.getTotalHours();
        totalMinutes = totalMinutes + sunday.getTotalMinutes();
        totalHours = totalHours + sunday.getTotalHours();
    }
    // adding remaining minutes 
    private void convertToHours(){
        
        while( totalMinutes >= 60){
            totalMinutes = totalMinutes - 60;
            totalHours = totalHours + 1;
        }
    }
    
    public String getTotalHoursMonday() {
       return this.monday.getTotalTime();
    }

    public String getTotalHoursTuesday() {
       return this.tuesday.getTotalTime();
    }

    public String getTotalHoursWednesday() {
        return this.wednesday.getTotalTime();
    }

    public String getTotalHoursThursday() {
        return this.thursday.getTotalTime();
    }

    public String getTotalHoursFriday() {
        return this.friday.getTotalTime();
    }

    public String getTotalHoursSaturday() {
        return this.saturday.getTotalTime();
    }

    public String getTotalHoursSunday() {
         return this.sunday.getTotalTime();

    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setMonday(Time monday) {
        this.monday = monday;
    }

    public void setTuesday(Time tuesday) {
        this.tuesday = tuesday;
    }

    public void setWednesday(Time wednesday) {
        this.wednesday = wednesday;
    }

    public void setThursday(Time thursday) {
        this.thursday = thursday;
    }

    public void setFriday(Time friday) {
        this.friday = friday;
    }

    public Time getMondayTime() {
        return monday;
    }

    public Time getTuesdayTime() {
        return tuesday;
    }

    public Time getWednesdayTime() {
        return wednesday;
    }

    public Time getThursdayTime(){
        return thursday;
    }

    public Time getFridayTime(){
        return friday;
    }

    public Time getSaturdayTime() {
        return saturday;
    }

    public Time getSundayTime() {
        return sunday;
    }

    public void setSaturday(Time saturday) {
        this.saturday = saturday;
    }

    public void setSunday(Time sunday) {
        this.sunday = sunday;
    }

}
