/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedulerbysalvador;

import dataClass.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import utilities.HoursTextField;
import utilities.MinTextField;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * @author salsa
 */
public class FXMLDocumentController implements Initializable {

    @FXML private ToggleGroup week;
    @FXML private HoursTextField tfHoursOut;
    @FXML private HoursTextField tfHoursIn;
    @FXML private MinTextField tfMinutesIn;
    @FXML private RadioButton rbSunday;
    @FXML private ChoiceBox<String> cbInTime;
    @FXML private RadioButton rbWednesday;
    @FXML private CheckBox cbOff;
    @FXML private Button btnEmployee;
    @FXML private RadioButton rbSaturday;
    @FXML private MinTextField tfMinutesOut;
    @FXML private Label lbWarning;
    @FXML private RadioButton rbTuesday;
    @FXML private Label lbReview;
    @FXML private ChoiceBox<String> cbOutTime;
    @FXML private RadioButton rbFriday;
    @FXML private RadioButton rbMonday;
    @FXML private RadioButton rbThursday;
    @FXML private ListView<String> lvEmployees;
    @FXML private TextArea taScheduleHolder;
    private List<Schedule> schedules = new ArrayList<Schedule>();
    private Stage stage;
    private Schedule bussineshours = null;
    private int selection = 0;
    private boolean change = false;

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    private void emptyTf(){
        this.tfHoursIn.setText("");
        this.tfHoursOut.setText("");
        this.tfMinutesIn.setText("");
        this.tfMinutesOut.setText("");
    }
    private void emptyRb(){
        this.rbSunday.setSelected(false);
        this.rbSaturday.setSelected(false);
        this.rbFriday.setSelected(false);
        this.rbThursday.setSelected(false);
        this.rbWednesday.setSelected(false);
        this.rbTuesday.setSelected(false);
        this.rbMonday.setSelected(false);
        
    }
    private Schedule creatingEmployeeDialog() {
        EmployeeController controller = null;

        try {
            //Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FXMLDocumentController.class.getResource("Employee.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            //Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Employee Time");
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            
            //Set the person into the controller.
            controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setBussinesTotalHours(bussineshours);
            // pass the dialog to close the dialog in the dialog class
            controller.setDialogStage(dialogStage);
            //  Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

        } catch (IOException e) {
            System.out.println(e.getCause());
            e.printStackTrace();

        }

        if (controller.isDialogOk()) {
            emptyTf();
            emptyRb();
            return controller.getSchedule();
        } else {
            return null;
        }
    }

    private void settingSChedule(Schedule schedule) {
        schedules.add(schedule);
        this.lvEmployees.getItems().add(schedule.getName());

    }

    private String workSchedule(Schedule schedule) {
        return schedule.getName() + " monday: " + schedule.getMondayTime().getFromatedTime() + " tuesday: "
                + schedule.getTuesdayTime().getFromatedTime() + " wednesday: " + schedule.getWednesdayTime().getFromatedTime()
                + " Thursday: " + schedule.getThursdayTime().getFromatedTime() + " Friday: " + schedule.getFridayTime().getFromatedTime()
                + " Saturday: " + schedule.getSaturdayTime().getFromatedTime() + " Sunday: " + schedule.getSundayTime().getFromatedTime() + "\n";
    }

    private String totalHourWork(Schedule schedule) {
        return "Total Hours work:      " + schedule.getTotalHoursOfWeek() + "      monday total: " + schedule.getTotalHoursMonday()
                + "      tuesday total: " + schedule.getTotalHoursTuesday() + "      wednesday total: " + schedule.getTotalHoursWednesday()
                + "      thursday total: " + schedule.getTotalHoursThursday() + "      friday total: " + schedule.getTotalHoursFriday()
                + "      saturday total: " + schedule.getTotalHoursSaturday() + "      sunday total: " + schedule.getTotalHoursSunday() + "\n\n";
        
    }

    private void printingSchedules(Schedule schedule) {
        String print = "";
        for (Schedule schedule1 : schedules) {
            print = print + (workSchedule(schedule1) + totalHourWork(schedule1));

        }
        this.taScheduleHolder.setText(print);
    }

    @FXML
    private void btnAddEmployee(ActionEvent event) {
        Schedule schedule = creatingEmployeeDialog();
        settingSChedule(schedule);
        printingSchedules(schedule);

    }

