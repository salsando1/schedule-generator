package schedulerbysalvador;

import dataClass.*;
import utilities.OnlyNumber;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EmployeeController {

    @FXML private Button btOk;
    @FXML private OnlyNumber tfHours;
    @FXML private CheckBox cbMondayOff;
    @FXML private CheckBox cbTuesdayOff;
    @FXML private TextField tfName;
    @FXML private Button btCancel;
    @FXML private CheckBox cbSundayOff;
    @FXML private CheckBox cbSaturdayOff;
    @FXML private CheckBox cbThursdayOff;
    @FXML private CheckBox cbFridayOff;
    @FXML private CheckBox cbWednesdayOff;
    private Schedule business = null;
    private Schedule work = null;
    private Stage dialogStage;
    private boolean dialogOk = false;
    
    public boolean isDialogOk() {
        return dialogOk;
    }
    
     // to pass the current open busines houurs       
    public void setBussinesTotalHours(Schedule bussineshours){
        this.business = bussineshours;
    }
    // to close dialog when done
     public  void setDialogStage(Stage dialogStage) {
       this.dialogStage = dialogStage;
    }
     // show warning if error ocurr
     private void ShowWarning(String bussinesHours, String requestedHours){
           Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(dialogStage);
            alert.setTitle("Warning");
            alert.setHeaderText("The maximum Hours for this bussines week is "
               + bussinesHours + " Requested hours to work " + requestedHours + 
               "please check available hours!!   ");
            alert.showAndWait();
}
     
     // getting days off from check box
    private ScheduleGenerator gettingDaysOff( ScheduleGenerator g){
        g.setMondayoff(cbMondayOff.isSelected());
       // System.out.println( cbMondayOff.isSelected());
        g.setTuesdayOff(cbTuesdayOff.isSelected());
        g.setWednesdayOff(cbWednesdayOff.isSelected());
        g.setThursdayOff(cbThursdayOff.isSelected());
        g.setFridayOff(cbFridayOff.isSelected());
        g.setSaturdayOff(cbSaturdayOff.isSelected());
        g.setSundaOff(cbSundayOff.isSelected());
        
        return g;
    }
    
    @FXML
    private void btnOk(ActionEvent event) {
  
      ScheduleGenerator schedulegenerator = new ScheduleGenerator();
      schedulegenerator.setWorkWeek(business);
       //setting the days off and returning the object 
      schedulegenerator = gettingDaysOff(schedulegenerator);
      // setting are schedule to class member to return   
     this.work =  schedulegenerator.generateSchedule(this.tfHours.getHours(),0);
//     System.out.println( "this is the total for working schedule " + this.work.getTotalMinutesOfWeek() + 
//             " this is how many hours is pass from textfield "+ this.tfHours.getHours() + " this is what return from bussines total week "+
//                     this.business.getTotalMinutesOfWeek());
      //checking if requested hours is not greater than bussines hours
       if( this.tfHours.getHours() * 60 > this.business.getTotalMinutesOfWeek()){
            //  the warning will show how many hours in bussines days also how many hours
            // is possible to due in requested time
           ShowWarning(this.business.getTotalHoursOfWeek(), this.tfHours.getHours()+ " : 00");
           this.dialogOk = false; // making sure the operation was succesfull             
        }else{
         // close dialog because
          dialogStage.close();
          this.dialogOk = true; // making sure the operation was succesfull
        }
        
    }
    
    public Schedule getSchedule(){
       work.setName(this.tfName.getText());
        return work;
    }

    @FXML
    void btnCancel(ActionEvent event) {
        this.dialogOk = false;
        dialogStage.close();
    }
  
}
