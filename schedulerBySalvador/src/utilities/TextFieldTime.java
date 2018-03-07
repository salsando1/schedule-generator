/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.util.Arrays;
import javafx.scene.control.TextField;

/**
 *
 * @author ssandoval114
 */
public class TextFieldTime extends TextField{

    
    @Override
    public void replaceSelection(String replacement) {
       verifyLegth();
        FormatStringInTime();
        CheckStringForDigit();
        InRange();
    }

    @Override
    public void replaceText(int start, int end, String text) {
        verifyLegth();
        FormatStringInTime();
        CheckStringForDigit();
        InRange();
    }
    
    private void FormatStringInTime(){
         String time = getText();
         StringBuilder b = new StringBuilder(time);
	                
         b.setCharAt(3, ':');
	 b.setCharAt(9, ':');
         b.setCharAt(6, '-');
         
       
       setText( b.toString());
    }
    
    private void CheckStringForDigit(){
         String time = getText();
         StringBuilder b = new StringBuilder(time);
         
         for(int i=0;i < time.length();i++){  
          if(  i != 3|| i != 6 ||i != 9 ){
             
              if( !Character.isDigit(time.charAt(i))){
                   b.setCharAt(i, '0');
              }
                  
	 }
       } 
       
       setText( b.toString());
    }
    
    private void InRange(){
        int fromHour = Integer.parseInt(getText().substring(0,2))  ;
        int fromMin = Integer.parseInt(getText().substring(3,5)) ;
        int toHour = Integer.parseInt(getText().substring(6,8));
        int toMin = Integer.parseInt(getText().substring(9,11) );
        
        setText(HoursInRange(fromHour) + ":" + MinutesInRange(fromMin) + "-"
                +HoursInRange(toHour) + ":" + MinutesInRange(toMin));
    } 
    private String HoursInRange(int hours){
        if(hours > 12){
            return "12";
        }else if(hours < 1 ){
            return "1";
        }
        
        return ""+ hours;
    }
    private String MinutesInRange(int minutes){
        if(minutes > 59){
            return "59";
        }else if(minutes < 1 ){
            return "1";
        }
        
        return ""+ minutes;
    }
    private void verifyLegth() {
        
        if (getText().length() > 11) {
            setText(getText().substring(0, 11));
        }
        
        
 
       
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