    @FXML
    void rbFridaySelected(ActionEvent event) {
        this.selection = this.lvEmployees.getSelectionModel().getSelectedIndex();
        this.change = false;
        emptyTf();
        if (this.selection > -1) {
            
            Schedule schedule = schedules.get(selection);
            int in = transformTo12(schedule.getFridayTime().getInHours());
            int out = transformTo12(schedule.getFridayTime().getOutHours());
            isThirsAmOrPmFrom(schedule.getFridayTime().getInHours());
            isThirsAmOrPmTo(schedule.getFridayTime().getOutHours());
            this.tfHoursIn.setText(in + "");
            this.tfMinutesIn.setText(schedule.getFridayTime().getInMin() + "");
            this.tfHoursOut.setText(out + "");
            this.tfMinutesOut.setText(schedule.getFridayTime().getOutMin() + "");

        }
        this.change = true;
    }

    private int transformTo12(int num) {
        if (num > 12) {
            return (num - 12);
        } else {
            return num;
        }
    }

    private void isThirsAmOrPmFrom(int num) {
        String typeOfTime;
        if (num > 12) {
            typeOfTime = "PM";
            this.cbInTime.getSelectionModel().select(1);
        } else {
            typeOfTime = "AM";
            this.cbInTime.getSelectionModel().select(0);
        }
    }

    private void isThirsAmOrPmTo(int num) {
        String typeOfTime;
        if (num > 12) {
            typeOfTime = "PM";
            this.cbOutTime.getSelectionModel().select(1);
        } else {
            typeOfTime = "AM";
            this.cbOutTime.getSelectionModel().select(0);
        }
    }

    @FXML
    void rbSaturdaySelected(ActionEvent event) {
        this.selection = this.lvEmployees.getSelectionModel().getSelectedIndex();
        this.change = false;
          emptyTf();
        if (this.selection > -1) {
            this.change = false;
            Schedule schedule = schedules.get(selection);
            int in = transformTo12(schedule.getSaturdayTime().getInHours());
            int out = transformTo12(schedule.getSaturdayTime().getOutHours());
            isThirsAmOrPmFrom(schedule.getSaturdayTime().getInHours());
            isThirsAmOrPmTo(schedule.getSaturdayTime().getOutHours());
            this.tfHoursIn.setText(in + "");
            this.tfMinutesIn.setText(schedule.getSaturdayTime().getInMin() + "");
            this.tfHoursOut.setText(out + "");
            this.tfMinutesOut.setText(schedule.getSaturdayTime().getOutMin() + "");

        }
        this.change = true;
    }

    @FXML
    void rbSundaySelected(ActionEvent event) {
        this.selection = this.lvEmployees.getSelectionModel().getSelectedIndex();
        this.change = false;
        emptyTf();
        if (this.selection > -1) {
            this.change = false;
            Schedule schedule = schedules.get(selection);
            int in = transformTo12(schedule.getSundayTime().getInHours());
            int out = transformTo12(schedule.getSundayTime().getOutHours());
            isThirsAmOrPmFrom(schedule.getSundayTime().getInHours());
            isThirsAmOrPmTo(schedule.getSundayTime().getOutHours());
            this.tfHoursIn.setText(in + "");
            this.tfMinutesIn.setText(schedule.getSundayTime().getInMin() + "");
            this.tfHoursOut.setText(out + "");
            this.tfMinutesOut.setText(schedule.getSundayTime().getOutMin() + "");
        
        }
        this.change = true;
    }

    @FXML
    void rbThursdaySelected(ActionEvent event) {
        this.selection = this.lvEmployees.getSelectionModel().getSelectedIndex();
        this.change = false;
        emptyTf();
        if (this.selection > -1) {
            
            Schedule schedule = schedules.get(selection);
            int in = transformTo12(schedule.getThursdayTime().getInHours());
            int out = transformTo12(schedule.getThursdayTime().getOutHours());
            isThirsAmOrPmFrom(schedule.getThursdayTime().getInHours());
            isThirsAmOrPmTo(schedule.getThursdayTime().getOutHours());
            this.tfHoursIn.setText(in + "");
            this.tfMinutesIn.setText(schedule.getThursdayTime().getInMin() + "");
            this.tfHoursOut.setText(out + "");
            this.tfMinutesOut.setText(schedule.getThursdayTime().getOutMin() + "");

        }
        this.change = true;
    }

    @FXML
    void rbWednesdaySelected(ActionEvent event) {
        this.selection = this.lvEmployees.getSelectionModel().getSelectedIndex();
          this.change = false;
         emptyTf();
        if (this.selection > -1) {
          
            Schedule schedule = schedules.get(selection);
            int in = transformTo12(schedule.getWednesdayTime().getInHours());
            int out = transformTo12(schedule.getWednesdayTime().getOutHours());
            isThirsAmOrPmFrom(schedule.getWednesdayTime().getInHours());
            isThirsAmOrPmTo(schedule.getWednesdayTime().getOutHours());
            this.tfHoursIn.setText(in + "");
            this.tfMinutesIn.setText(schedule.getWednesdayTime().getInMin() + "");
            this.tfHoursOut.setText(out + "");
            this.tfMinutesOut.setText(schedule.getWednesdayTime().getOutMin() + "");

        }
        this.change = true;
    }

