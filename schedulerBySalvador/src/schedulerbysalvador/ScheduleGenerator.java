/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedulerbysalvador;

import dataClass.Time;
import dataClass.Schedule;

/**
 *
 * @author ssandoval114
 */
public class ScheduleGenerator {

    Schedule bussinesWeek;
    int totalMinutesPerWeek= 0;
    int totalMinutesMonday = 0;
    int totalMinTuesday = 0;
    int totalMinWednesday = 0;
    int totalMinThursday = 0;
    int totalMinFriday = 0;
    int totalMinSaturday = 0;
    int totalMinSunday = 0;
    boolean mondayoff = false;
    boolean tuesdayOff = true;
    boolean wednesdayOff = true;
    boolean ThursdayOff = true;
    boolean fridayOff = true;
    boolean saturdayOff = true;
    boolean sundaOff = true;

    ScheduleGenerator() {
        this.bussinesWeek = new Schedule();
    }

    public void divideTime() {
        // keep subtracting from minutes until you reach 0     
        while (totalMinutesPerWeek > 0) {
            // choosing ramdomly on which date to add minutes
            int num = (int) ((Math.random() * 7) + 1);

            if (num == 1 && !this.mondayoff){
                addingMonday();
            } else if (num == 2 && !this.tuesdayOff) {
                addingTuesday();
            } else if (num == 3 && !this.wednesdayOff) {
                addingWednesday();
            } else if (num == 4 && !this.ThursdayOff) {
                addingThursday();
            } else if (num == 5 && !this.fridayOff) {
                addingFriday();
            } else if (num == 6 && !this.saturdayOff) {
                addingSaturday();
            } else if (num == 7 && !this.sundaOff) {
                addingSunday();
            }
        }

    }

    //----------------adding time to each day seperately

    private void addingSunday() {
        if (!this.sundaOff) {
            // checking there is enought time on this date
            if (this.bussinesWeek.getSundayTime().getTotalMinutesOfDate() >= this.totalMinSunday) {
                totalMinutesPerWeek = (totalMinutesPerWeek - 30);
                this.totalMinSunday = this.totalMinSunday + 30;

            }
        }
    }

    private void addingSaturday() {
        if (!this.saturdayOff) {
            // checking there is enought time on this date
            if (this.bussinesWeek.getSaturdayTime().getTotalMinutesOfDate() >= this.totalMinSaturday) {
                totalMinutesPerWeek = (totalMinutesPerWeek - 30);
                this.totalMinSaturday = this.totalMinSaturday + 30;

            }
        }
    }

    private void addingFriday() {
        if (!this.fridayOff) {
            // checking there is enought time on this date
            if (this.bussinesWeek.getFridayTime().getTotalMinutesOfDate() >= this.totalMinFriday) {
                totalMinutesPerWeek = (totalMinutesPerWeek - 30);
                this.totalMinFriday = this.totalMinFriday + 30;

            }
        }
    }

    private void addingThursday() {
        if (!this.ThursdayOff) {
            // checking there is enought time on this date
            if (this.bussinesWeek.getThursdayTime().getTotalMinutesOfDate() >= this.totalMinThursday) {
                totalMinutesPerWeek = (totalMinutesPerWeek - 30);
                this.totalMinThursday = (this.totalMinThursday + 30);

            }
        }
    }

    private void addingWednesday() {
        if (!wednesdayOff) {
            // checking there is enought time on this date
            if (this.bussinesWeek.getWednesdayTime().getTotalMinutesOfDate() >= this.totalMinWednesday) {
                totalMinutesPerWeek = (totalMinutesPerWeek - 30);
                this.totalMinWednesday = (this.totalMinWednesday + 30);

            }
        }
    }

    private void addingTuesday() {
        if (!tuesdayOff) {
            // checking there is enought time on this date
            if (this.bussinesWeek.getTuesdayTime().getTotalMinutesOfDate() >= this.totalMinTuesday) {
                totalMinutesPerWeek = (totalMinutesPerWeek - 30);
                totalMinTuesday = (totalMinTuesday + 30);

            }
        }
    }

    private void addingMonday() {
        if (!mondayoff) {
            // checking there is enought time on this date
            if (this.bussinesWeek.getMondayTime().getTotalMinutesOfDate() >= totalMinutesMonday) {
                totalMinutesPerWeek = (totalMinutesPerWeek - 30);
                totalMinutesMonday = (totalMinutesMonday + 30);

            }
        }
    }
 //----------------------------------------------------------
    // checking if there is enought time on week for requested amount of hours

