/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataClass;

/**
 *
 * @author ssandoval114
 */

public class Time {
// class save time in 24 hours
    

    private int inHours = 0;
    private int inMin = 0;
    private int outHours = 0;
    private int outMin = 0;
    private int totalMinutes = 0;
    private int totalHours = 0;
    private boolean off;
    private int errorType = 0;

    // call default constructor when off
    public Time() {
      off = true;
    }
public Time(int inHours,int inMin,int outHours,int outMin){
    this.inHours = hoursInRange(inHours);
    this.inMin = minutesInRange(inMin);
    this.outHours = hoursInRange(outHours);
    this.outMin = minutesInRange(outMin);
    calculateTime();
}
    // takes time as 12 hours
    // constructor take in note the day end  at 11:59 pm 
    // start at 12:00 am
    public Time( int inHours, int inMin, String inTime,
                 int outHours,int outMin, String outTime) {
        
      inTimeto24(inHours,inMin, inTime);
      outTimeto24(outHours,outMin, outTime);
        calculateTime();
    }
    // class only take PM and AM
    public void setinTime12(int inHours, int inMin, String inTime) {
        inTimeto24(inHours,inMin,inTime);
        calculateTime();
    }
    // class only take PM and AM    
    public void setOutTime12(int outHours,int outMin, String outTime){
        outTimeto24(outHours,outMin,outTime);
        calculateTime();
    }
    
    private void outTimeto24(int outHours,int outMin, String outTime){
        
        if("AM".equals(outTime)){
            if(outHours == 12){
               this.outHours = 0;
               this.outMin = outMin;
            }else{
                this.outHours = outHours;
                this.outMin = outMin;
            }
        }else if("PM".equals(outTime)){
            if(outHours == 12){
                this.outHours =  outHours;
                this.outMin = outMin;
            }else{
                this.outHours = outHours + 12;
                this.outMin = outMin;
            }
                
        }
    }
    private void inTimeto24(int inHours, int inMin, String inTime){
        if("AM".equals(inTime)){
            if(inHours == 12){
               this.inHours = 0;
               this.inMin = inMin;
            }else{
                this.inHours = inHours;
                this.inMin = inMin;
            }
        }else if("PM".equals(inTime)){
            if(inHours == 12){
                this.inHours =  inHours;
                this.inMin = inMin;
            }else{
                this.inHours = inHours + 12;
                this.inMin = inMin;
            }
                
        }
    }
    public void setOff(boolean off) {
        this.off = off;
    }

    public boolean isOff() {
        return off;
    }
    
    public String getFormatedTimeIn(){
        if(this.inHours == 0){
            return "12 : " + this.inMin + " AM";
            
        }else if( this.inHours < 12){
         return this.inHours + " : " + this.inMin + " AM";
         
        }else if(this.inHours == 12){
           return this.inHours + " : " + this.inMin + " PM"; 
           
        }
        int num = this.inHours - 12;
        return num + " : " + this.inMin + " PM";
    }
    public String getFormatedTimeOut(){
        if(this.outHours == 0){
            return "12 : " + this.outMin + " AM";
            
        }else if( this.outHours < 12){
         return this.outHours + " : " + this.outMin + " AM";
         
        }else if(this.outHours == 12){
           return this.outHours + " : " + this.outMin + " PM"; 
           
        }
        int num = this.outHours - 12;
        return num + " : " + this.outMin + " PM";
    }
    public String getFromatedTime(){
       if(this.off == true){
           return "Off";
       }
        return getFormatedTimeIn() + " - " + getFormatedTimeOut();
        
    }
    
    public int getTotalHours() {
        if(this.off == true){
            return 0;
        }else{
            return totalHours;
        }
    }
    
    public int getTotalMinutes() {
        if(this.off == true){
            return 0;
        }else{
            return totalMinutes;
        }
    }
    
   public String getTotalTime(){
       if(this.off == true){
           return "Off";
       }
       return totalHours + " : " + totalMinutes;
   }
   public int getTotalMinutesOfDate(){
       
       if( off == true){
           return 0;
       }
       return totalMinutes = totalMinutes();
   }
   // function get the total hours and minutes  
    private void calculateTime() {

        totalMinutes = totalMinutes();
        
        while( totalMinutes >= 60){
            totalMinutes = totalMinutes - 60;
            totalHours = totalHours + 1;
        }
        
    }
// check if time make sense
    private boolean validate() {
        int inhour = this.inHours;
        int outhour = this.outHours;
      if(this.inHours == 0 || this.outHours == 0){
           inhour = 1 + this.inHours;   
           outhour = 1 + this.outHours;
        }
        if (inhour > outhour) {
            errorType = 1;
            return false;

        } else if (inhour == outhour && this.inMin == this.outMin) {
            errorType = 2;
            return false;
            
        } else if (outhour == inhour && inMin > outMin) {
            errorType = 3;
            return false;
            
        }else if(this.off == true){
            errorType = 4;
            
        }
        errorType = 0;
        return true;
    }
    // validate and return error if their is one
    public String getTypeOfError(){
        validate();
        
        if(errorType == 1){
            return "Opening bussines hours can not be greater than " ;
        }else if(errorType == 2){
            return "Opening and closing could not be the same time";
        }else if(errorType == 3){
            return "Minutes opening could not be greater than closing. When hours is the same";
        }else if(errorType == 4){
            return "This date was choosen as off";
        }
        
        return "";
    }
// return the total # of minutes
    private int totalMinutes() {
        int inhour = this.inHours;
        int inmin = this.inMin;
        int outhour = this.outHours;
        int outmin = this.outMin;
        
        boolean equal = validate();
        
        int totalMin = 0;
       
        while (equal) {

            inmin = (inmin + 1);

            if (inmin == 60) {
                inhour = (inhour + 1);
                inmin = 0;
            }

            totalMin = (totalMin + 1);

            if (inhour == outhour && inmin == outmin) {
                equal = false;
            }
        }

        return totalMin;
    }

    private int minutesInRange(int minutes) {
        if (minutes > 59) {
            return 59;
        } else if (minutes < 1) {
            return 1;
        }

        return minutes;
    }

    private int hoursInRange(int hours) {
        
        if (hours > 23) {
          hours =  23;
            
            
        } else if (hours < 0 ) {
            hours =  1;
        }

        return hours;
    }

    public void setTohours(int tohours) {
        this.inHours = hoursInRange(tohours);
        calculateTime();
    }

    public void setToMin(int toMin) {
        this.inMin = minutesInRange(toMin);
         calculateTime();
    }

    public void setFromHours(int fromHours) {
        this.outHours = hoursInRange(fromHours);
        calculateTime();
    }

    public void setFromMin(int fromMin) {
        this.outMin = minutesInRange(fromMin);
        calculateTime();
    }
///opening
    public int getInHours() {
        return inHours;
       
    }

    public int getInMin() {
        return inMin;
    }
// closing
    public int getOutHours() {
        return outHours;
    }

    public int getOutMin() {
        return outMin;
    }

}

