package schedulerbysalvador;

import dataClass.Schedule;
import dataClass.Time;
import utilities.HoursTextField;
import utilities.MinTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ssandoval114
 */
public class Dialog {

    @FXML private HBox hbFridayConfig;
    @FXML private HBox hbTuesdayConfig;
    @FXML private HBox hbWednesdayConfig;
    @FXML private HBox hbThursdayConfig;
    @FXML private HBox hbMondayConfig;
    @FXML private HBox hbSaturdayConfig;
    @FXML private HBox hbSundayConfig;
    @FXML private HoursTextField tfHoursFromMonday;
    @FXML private MinTextField tfMinFromMonday;
    @FXML private HoursTextField tfHoursToMonday;
    @FXML private MinTextField tfMinToMonday;
    @FXML private HoursTextField tfHoursFromTuesday;
    @FXML private MinTextField tfMinFromTuesday;
    @FXML private HoursTextField tfHoursToTuesday;
    @FXML private MinTextField tfMinToTuesday;        
    @FXML private HoursTextField tfHoursFromWednesday;
    @FXML private MinTextField tfMinFromWednesday;
    @FXML private HoursTextField tfHoursToWednesday;
    @FXML private MinTextField tfMinToWednesday;        
    @FXML private HoursTextField tfHoursFromThursday;
    @FXML private MinTextField tfMinFromThursday;
    @FXML private HoursTextField tfHoursToThursday;
    @FXML private MinTextField tfMinToThursday;
    @FXML private HoursTextField tfHoursFromFriday;
    @FXML private MinTextField tfMinFromFriday;
    @FXML private HoursTextField tfHoursToFriday;
    @FXML private MinTextField tfMinToFriday;        
    @FXML private HoursTextField tfHoursFromSaturday;
    @FXML private MinTextField tfMinFromSaturday;
    @FXML private HoursTextField tfHoursToSaturday;
    @FXML private MinTextField tfMinToSaturday;     
    @FXML private HoursTextField tfHoursFromSunday;
    @FXML private MinTextField tfMinFromSunday;
    @FXML private HoursTextField tfHoursToSunday;
    @FXML private MinTextField tfMinToSunday;        
    @FXML private CheckBox cbMontoFri;
    @FXML private CheckBox cbSatToSun;
    @FXML private Label lMonday;
    @FXML private Label lTuesday;
    @FXML private Label lWednesday;
    @FXML private Label lThursday;
    @FXML private Label lFriday;
    @FXML private Label lSaturday;
    @FXML private Label lSunday;
    @FXML private Label lbWarning;
    @FXML private ChoiceBox cbFromMonday;
    @FXML private ChoiceBox cbToMonday;
    @FXML private ChoiceBox cbFromTuesday;
    @FXML private ChoiceBox cbToTuesday;
    @FXML private ChoiceBox cbFromWednesday;
    @FXML private ChoiceBox cbToWednesday;
    @FXML private ChoiceBox cbFromThursday;
    @FXML private ChoiceBox cbToThursday;
    @FXML private ChoiceBox cbFromFriday;
    @FXML private ChoiceBox cbToFriday;
    @FXML private ChoiceBox cbFromSaturday;
    @FXML private ChoiceBox cbToSaturday;
    @FXML private ChoiceBox cbFromSunday;
    @FXML private ChoiceBox cbToSunday;
    @FXML private CheckBox cbClosedMonday;
    @FXML private CheckBox cbClosedTuesday;
    @FXML private CheckBox cbClosedWednesday;
    @FXML private CheckBox cbClosedThursday;
    @FXML private CheckBox cbClosedFriday;
    @FXML private CheckBox cbClosedSaturday;
    @FXML private CheckBox cbClosedSunday;
    private Stage dialogStage;
    private Schedule business;

    public Schedule getBusiness() {
        return business;
    }
    private boolean confirm;
 


     @FXML
     public void btCancel(ActionEvent event){

    confirm = false;
       dialogStage.close();          
     }
     