    private boolean checkEnoughtTimeOnWeek(int totalHours, int totalMin) {
        // adding hours to minutes
        int minutes = totalMin + (totalHours * 60);
     // if week is more then requested time then divide time and 
        // set up total minutes
        if (totalOfPossibleHoursWork() > minutes) {
            this.totalMinutesPerWeek = totalMin + (totalHours * 60);
            divideTime();
            return true;
        }
        return false;// not enoguth time 
    }
  private int totalOfPossibleHoursWork(){
    int totalMinutes = 0;
    int totalHours = 0;
    if(!mondayoff){
       totalHours = bussinesWeek.getMondayTime().getTotalHours();
        totalMinutes = bussinesWeek.getMondayTime().getTotalMinutes();
    }
    
    if(!tuesdayOff){
        totalHours = totalHours + bussinesWeek.getTuesdayTime().getTotalHours();
        totalMinutes = totalMinutes + bussinesWeek.getTuesdayTime().getTotalMinutes();
    }
    
    if(!wednesdayOff){
        totalHours = totalHours + bussinesWeek.getWednesdayTime().getTotalHours();
        totalMinutes = totalMinutes + bussinesWeek.getWednesdayTime().getTotalMinutes();
    }
        
    if(!ThursdayOff){
        totalMinutes = totalMinutes + bussinesWeek.getThursdayTime().getTotalMinutes();
        totalHours = totalHours + bussinesWeek.getThursdayTime().getTotalHours();
    }
        
    if(!fridayOff){
        totalMinutes = totalMinutes + bussinesWeek.getFridayTime().getTotalMinutes();
        totalHours = totalHours + bussinesWeek.getFridayTime().getTotalHours();
    }
        
    if(!saturdayOff){
        totalMinutes = totalMinutes + bussinesWeek.getSaturdayTime().getTotalMinutes();
        totalHours = totalHours + bussinesWeek.getSaturdayTime().getTotalHours();
    }
     
    if(!sundaOff){
        totalMinutes = totalMinutes + bussinesWeek.getSundayTime().getTotalMinutes();
        totalHours = totalHours + bussinesWeek.getSundayTime().getTotalHours();
    }
        int num = totalMinutes + (totalHours * 60);
        return num ;
}
    private Time open(int totalMinutes, Time time) {
     // transfering bussines open to close and open
        // since we are opening
        int inHours = time.getInHours();
        int inMin = time.getInMin();
        int outHours = time.getInHours();
        int outMin = time.getInMin();
        // adding the time to out since we are opening
//         System.out.println("open \ntotal minutes " + totalMinutes);
//         System.out.println("before time " + inHours +" : " + inMin+
//             " - " + outHours + " : " + outMin);
        while (0 <= totalMinutes) {
            // to make sure nothin 
            if(totalMinutes == 0){
                break;
            }else if (totalMinutes < 0){
                break;
            }
          // System.out.println();
    //transfering total of minutes work 
            // to actual schedule    
            totalMinutes = (totalMinutes - 30);
            outMin = (outMin + 30);
            
            // if minutes is 60 add and hours to closing
            if (outMin == 60) {
                outHours = (outHours + 1);
                outMin = outMin - 60;
                outMin = 0;
            }else if(outMin > 60){
                outHours = (outHours + 1);
                outMin = outMin - 60;
             
            }
      }
//    System.out.println("after time " + inHours +" : " + inMin+
//" - " + outHours + " : " + outMin + "\n");
        Time work = new Time(inHours, inMin, outHours, outMin);
        return work;
    }

    private Time close(int totalMinutes, Time time) {
   // transfering bussines open to close and open
        // since we are opening
        int inHours = time.getOutHours();
        int inMin = time.getOutMin();
        int outHours = time.getOutHours();
        int outMin = time.getOutMin();
//         System.out.println("closing \ntotal minutes " + totalMinutes);
//         System.out.println("before time " + inHours +" : " + inMin+
//             " - " + outHours + " : " + outMin);
        // adding the time to in since we are closing
        while (  0 <= totalMinutes) {
          // to make sure nothin 
            if(totalMinutes == 0){
                break;
            }else if (totalMinutes < 0){
                break;
            }
            totalMinutes = (totalMinutes - 30);
            inMin = (inMin - 30); 
      // in minutes monday is less then 0 subtract and hour
            // schedule generator only handle 24 hour time
            // if subtract add 59 to minutes of working schedule
             if(inMin < 0){
                inHours = (inHours - 1);
                inMin = inMin + 60;
            }
        }
//         System.out.println("after time " + inHours +" : " + inMin+
//" - " + outHours + " : " + outMin + "\n");
        Time work = new Time(inHours, inMin, outHours, outMin);
        return work;
    }

    // is going to choose ramdomly if person is going to open or close

