/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import javafx.scene.control.TextField;
import java.io.*;

/**
 *
 * @author ssandoval114
 */
public class HoursTextField extends TextField{
    
    public HoursTextField(){
        //setText("12");
    }
    public int getHours(){
      
       if(getText().isEmpty()){
           return 1;
       }
       
         verifyLegth();
       return Integer.parseInt(getText());
       
    }
   @Override
    public void replaceText(int start, int end, String text)
    { 
              
        if (validate(text))
        {
                       
            super.replaceText(start, end, text);
           verifyLegth();
        }
        
        
    }

    @Override
    public void replaceSelection(String text)
    {
        
        if (validate(text))
        {
            super.replaceSelection(text);
            verifyLegth();
        }
        
        
    }

    private boolean validate(String text)
    {
       return text.matches("[0-9]*");
    }
   
    private void verifyLegth() {
        if (getText().length() > 2) {
            setText(getText().substring(0, 2));
        }
        
       numInRange();
       
    }
    
    private void numInRange(){
        int num;
        
        if(!getText().isEmpty())
        {
            num = Integer.parseInt(getText()); 
            
           if(num > 12){
          setText("12");
            }

            if( num<1 ){
                setText("1");
            }  
        } 
             
    }
}
