/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package mx.uv.fei.GUI;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import mx.uv.fei.DataAccess.AdvanceDAO;
import mx.uv.fei.Logic.Advance;
import mx.uv.fei.Logic.User;
import java.util.UUID;
import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import mx.uv.fei.Logic.AdvanceFile;

/**
 * FXML Controller class
 *
 * @author yusgu
 */
public class AdvancesStudentViewController implements Initializable {

    User user = new User();
    int receptionalWork = 1;
   
    ArrayList<File> files = new ArrayList<>();
    
    @FXML
    private VBox sldContainer;
    
    @FXML
    private TextField txtTitle;

    @FXML
    private TextArea txtpDescription;
   
    @FXML
    private Pane pnlListAdvances;

    @FXML
    private Pane pnlRegisterAdvance;

    @FXML
    private HBox hbFilesVisual;    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        user.setIdUser(1);
        try {
            fillData();
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(AdvancesStudentViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    @FXML
    public void fillData() throws SQLException{
        AdvanceDAO advanceDAO = new AdvanceDAO();
        List<Advance> advances = advanceDAO.getAllAdvance(user);
        
        for(int i = 0; i < advances.size(); i++){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("AdvanceItem.FXML"));
            
            try{
                Pane pane = fxmlLoader.load();
                AdvanceItemController aIC = fxmlLoader.getController();
                aIC.setData(advances.get(i));
                sldContainer.getChildren().add(pane);
            }catch(IOException e){
                System.out.println("e");
            }
        }
    }
    
    
    @FXML
    void openMakeAdvance(ActionEvent event) {
        pnlListAdvances.setVisible(false);
        pnlRegisterAdvance.setVisible(true);
    }
    
    @FXML
    public void getFiles(){    
        FileChooser fc = new FileChooser();
        Stage stage = (Stage) sldContainer.getScene().getWindow();
        File file = fc.showOpenDialog(stage);
        if(file != null){
            files.add(file);
            addVisualFile(file.getName());
        }
    }
    
    @FXML
    public void copyFiles() throws SQLException {
        String src = "src\\mx\\uv\\fei\\Files";
        for (int i = 0; i < files.size(); i++) {
            String originalName = files.get(i).getName();
            String format = originalName.substring(originalName.lastIndexOf("."));
            UUID uuid = UUID.randomUUID();  
            File sourceFile = files.get(i);
            File destFile = new File(src + File.separator + uuid.toString() + format);
            copyFiletoDirectory(sourceFile,destFile);
            registerFilesDataBase(destFile.toString(),originalName, uuid.toString());
        }
        files.clear();
    }
    
    @FXML
    public void addVisualFile(String name){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("FileVisualItem.FXML"));
        try{
            HBox hbox = fxmlLoader.load();
            FileVisualItemController aIC = fxmlLoader.getController();
            aIC.setData(name);
            hbFilesVisual.getChildren().add(hbox);
        }catch(IOException e){
            System.out.println("e");
        } 
    }
    
    private void copyFiletoDirectory(File sourceFile, File destFile){
        try (InputStream is = new FileInputStream(sourceFile);
            OutputStream os = new FileOutputStream(destFile)) {
            byte[] buffer = new byte[4096];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } catch (IOException ex) {
            Logger.getLogger(AdvancesStudentViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void registerFilesDataBase(String source, String name, String id) throws SQLException{
        AdvanceFile file = new AdvanceFile();
        AdvanceDAO dao = new AdvanceDAO();
        int idAvance = dao.getLastID(user.getIdUser());
        file.setSource(source);
        file.setOriginalName(name);
        file.setNewName(id);
        file.setIdAdvance(idAvance);
        
        dao.registerFileAdvance(file);
        
    }
    
    @FXML
    public int registerAdvanceDataBase() throws SQLException{
        int result;
        Advance advance = new Advance();
        advance.setTitle(this.txtTitle.getText());
        advance.setDescription(this.txtpDescription.getText());
        advance.setIdStudent(user.getIdUser());
        advance.setIdReceptionWork(receptionalWork);
        AdvanceDAO dao = new AdvanceDAO();
        
        result = dao.registerAdvance(advance);
        
        return result;
    }
    
    @FXML
    public void registerAdvance() throws SQLException{
        registerAdvanceDataBase();
        copyFiles();
        clearWindow();
    }
    
    @FXML
    void closeWindow(){
        pnlListAdvances.setVisible(true);
        pnlRegisterAdvance.setVisible(false);
    }
    
    @FXML
    public void clearWindow() throws SQLException{
        this.txtTitle.setText("");
        this.txtpDescription.setText("");
        files.clear();
        sldContainer.getChildren().clear();
        fillData();
        hbFilesVisual.getChildren().clear();
        closeWindow();
    }
}