    private Schedule openingOrClosing() {
        Schedule schedule = new Schedule();

        for (int i = 1; i < 8; i++) {
            // choose ramdomly if employee is going to open or close
            byte num = (byte) ((Math.random() * 2) + 1);

            // if date is not off then choose opening or closing
            if (i == 1 && !mondayoff) {
                if (num == 1) {
                    Time time = open(totalMinutesMonday, bussinesWeek.getMondayTime());
                    schedule.setMonday(time);
                } else {
                    Time time = close(totalMinutesMonday, bussinesWeek.getMondayTime());
                    schedule.setMonday(time);
                }

            } else if (i == 2 && !tuesdayOff) {
                if (num == 1) {
                    Time time  = open(totalMinTuesday, bussinesWeek.getTuesdayTime());
                    schedule.setTuesday(time);
                } else {
                    Time time = close(totalMinTuesday, bussinesWeek.getTuesdayTime());
                    schedule.setTuesday(time);
                }

            } else if (i == 3 && !wednesdayOff) {
                if (num == 1) {
                    Time time =open(totalMinWednesday, bussinesWeek.getWednesdayTime());
                    schedule.setWednesday(time);
                } else {
                    Time time = close(totalMinWednesday, bussinesWeek.getWednesdayTime());
                    schedule.setWednesday(time);
                }

            } else if (i == 4 && !ThursdayOff) {
                if (num == 1) {
                    Time time = open(totalMinThursday, bussinesWeek.getThursdayTime());
                    schedule.setThursday(time);
                } else {
                    Time time = close(totalMinThursday, bussinesWeek.getThursdayTime());
                    schedule.setThursday(time);
                }

            } else if (i == 5 && !fridayOff) {
                if (num == 1) {
                    Time time = open(totalMinFriday, bussinesWeek.getFridayTime());
                    schedule.setFriday(time);
                } else {
                    Time time = close(totalMinFriday, bussinesWeek.getFridayTime());
                    schedule.setFriday(time);
                }

            } else if (i == 6 && !saturdayOff) {
                if (num == 1) {
                    Time time = open(totalMinSaturday, bussinesWeek.getSaturdayTime());
                    schedule.setSaturday(time);
                } else {
                    Time time = close(totalMinSaturday, bussinesWeek.getSaturdayTime());
                    schedule.setSaturday(time);
                }

            } else if (i == 7) {
                if (num == 1 && !sundaOff) {
                    Time time = open(totalMinSunday, bussinesWeek.getSundayTime());
                    schedule.setSunday(time);
                } else {
                    Time time = close(totalMinSunday, bussinesWeek.getSundayTime());
                    schedule.setSunday(time);
                }
                
            }

        }
        return schedule;
    }
    public String errorNotEngoutTime(){
        return this.bussinesWeek.getTotalHoursOfWeek();
    }
    public Schedule generateSchedule(int totalHours, int totalMin) {

        if (!checkEnoughtTimeOnWeek(totalHours, totalMin)) {

            Schedule schedule =  new Schedule();
              schedule.setMonday(new Time());
              schedule.setTuesday(new Time());
              schedule.setWednesday(new Time());
              schedule.setThursday(new Time());
              schedule.setFriday(new Time());
              schedule.setSaturday(new Time());
              schedule.setSunday(new Time());
              
              return schedule;
        }

        Schedule schedule = openingOrClosing();

        return schedule;
    }

    public void setMonday(Time monday) {
        this.bussinesWeek.setMonday(monday);
    }

    public void setTuesday(Time tuesday) {
        this.bussinesWeek.setTuesday(tuesday);
    }

    public void setWednesday(Time wednesday) {
        this.bussinesWeek.setWednesday(wednesday);
    }

    private void setThursday(Time thursday) {
        this.bussinesWeek.setThursday(thursday);
    }

    private void setFriday(Time friday) {
        this.bussinesWeek.setFriday(friday);
    }

    private void setSaturday(Time saturday) {
        this.bussinesWeek.setSaturday(saturday);
    }

    private void setSunday(Time sunday) {
        this.bussinesWeek.setSunday(sunday);
    }

    public void setMondayoff(boolean mondayoff) {
        this.mondayoff = mondayoff;
    }

    public void setTuesdayOff(boolean tuesdayOff) {
        this.tuesdayOff = tuesdayOff;
    }
    public void setWednesdayOff(boolean wednesdayOff) {
        this.wednesdayOff = wednesdayOff;
    }

    public void setThursdayOff(boolean ThursdayOff) {
        this.ThursdayOff = ThursdayOff;
    }

    public void setFridayOff(boolean fridayOff) {
        this.fridayOff = fridayOff;
    }

    public void setSaturdayOff(boolean saturdayOff) {
        this.saturdayOff = saturdayOff;
    }

    public void setSundaOff(boolean sundaOff) {
        this.sundaOff = sundaOff;
    }

    public void setWorkWeek(Schedule workWeek) {
        this.bussinesWeek = workWeek;
    }

    
}
