/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package mx.uv.fei.GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import mx.uv.fei.DataAccess.AdvanceDAO;
import mx.uv.fei.Logic.Advance;
import mx.uv.fei.Logic.AdvanceFile;
import mx.uv.fei.Logic.AdvanceHolder;
import sgerypg.SGERYPG;

/**
 * FXML Controller class
 *
 * @author yusgu
 */
public class AdvanceDataController implements Initializable {

    @FXML
    private TextField txtTitle;

    @FXML
    private TextArea txtpDescription;
    
    @FXML
    private HBox hbFilesVisual;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            receiveData();
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(AdvanceDataController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    @FXML
    private void receiveData() throws SQLException{
        
        AdvanceHolder holder = AdvanceHolder.getInstance();
        Advance globalAdvance = holder.getAdvance();
        this.txtTitle.setText(globalAdvance.getTitle());
        this.txtpDescription.setText(globalAdvance.getDescription());
        fillData();
    }
    
    @FXML
    public void backToList() throws IOException{
        SGERYPG.changeView("/mx/uv/fei/GUI/AdvancesStudentView");
    }
    
    @FXML
    public void fillData() throws SQLException{
        AdvanceHolder holder = AdvanceHolder.getInstance();
        Advance globalAdvance = holder.getAdvance();
        AdvanceDAO advanceDAO = new AdvanceDAO();
        ArrayList<AdvanceFile> files = advanceDAO.getAllFilesAvance(globalAdvance);
        
        for(int i = 0; i < files.size(); i++){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("FileVisualItem.FXML"));
            
            try{
                HBox hbox = fxmlLoader.load();
                FileVisualItemController aIC = fxmlLoader.getController();
                aIC.setData(files.get(i).getOriginalName());
                hbFilesVisual.getChildren().add(hbox);
            }catch(IOException e){
                System.out.println("e");
            } 
        }
    }
}
