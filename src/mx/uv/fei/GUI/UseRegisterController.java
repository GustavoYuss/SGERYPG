/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package mx.uv.fei.GUI;


import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import mx.uv.fei.DataAccess.AccountDAO;
import mx.uv.fei.DataAccess.StatusDAO;
import mx.uv.fei.DataAccess.UserDAO;
import mx.uv.fei.Logic.Account;
import mx.uv.fei.Logic.Status;
import mx.uv.fei.Logic.StatusConverter;
import mx.uv.fei.Logic.User;

import mx.uv.fei.Logic.UserTable;
/**
 * FXML Controller class
 *
 * @author yusgu
 */

public class UseRegisterController implements Initializable{

    @FXML
    private Button btnCancel;    
    
    @FXML
    private Button btnCreateuser;
    
    @FXML
    private Button btnCreateUserWindow;
    
    @FXML
    private Button btnConsultUser;
    
    @FXML
    private Button btnUserUpdate;
    
    @FXML
    private ComboBox<Status> cboUserStatus;    
    
    @FXML
    private ComboBox<Status> cboUserStatusCreation;
    
    @FXML
    private TableColumn<UserTable, Integer> tblId;
    
    @FXML
    private TableColumn<UserTable, String> tblClave;
    
    @FXML
    private TableColumn<UserTable, String> tblEmail;
    
    @FXML
    private TableColumn<UserTable, String> tblFullName;

    @FXML
    private TableColumn<UserTable, String> tblStatus;

    @FXML
    private TableColumn<UserTable, String> tblType;

    @FXML
    private TableView<UserTable> tblUsers;
    
    @FXML
    private Pane pnlCreateuser;

    @FXML
    private Pane pnlUsers;
    
    @FXML
    private Pane pnlUserData;

    @FXML
    private Pane pnlUserProject;

    @FXML
    private TextField txtClave;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtMiddleName;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSurname;  
    
    @FXML
    private TextField txtUserClave;

    @FXML
    private TextField txtUserEmail;

    @FXML
    private TextField txtUserLastName;

    @FXML
    private TextField txtUserMiddleName;

    @FXML
    private TextField txtUserName;

    @FXML
    private TextField txtUserSurname;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
        
        ArrayList<Status> statusList;
        filtable();
        
        try{
            StatusDAO status = new StatusDAO();   
            statusList = status.getAllStatus();
            cboUserStatusCreation.getItems().addAll(statusList);
            cboUserStatusCreation.setConverter(new StatusConverter());
            cboUserStatus.getItems().addAll(statusList);
            cboUserStatus.setConverter(new StatusConverter());
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }    
    
    @FXML
    private void filtable(){
        this.tblUsers.getItems().clear();
        
        UserDAO table = new UserDAO();
        
        this.tblId.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.tblFullName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        this.tblStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        this.tblType.setCellValueFactory(new PropertyValueFactory<>("userType"));
        this.tblEmail.setCellValueFactory(new PropertyValueFactory<>("hola"));
        this.tblClave.setCellValueFactory(new PropertyValueFactory<>("clave"));

        try{
            ObservableList<UserTable> items = table.getUsersTable();
            this.tblUsers.setItems(items);
        }catch(SQLException e){
            System.out.println("HOLA");
        }     
    }
    