    @FXML
    void rbTuesdaySelected(ActionEvent event) {
        this.selection = this.lvEmployees.getSelectionModel().getSelectedIndex();
        this.change = false;
         emptyTf();
        if (this.selection > -1) {
            
            Schedule schedule = schedules.get(selection);
            int in = transformTo12(schedule.getTuesdayTime().getInHours());
            int out = transformTo12(schedule.getTuesdayTime().getOutHours());
            isThirsAmOrPmFrom(schedule.getTuesdayTime().getInHours());
            isThirsAmOrPmTo(schedule.getTuesdayTime().getOutHours());
            this.tfHoursIn.setText(in + "");
            this.tfMinutesIn.setText(schedule.getTuesdayTime().getInMin() + "");
            this.tfHoursOut.setText(out + "");
            this.tfMinutesOut.setText(schedule.getTuesdayTime().getOutMin() + "");
       
        }
        this.change = true;
    }

    @FXML
    void rbMondaySelected(ActionEvent event) {
        this.selection = this.lvEmployees.getSelectionModel().getSelectedIndex();
        this.change = false;
         emptyTf();
        if (this.selection > -1) {
          
            Schedule schedule = schedules.get(selection);
            int in = transformTo12(schedule.getMondayTime().getInHours());
            int out = transformTo12(schedule.getMondayTime().getOutHours());
            isThirsAmOrPmFrom(schedule.getMondayTime().getInHours());
            isThirsAmOrPmTo(schedule.getMondayTime().getOutHours());
            this.tfHoursIn.setText(in + "");
            this.tfMinutesIn.setText(schedule.getMondayTime().getInMin() + "");
            this.tfHoursOut.setText(out + "");
            this.tfMinutesOut.setText(schedule.getMondayTime().getOutMin() + "");

        }
        this.change = true;
    }

    private void creatingBussinesHoursDialog() {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FXMLDocumentController.class.getResource("FXMLDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit BussinesHours");
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
     
            // Set the person into the controller.
            Dialog controller = loader.getController();
            controller.setDialogStage(dialogStage);

            // pass the dialog to close the dialog in the dialog class
            // controller.setDialogStage(dialogStage);
            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            if (controller.okButtonClick()) {
                bussineshours = controller.getBusiness();

            }

        } catch (IOException e) {
            // System.out.println( e.getCause()); 
            e.printStackTrace();

        }

