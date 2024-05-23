package com.system.demo.controllers.modal;

import com.system.demo.PrefectInfoMgtApplication;
import com.system.demo.appl.facade.prefect.offense.OffenseFacade;
import com.system.demo.appl.model.offense.Offense;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class EditOffenseController implements Initializable {
    @FXML
    private TextField offenseField;

    @FXML
    private ComboBox<String> comboBox;

    private Offense offense;

    private OffenseFacade offenseFacade;

    @FXML
    protected void saveEditOffenseClicked(ActionEvent event) {
        PrefectInfoMgtApplication app = new PrefectInfoMgtApplication();
        offenseFacade = app.getOffenseFacade();

        Offense editOffense = new Offense();
        editOffense.setId(offense.getId());
        editOffense.setType(comboBox.getValue());
        editOffense.setDescription(offenseField.getText());

//        System.out.println(offense.getId());
//        System.out.println(offense.getType());
//        System.out.println(offense.getDescription());

        try {
//            System.out.println(editOffense.getId());
//            System.out.println(editOffense.getType());
//            System.out.println(editOffense.getDescription());
            offenseFacade.updateOffense(editOffense);
        } catch(Exception ex) {
            ex.printStackTrace();;
        }
        finally {
            try {
                Stage previousStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                previousStage.close();

                Stage dashboardStage = new Stage();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/views/OffenseList.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                dashboardStage.setScene(scene);

                dashboardStage.initStyle(StageStyle.UNDECORATED);

                dashboardStage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBox.getItems().addAll("Minor", "Major");
    }

    public void setOffense(Offense offense) {
        this.offense = offense;
        offense.getId();
        comboBox.setValue(offense.getType());
        offenseField.setText(offense.getDescription());
    }

    @FXML
    protected void handleCancelEditOffenseClicked(MouseEvent event) {
        try {
            Stage previousStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            previousStage.close();

            Stage dashboardStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/OffenseList.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            dashboardStage.setScene(scene);
            dashboardStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