    @FXML
    public void createUser(ActionEvent event) {
     
        User user = new User();
        AccountDAO accountDAO = new AccountDAO();
        UserDAO userDAO = new UserDAO();
        user.setFirstName(this.txtName.getText());
        user.setMiddleName(this.txtMiddleName.getText());
        user.setLastName(this.txtLastName.getText());
        user.setSecondSurname(this.txtSurname.getText());
        user.seteMail(this.txtEmail.getText());
        user.setClave(Integer.parseInt(this.txtClave.getText()));
        user.setIdStatus(Integer.parseInt(this.cboUserStatusCreation.getSelectionModel().getSelectedItem().toString()));
        Account account; 
        
        try {
            userDAO.registerUser(user);
            user.setIdUser(userDAO.getIdUser(user));
            account = new Account(user);
            accountDAO.registerAccount(account);
        } catch (Exception ex) {
            Logger.getLogger(UseRegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeWindow();
    }

    @FXML
    public void bindButton(){
        BooleanBinding booleanBinding = this.txtName.textProperty().isEqualTo("").or(this.txtLastName.textProperty().isEqualTo(""))
                .or(this.txtSurname.textProperty().isEqualTo("")).or(this.txtClave.textProperty().isEqualTo("")).or
                (this.txtEmail.textProperty().isEqualTo(""));
        btnCreateuser.disableProperty().bind(booleanBinding);
    }
    
    @FXML
    public void bindUpdateButton(){
        BooleanBinding booleanBinding = this.txtUserName.textProperty().isEqualTo("").or(this.txtUserLastName.textProperty().isEqualTo(""))
                .or(this.txtUserSurname.textProperty().isEqualTo("")).or(this.txtUserClave.textProperty().isEqualTo("")).or
                (this.txtUserEmail.textProperty().isEqualTo(""));
        btnUserUpdate.disableProperty().bind(booleanBinding);
    }
    
    @FXML
    private void closeWindow() {
        filtable();
        this.pnlCreateuser.setVisible(false);
        this.pnlUserData.setVisible(false);
        this.pnlUserProject.setVisible(false);
        this.pnlUsers.setVisible(true);
    }
    
    @FXML
    private void openCreateUserWindow(ActionEvent event) {
        emptyUserData();
        bindButton();    
        
        this.pnlCreateuser.setVisible(true);
        this.pnlUsers.setVisible(false);
        this.cboUserStatusCreation.getSelectionModel().select(0);
    }    
    
    @FXML
    void openUserDataWindow(ActionEvent event) {   
        
        if(this.tblUsers.getSelectionModel().getSelectedItem() != null){
            User user = makeAuxUser();
            bindUpdateButton();
            this.pnlUserData.setVisible(true);
            this.pnlUserProject.setVisible(false);
            this.pnlUsers.setVisible(false);
            fillUserData(user);
        }
        
    }    
    
    @FXML
    private User makeAuxUser(){
        UserDAO dao = new UserDAO();
        User user = new User();
        int id = this.tblUsers.getSelectionModel().getSelectedItem().getId();
        
        try{
        user = dao.getUserById(id);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
        user.setIdUser(id);
        
        return user;
    }
    
    @FXML
    private void updateUserData(){
        
        User user = new User();
        user.setIdUser(makeAuxUser().getIdUser());
        user.setFirstName(this.txtUserName.getText());
        user.setMiddleName(this.txtUserMiddleName.getText());
        user.setLastName(this.txtUserLastName.getText());
        user.setSecondSurname(this.txtUserSurname.getText());
        user.seteMail(this.txtUserEmail.getText());
        user.setClave(Integer.parseInt(this.txtUserClave.getText()));
        user.setIdStatus(Integer.parseInt(this.cboUserStatus.getSelectionModel().getSelectedItem().toString()));
        
        UserDAO userDAO = new UserDAO();
        try{
            int result = userDAO.updateUser(user);   
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        closeWindow();
    }
    
    @FXML
    private void fillUserData(User user){ 
        this.txtUserName.setText(user.getFirstName());
        this.txtUserMiddleName.setText(user.getMiddleName());
        this.txtUserLastName.setText(user.getLastName());
        this.txtUserSurname.setText(user.getSecondSurname());
        this.txtUserEmail.setText(user.geteMail());
        this.txtUserClave.setText(Integer.toString(user.getClave()));
        this.cboUserStatus.getSelectionModel().select(user.getIdStatus()-1);
    }
    
    @FXML
    private void emptyUserData(){ 
        this.txtName.setText("");
        this.txtMiddleName.setText("");
        this.txtLastName.setText("");
        this.txtSurname.setText("");
        this.txtEmail.setText("");
        this.txtClave.setText("");
    }    
}