     @FXML
    void btnOk(ActionEvent event) {
        
        
       if(ValidateMonday()){
           
       }else if(ValidateTuesday()){
       
       }else if(ValidateWednesday()){
           
       }else if(ValidateThursday()){
           
       }else if(ValidateFriday()){
           
       }else if(ValidateSaturday()){
           
       }else if(ValidateSunday()){
           
       }else{
           confirm = true;
          dialogStage.close(); 
       }
       
       
    }
    public boolean okButtonClick(){
        return confirm;
    }
    private boolean ValidateSunday(){
        if(!hbSundayConfig.isVisible()){
            
            return false;
        }
            String in = "AM";
            String out ="AM";
            int numFrom = 0;
            int numTo = 0;
         if(cbFromSunday.getSelectionModel().getSelectedItem() == "PM"){
            in = "PM";
            numFrom = 12;
         }
         
         if(cbToSunday.getSelectionModel().getSelectedItem() == "PM"){
             out = "PM";
             numTo = 12;
         }
          int inHours =  tfHoursFromSunday.getHours();
          int inMin = tfMinFromSunday.getMinutes();
          int outHours = tfHoursToSunday.getHours() ;
          int outMin = tfMinToSunday.getMinutes();
         
          //System.out.println("how UI give number" +hoursSundayFrom + minSundayFrom + hoursSundayTo + minSundayTo );
          Time time = new Time(inHours,inMin,in,
                               outHours,outMin,out);
          business.setSunday(time);
          
         
       return IsItInRange(tfHoursFromSunday.getHours() + numFrom,
       tfMinFromSunday.getMinutes(),tfHoursToSunday.getHours() + numTo,
       tfMinToSunday.getMinutes(),"Sunday"); 
             
     }
    private boolean ValidateSaturday(){
        if(!hbSaturdayConfig.isVisible()){      
            return false;
        }
            int numFrom = 0;
            int numTo = 0;
            String in = "AM";
            String out = "AM";
         if(cbFromSaturday.getSelectionModel().getSelectedItem() == "PM"){
             numFrom = 12;
             in = "PM";
         }
         
         if(cbToSaturday.getSelectionModel().getSelectedItem() == "PM"){
             numTo = 12;
             out = "PM";
         }
         int inHours = tfHoursFromSaturday.getHours();
         int inMin = tfMinFromSaturday.getMinutes();
         int outHours = tfHoursToSaturday.getHours();
         int outMin = tfMinToSaturday.getMinutes();
         Time time = new Time(inHours,inMin,in,
                              outHours,outMin,out);
         
         business.setSaturday(time);
         
       return IsItInRange(tfHoursFromSaturday.getHours() + numFrom,
       tfMinFromSaturday.getMinutes(),tfHoursToSaturday.getHours() + numTo,
       tfMinToSaturday.getMinutes(),"Saturday"); 
             
     }
     private boolean ValidateFriday(){
        if(!hbFridayConfig.isVisible()){
            return false;
        }
            int numFrom = 0;
            int numTo = 0;
            String in = "AM";
            String out = "AM";
         if(cbFromFriday.getSelectionModel().getSelectedItem() == "PM"){
             numFrom = 12;
             in = "PM";
         }
         
         if(cbToFriday.getSelectionModel().getSelectedItem() == "PM"){
             numTo = 12;
             out = "PM";
         }
         int inHours = tfHoursFromFriday.getHours() ;
         int inMin =  tfMinFromFriday.getMinutes();
         int outHours = tfHoursToFriday.getHours();
         int outMin = tfMinToFriday.getMinutes();
         Time time = new Time(inHours, inMin, in,
                              outHours,outMin,out);
         business.setFriday(time);
         
       return IsItInRange(tfHoursFromFriday.getHours() + numFrom,
       tfMinFromFriday.getMinutes(),tfHoursToFriday.getHours() + numTo,
       tfMinToFriday.getMinutes(),"Friday"); 
             
     }
    private boolean ValidateThursday(){
        if(!hbThursdayConfig.isVisible()){
            return false;
        }
            int numFrom = 0;
            int numTo = 0;
            String in = "AM";
            String out = "AM";
         if(cbFromThursday.getSelectionModel().getSelectedItem() == "PM"){
             in = "PM";
             numFrom = 12;
         }
         
         if(cbToThursday.getSelectionModel().getSelectedItem() == "PM"){
             out = "PM";
             numTo = 12;
         }
         int inHours = this.tfHoursFromThursday.getHours();
         int inMin = this.tfMinFromThursday.getMinutes();
         int outHours = this.tfHoursToThursday.getHours();
         int outMin = this.tfMinToThursday.getMinutes();
         Time time = new Time(inHours,inMin,in,
                              outHours,outMin,out);
         
         business.setThursday(time);
         
       return IsItInRange(tfHoursFromThursday.getHours() + numFrom,
       tfMinFromThursday.getMinutes(),tfHoursToThursday.getHours() + numTo,
       tfMinToThursday.getMinutes(),"Thursday"); 
             
     }
        private boolean ValidateWednesday(){
        if(!hbWednesdayConfig.isVisible()){
            return false;
        }
            int numFrom = 0;
            int numTo = 0;
            String in = "AM";
            String out = "AM";
         if(cbFromWednesday.getSelectionModel().getSelectedItem() == "PM"){
             numFrom = 12;
             in = "PM";
         }
         
         if(cbToWednesday.getSelectionModel().getSelectedItem() == "PM"){
             numTo = 12;
             out = "PM";
         }
         int inHours = tfHoursFromWednesday.getHours();
         int inMin = tfMinFromWednesday.getMinutes();
         int outHours = tfHoursToWednesday.getHours();
         int outMin = tfMinToWednesday.getMinutes();
         Time time = new Time(inHours,inMin,in,
                              outHours,outMin,out);
         business.setWednesday(time);
         
       return IsItInRange(tfHoursFromWednesday.getHours() + numFrom,
       tfMinFromWednesday.getMinutes(),tfHoursToWednesday.getHours() + numTo,
       tfMinToWednesday.getMinutes(),"Wednesday"); 
             
     }
   
