package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML private ImageView imageView_sample, imageView_register, imageView_login, imageView_empLogin, imageView_empRegister;
    @FXML private Circle closeCircle_sample, closeCircle_login, closeCircle_register, closeCircle_empLogin, closeCircle_empRegister;
    @FXML private Button dineInBtn_sample, takeAwayBtn_sample, managerBtn_sample, backOfficeBtn_sample, signBtn_login, registerBtn_login, signBtn_register, registerBtn_register,
            cardBtn_payment, cashBtn_payment, registerBtn_empLogin, signBtn_empLogin, registerBtn_empRegister, signBtn_empRegister;
    @FXML private TextField userField_login, userField_register, userField_empLogin, nameField_empRegister, userField_empRegister;
    @FXML private PasswordField passwordField_login, passwordField_register, passwordField_empLogin, passwordField_empRegister;
    private int count;
    private String orderRefNoRec;

    methodCalls methodCalls = new methodCalls();
    Connection connection = methodCalls.getSQLConnection();

    public Controller() throws SQLException, ClassNotFoundException {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        slideshow_sample();
        slideshow_login();
        slideshow_register();
        slideshow_empLogin();
        slideshow_empRegister();
    }

    private void slideshow_empRegister() {

        ArrayList<Image> images = new ArrayList<Image>();
        images.add(new Image("/b2.jpg"));
        images.add(new Image("/b3.jpg"));
        images.add(new Image("/b4.jpg"));
        images.add(new Image("/b1.jpg"));

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
            imageView_empRegister.setImage(images.get(count));
            count++;
            if (count == 4)
                count = 0;
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void slideshow_empLogin() {

        ArrayList<Image> images = new ArrayList<Image>();
        images.add(new Image("/b2.jpg"));
        images.add(new Image("/b3.jpg"));
        images.add(new Image("/b4.jpg"));
        images.add(new Image("/b1.jpg"));

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
            imageView_empLogin.setImage(images.get(count));
            count++;
            if (count == 4)
                count = 0;
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void slideshow_register() {
        ArrayList<Image> images = new ArrayList<Image>();
        images.add(new Image("/b2.jpg"));
        images.add(new Image("/b3.jpg"));
        images.add(new Image("/b4.jpg"));
        images.add(new Image("/b1.jpg"));

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
            imageView_register.setImage(images.get(count));
            count++;
            if (count == 4)
                count = 0;
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void slideshow_login() {
        ArrayList<Image> images = new ArrayList<Image>();
        images.add(new Image("/b2.jpg"));
        images.add(new Image("/b3.jpg"));
        images.add(new Image("/b4.jpg"));
        images.add(new Image("/b1.jpg"));

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
            imageView_login.setImage(images.get(count));
            count++;
            if (count == 4)
                count = 0;
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void slideshow_sample() {
        ArrayList<Image> images = new ArrayList<Image>();
        images.add(new Image("/b2.jpg"));
        images.add(new Image("/b3.jpg"));
        images.add(new Image("/b4.jpg"));
        images.add(new Image("/b1.jpg"));

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
            imageView_sample.setImage(images.get(count));
            count++;
            if (count == 4)
                count = 0;
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void transferMessage(String message){
        orderRefNoRec = message;
    }

    public void closeCircleClicked(MouseEvent event) {
        if (event.getSource() == closeCircle_sample)
            methodCalls.closeCircleClicked(closeCircle_sample);
        else if (event.getSource() == closeCircle_login)
            methodCalls.closeCircleClicked(closeCircle_login);
        else if (event.getSource() == closeCircle_register)
            methodCalls.closeCircleClicked(closeCircle_register);
        else if (event.getSource() == closeCircle_empLogin)
            methodCalls.closeCircleClicked(closeCircle_empLogin);
        else if (event.getSource() == closeCircle_empRegister)
            methodCalls.closeCircleClicked(closeCircle_empRegister);
    }

    public void sampleBtnClicked(MouseEvent event) throws IOException {
        if (event.getSource() == dineInBtn_sample){
            methodCalls.loadNewFXML(dineInBtn_sample, "login.fxml");
        }
        else if (event.getSource() == takeAwayBtn_sample){
            methodCalls.loadNewFXML(takeAwayBtn_sample, "login.fxml");
        }
        else if (event.getSource() == managerBtn_sample){
            methodCalls.loadNewFXMLDifferentController(managerBtn_sample, "manager.fxml", null, null, null, null);
        }
        else if (event.getSource() == backOfficeBtn_sample){
            methodCalls.loadNewFXML(backOfficeBtn_sample, "empLogin.fxml");
        }
    }

    public void loginBtnClicked(MouseEvent event) throws IOException, SQLException {
        String username = userField_login.getText();
        String password = passwordField_login.getText();
        if (event.getSource() == signBtn_login){
            if (username.equals("") || password.equals("")){
                methodCalls.informationAlert(signBtn_login, "Fields Cannot be Empty !!!");
            }
            else {
                String query = "SELECT * FROM users WHERE USERNAME = '"+username+"' AND PASSWORD = '"+password+"'";
                ResultSet resultSet = methodCalls.captureSQLResults(query, connection);
                if (resultSet.next()){
                    methodCalls.informationAlert(signBtn_login, "Successfully Logged In, Welcome.");
                    methodCalls.loadNewFXMLDifferentController(signBtn_login, "menu.fxml", username, null, null, null);
                }
                else {
                    methodCalls.informationAlert(signBtn_login, "Username and Password Don't Match !!");
                }
            }
        }
        else if (event.getSource() == registerBtn_login){
            methodCalls.loadNewFXML(registerBtn_login, "register.fxml");
        }
    }

    public void registerBtnClicked(MouseEvent event) throws IOException, SQLException {
        if (event.getSource() == registerBtn_register){
            String username = userField_register.getText();
            String password = passwordField_register.getText();
            if (username.equals("") || password.equals("")){
                methodCalls.informationAlert(registerBtn_register, "Fields Cannot be Empty !!");
            }
            else {
                String query = "SELECT * FROM users WHERE USERNAME = '"+username+"'";
                ResultSet resultSet = methodCalls.captureSQLResults(query, connection);
                if (resultSet.next()){
                    methodCalls.informationAlert(registerBtn_register, "Username not available. Try another !!");
                }
                else {
                    String query1 = "INSERT INTO users VALUES(null, '"+username+"', '"+password+"')";
                    String strQuery="create table "+username+" (ID INT NOT NULL AUTO_INCREMENT, ORDER_REF_NO VARCHAR(100) NOT NULL, PRIMARY KEY(ID));";
                    methodCalls.executeSQLStatement(query1, connection);
                    methodCalls.executeSQLStatement(strQuery, connection);
                    methodCalls.informationAlert(registerBtn_register, "Registration Successfull");
                }
            }
        }
        else if (event.getSource() == signBtn_register)
            methodCalls.loadNewFXML(signBtn_register, "login.fxml");
    }

    public void empLoginBtnClicked(MouseEvent event) throws SQLException, IOException {
        if (event.getSource() == signBtn_empLogin){
            String username = userField_empLogin.getText();
            String password = passwordField_empLogin.getText();
            if (username.equals("") || password.equals("")){
                methodCalls.informationAlert(signBtn_empLogin, "Fields Cannot be Empty !!!");
            }
            else {
                String query = "SELECT * FROM employee WHERE USERNAME = '"+username+"' AND PASSWORD = '"+password+"'";
                ResultSet resultSet = methodCalls.captureSQLResults(query, connection);
                if (resultSet.next()){
                    methodCalls.informationAlert(signBtn_empLogin, "Successfully Logged In, Welcome.");
                    methodCalls.closeButtonClicked(signBtn_empLogin);
                    methodCalls.loadNewFXMLDifferentController(signBtn_empLogin, "chef.fxml", null, null, null, username);
                }
                else {
                    methodCalls.informationAlert(signBtn_empLogin, "Username and Password Don't Match !!");
                }
            }
        }
        else if (event.getSource() == registerBtn_empLogin){
            methodCalls.loadNewFXML(registerBtn_empLogin, "empRegister.fxml");
        }
    }

    public void empRegisterBtnClicked(MouseEvent event) throws SQLException, IOException {
        if (event.getSource() == registerBtn_empRegister){
            String name = nameField_empRegister.getText();
            String username = userField_empRegister.getText();
            String password = passwordField_empRegister.getText();
            if (name.equals("") || username.equals("") || password.equals("")){
                methodCalls.informationAlert(registerBtn_empRegister, "Fields Cannot be Empty !!!");
            }
            else {
                String query = "SELECT * FROM employee WHERE USERNAME = '"+username+"'";
                ResultSet resultSet = methodCalls.captureSQLResults(query, connection);
                if (resultSet.next()){
                    methodCalls.informationAlert(registerBtn_empRegister, "Username not available. Try another !!");
                }
                else {
                    String query1 = "INSERT INTO employee VALUES (null, '"+name+"', '"+username+"', '"+password+"')";
                    methodCalls.executeSQLStatement(query1, connection);
                    methodCalls.informationAlert(registerBtn_empRegister, "Registration Successfull");
                }
            }
        }
        else if (event.getSource() == signBtn_empRegister){
            methodCalls.loadNewFXML(registerBtn_empRegister, "empLogin.fxml");
        }
    }
}
