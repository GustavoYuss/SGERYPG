/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package mx.uv.fei.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import mx.uv.fei.Logic.Advance;
import mx.uv.fei.Logic.AdvanceHolder;
import sgerypg.SGERYPG;


/**
 * FXML Controller class
 *
 * @author yusgu
 */
public class AdvanceItemController implements Initializable {

    
    @FXML
    private Button btnDetails;

    @FXML
    private Label iblTitle;

    @FXML
    private Text txtpDescription;
    
    Advance globalAdvance;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setData(Advance advance){
        globalAdvance = advance;
        iblTitle.setText(advance.getTitle());
        txtpDescription.setText(advance.getDescription());
    }
    
    @FXML
    public void sendData(ActionEvent event) throws IOException {    
        AdvanceHolder holder = AdvanceHolder.getInstance();
        holder.setAdvance(globalAdvance);
        SGERYPG.changeView("/mx/uv/fei/GUI/AdvanceData");
    }
}