       private boolean ValidateTuesday(){
        if(!hbTuesdayConfig.isVisible()){
            return false;
        }
            int numFrom = 0;
            int numTo = 0;
            String in = "AM";
            String out = "AM";
         if(cbFromTuesday.getSelectionModel().getSelectedItem() == "PM"){
             numFrom = 12;
             in = "PM";
         }
         
         if(cbToTuesday.getSelectionModel().getSelectedItem() == "PM"){
             numTo = 12;
             out = "PM";
         }
         
         int inHours = tfHoursFromTuesday.getHours();
         int inMin = tfMinFromTuesday.getMinutes();
         int outHours = tfHoursToTuesday.getHours();
         int outMin = tfMinToTuesday.getMinutes();
         Time time = new Time(inHours,inMin,in,
                              outHours,outMin,out);
         business.setTuesday(time);
         
       return IsItInRange(tfHoursFromTuesday.getHours() + numFrom,
       tfMinFromTuesday.getMinutes(),tfHoursToTuesday.getHours() + numTo,
       tfMinToTuesday.getMinutes(),"Tuesday"); 
             
     }
     private boolean ValidateMonday(){
        if(!hbMondayConfig.isVisible()){
            return false;
        }
            
            int numFrom = 0;
            int numTo = 0;
            String in = "AM";
            String out = "AM";
         if(cbFromMonday.getSelectionModel().getSelectedItem() == "PM"){
             numFrom = 12;
             in = "PM";
         }
         
         if(cbToMonday.getSelectionModel().getSelectedItem() == "PM"){
             numTo = 12;
             out = "PM";
         }
         int inHours = tfHoursFromMonday.getHours();
         int inMin = tfMinFromMonday.getMinutes();
         int outHours = tfHoursToMonday.getHours();
         int outMin = tfMinToMonday.getMinutes();
         Time time = new Time(inHours, inMin,in,
                              outHours,outMin,out);
         business.setMonday(time);
         
       return IsItInRange(tfHoursFromMonday.getHours() + numFrom,
       tfMinFromMonday.getMinutes(),tfHoursToMonday.getHours() + numTo,
       tfMinToMonday.getMinutes(),"Monday"); 
             
     }
private boolean IsItInRange(int inHour,int inMinutes , int outHour,int outMinutes, String date){
                
    if (inHour > outHour) {
        ShowWarning("Opening bussines hours can not be greater than " , date);
        return true;

    } else if (inHour == outHour && inMinutes == outMinutes) {
        ShowWarning( "Opening and closing could not be the same time", date);
       return true;
    }else if (inHour == outHour && inMinutes > outMinutes) {
        ShowWarning("Minutes opening could not be greater than closing. When hours"
                + " is the same", date);
       return true;
    }else if(outHour == 24 && outMinutes >= 0){
        ShowWarning("Day end at 11:59 PM for closing hours", date);
        return true;
    }else if(inHour == 24 && inMinutes >= 0){
        ShowWarning("Day end at 11:59 PM for opening hours", date);
        return true;
    }           
      return false;              
}

private void ShowWarning(String message,String date){
           Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(dialogStage);
            alert.setTitle("Warning");
            alert.setHeaderText(date);
            alert.setContentText(message);
            alert.showAndWait();
}
    public void initialize() {
       this.cbToMonday.getItems().addAll("AM","PM");
       this.cbToMonday.setValue("PM");
        this.cbFromMonday.getItems().addAll("AM","PM");
        this.cbFromMonday.setValue("AM");
        this.cbFromTuesday.getItems().addAll("AM","PM");
        this.cbFromTuesday.setValue("AM");
        this.cbToTuesday.setValue("PM");        
        this.cbToTuesday.getItems().addAll("AM","PM");
        this.cbFromWednesday.setValue("AM");
        this.cbFromWednesday.getItems().addAll("AM","PM");
        this.cbToWednesday.setValue("PM");
        this.cbToWednesday.getItems().addAll("AM","PM");
        this.cbToThursday.setValue("PM");
        this.cbToThursday.getItems().addAll("AM","PM");
        this.cbFromThursday.setValue("AM");
        this.cbFromThursday.getItems().addAll("AM","PM");
        this.cbToFriday.setValue("PM");
        this.cbToFriday.getItems().addAll("AM","PM");
        this.cbFromFriday.setValue("AM");
        this.cbFromFriday.getItems().addAll("AM","PM");
        this.cbFromSaturday.setValue("AM");
        this.cbFromSaturday.getItems().addAll("AM","PM");
        this.cbToSaturday.setValue("PM");
        this.cbToSaturday.getItems().addAll("AM","PM");
        this.cbFromSunday.setValue("AM");
        this.cbFromSunday.getItems().addAll("AM","PM");
        this.cbToSunday.setValue("PM");
        this.cbToSunday.getItems().addAll("AM","PM");
        tfHoursToMonday.setText("11");
        tfHoursToTuesday.setText("11");
        tfHoursToWednesday.setText("11");
        tfHoursToThursday.setText("11");
        tfHoursToFriday.setText("11");
        tfHoursToSaturday.setText("11");
        tfHoursToSunday.setText("11");
    
        this.business = new Schedule();
    } 
  