        if (bussineshours == null) {
            this.btnEmployee.setVisible(false);
        } else {
            this.btnEmployee.setVisible(true);
        }
    }

    private String printBussinesHours() {
        return  "monday "+ bussineshours.getMondayTime().getFromatedTime()+ " total monday " + bussineshours.getTotalHoursMonday()+
                "\ntuesday "+ bussineshours.getTuesdayTime().getFromatedTime() + " total tuesday " + bussineshours.getTotalHoursTuesday()+
                "\nwednesday " + bussineshours.getWednesdayTime().getFromatedTime() + " total wednesday " + bussineshours.getTotalHoursWednesday()+
                "\nthursday "+ bussineshours.getThursdayTime().getFromatedTime() + " total thursday " + bussineshours.getTotalHoursThursday()+
                "\nfriday "+ bussineshours.getFridayTime().getFromatedTime()+" total friday " + bussineshours.getTotalHoursFriday()+
                "\nsaturday "+ bussineshours.getSaturdayTime().getFromatedTime()+ " total saturday " + bussineshours.getTotalHoursSaturday()+
                "\nsunday "+bussineshours.getSundayTime().getFromatedTime()+ " total sunday " + bussineshours.getTotalHoursSunday()+
                 "\nTotal Hours " + bussineshours.getTotalHoursOfWeek();
    }

    @FXML
    void btnBusinessHours(ActionEvent event) {
        creatingBussinesHoursDialog();
        if (this.bussineshours != null) {
            this.lbReview.setText(printBussinesHours());
        }
    }

    private void setinTime(Schedule schedule) {
        String time = "AM";
        if("PM".equals(this.cbInTime.getSelectionModel().getSelectedItem())){
            time = "PM";
         }
        if (this.rbMonday.isSelected()) {
            schedule.getMondayTime().setinTime12(this.tfHoursIn.getHours(), this.tfMinutesIn.getMinutes(),time);
            printingSchedules(schedule);
        } else if (this.rbTuesday.isSelected()) {
            schedule.getTuesdayTime().setinTime12(this.tfHoursIn.getHours(), this.tfMinutesIn.getMinutes(),time);
            printingSchedules(schedule);
        } else if (this.rbWednesday.isSelected()) {
            schedule.getWednesdayTime().setinTime12(this.tfHoursIn.getHours(), this.tfMinutesIn.getMinutes(),time);
            printingSchedules(schedule);
        } else if (this.rbThursday.isSelected()) {
            schedule.getThursdayTime().setinTime12(this.tfHoursIn.getHours(), this.tfMinutesIn.getMinutes(),time);
            printingSchedules(schedule);
        } else if (this.rbFriday.isSelected()) {
            schedule.getFridayTime().setinTime12(this.tfHoursIn.getHours(), this.tfMinutesIn.getMinutes(),time);
            printingSchedules(schedule);
        } else if (this.rbSaturday.isSelected()) {
            schedule.getSaturdayTime().setinTime12(this.tfHoursIn.getHours(), this.tfMinutesIn.getMinutes(),time);
            printingSchedules(schedule);
        } else {
            schedule.getSundayTime().setinTime12(this.tfHoursIn.getHours(), this.tfMinutesIn.getMinutes(),time);
            printingSchedules(schedule);
        }
    }

    private void setOutTime(Schedule schedule) {
        String time = "AM";
        if("PM".equals(this.cbOutTime.getSelectionModel().getSelectedItem())){
            time = "PM";
         }
        if (this.rbMonday.isSelected()) {
            schedule.getMondayTime().setOutTime12(this.tfHoursOut.getHours(),this.tfMinutesOut.getMinutes(),time);
            printingSchedules(schedule);
        } else if (this.rbTuesday.isSelected()) {
            schedule.getTuesdayTime().setOutTime12(this.tfHoursOut.getHours(),this.tfMinutesOut.getMinutes(),time);
            printingSchedules(schedule);
        } else if (this.rbWednesday.isSelected()) {
            schedule.getWednesdayTime().setOutTime12(this.tfHoursOut.getHours(),this.tfMinutesOut.getMinutes(),time);
            printingSchedules(schedule);
        } else if (this.rbThursday.isSelected()) {
            schedule.getThursdayTime().setOutTime12(this.tfHoursOut.getHours(),this.tfMinutesOut.getMinutes(),time);
            printingSchedules(schedule);
        } else if (this.rbFriday.isSelected()) {
            schedule.getFridayTime().setOutTime12(this.tfHoursOut.getHours(),this.tfMinutesOut.getMinutes(),time);
            printingSchedules(schedule);
        } else if (this.rbSaturday.isSelected()) {
            schedule.getSaturdayTime().setOutTime12(this.tfHoursOut.getHours(),this.tfMinutesOut.getMinutes(),time);
            printingSchedules(schedule);
        } else {
            schedule.getSundayTime().setOutTime12(this.tfHoursOut.getHours(),this.tfMinutesOut.getMinutes(),time);
            printingSchedules(schedule);
        }
    }  
    
    private void listeners(){
        
        this.tfHoursIn.textProperty().addListener((observable, oldValue, newValue) -> {
            this.selection = this.lvEmployees.getSelectionModel().getSelectedIndex();
              if(this.change == true){
                 Schedule schedule = this.schedules.get(selection);
            setinTime(schedule); 
            System.out.println("activate");
              }
            

        });

        this.tfMinutesIn.textProperty().addListener((observable, oldValue, newValue) -> {
            this.selection = this.lvEmployees.getSelectionModel().getSelectedIndex();
                if(this.change == true){
                   Schedule schedule = this.schedules.get(selection);
                  setinTime(schedule);
                }
            

        });

        this.tfMinutesOut.textProperty().addListener((observable, oldValue, newValue) -> {
            this.selection = this.lvEmployees.getSelectionModel().getSelectedIndex();

            if(this.change == true){
              Schedule schedule = this.schedules.get(selection);
            setOutTime(schedule);  
            }
            

        });

        this.tfHoursOut.textProperty().addListener((observable, oldValue, newValue) -> {
            this.selection = this.lvEmployees.getSelectionModel().getSelectedIndex();
             if(this.change == true){
                Schedule schedule = this.schedules.get(selection);
                setOutTime(schedule);  
             }
            

        });
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.cbInTime.getItems().addAll("AM", "PM");
        this.cbOutTime.getItems().addAll("AM", "PM");

        if (bussineshours == null) {
            this.btnEmployee.setVisible(false);
        } else {
            this.btnEmployee.setVisible(true);
        }
     listeners();
        
    }

}