   public  void setDialogStage(Stage dialogStage) {
       this.dialogStage = dialogStage;
    }
   @FXML
    private void cbMondayisSelected(ActionEvent event) { 
         if(this.cbClosedMonday.isSelected())  {
             this.hbMondayConfig.setVisible(false);
         }else{
             this.hbMondayConfig.setVisible(true);
            
         }
    }

    @FXML
    private void cbTuesdayisSelected(ActionEvent event) {
        if(this.cbClosedTuesday.isSelected())  {
             this.hbTuesdayConfig.setVisible(false);
         }else{
             this.hbTuesdayConfig.setVisible(true);
         }
    }
     @FXML
    private void cbWednesdayisSelected(ActionEvent event) {
        if(this.cbClosedWednesday.isSelected())  {
             this.hbWednesdayConfig.setVisible(false);
         }else{
             this.hbWednesdayConfig.setVisible(true);
         }
    }
    @FXML
    private void cbThursdayisSelected(ActionEvent event) {
        if(this.cbClosedThursday.isSelected())  {
             this.hbThursdayConfig.setVisible(false);
         }else{
             this.hbThursdayConfig.setVisible(true);
         }
    }
    @FXML
    private void cbFridayisSelected(ActionEvent event) {
        if(this.cbClosedFriday.isSelected())  {
             this.hbFridayConfig.setVisible(false);
         }else{
             this.hbFridayConfig.setVisible(true);
         }
    }
    @FXML
    private void cbSaturdayisSelected(ActionEvent event) {
        if(this.cbClosedSaturday.isSelected())  {
             this.hbSaturdayConfig.setVisible(false);
         }else{
             this.hbSaturdayConfig.setVisible(true);
         }
    }
    @FXML
    private void cbSundayisSelected(ActionEvent event) {
        if(this.cbClosedSunday.isSelected())  {
             this.hbSundayConfig.setVisible(false);
             
         }else{
             this.hbSundayConfig.setVisible(true);
         }
    }
}
