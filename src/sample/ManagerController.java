package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class ManagerController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        employeeTablePopulate();
        completedOrdersTablePopulate();
        slideshow_manager1();
        slideshow_manager2();
        slideshow_manager3();
        Pane1.setVisible(true);
        Pane2.setVisible(false);
        Pane2.managedProperty().bind(Pane2.visibleProperty());
        Pane3.setVisible(false);
        Pane3.managedProperty().bind(Pane3.visibleProperty());
    }

    private void completedOrdersTablePopulate() {
        try {
            completedOrdersEmp = FXCollections.observableArrayList();
            getEmpName();
            completedOrdersEmp = empNameList;
            String query = "SELECT `OrderRefNo`, `Employee` FROM `orderHandler` WHERE OrderStatus='Completed'";
            ResultSet resultSet = methodCalls.captureSQLResults(query, connection);
            while (resultSet.next()){
                observableList2.add(new completedOrdersTableModel(resultSet.getString("OrderRefNo"), resultSet.getString("Employee")));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        methodCalls.tableSetColumnData(completedOrdersTable, compOrdersTableRefCol, "orderRefNo", observableList2);
        methodCalls.tableSetColumnData(completedOrdersTable, compOrdersTableEmpCol, "employee", observableList2);
        methodCalls.tableSetColoumnComboBox(compOrdersTableEmpCol, completedOrdersEmp);
        methodCalls.makeTableEditable(completedOrdersTable);
    }

    private void getEmpName() throws SQLException {
        String query = "SELECT `Name` FROM `employee`";
        ResultSet resultSet = methodCalls.captureSQLResults(query, connection);
        empNameList = FXCollections.observableArrayList();
        while (resultSet.next()){
            empNameList.add(resultSet.getString("Name"));
        }
    }

    private void employeeTablePopulate() {
        try {
            String query = "SELECT `Name`, `USERNAME`, `PASSWORD` FROM `employee`";
            ResultSet resultSet = methodCalls.captureSQLResults(query, connection);
            while (resultSet.next()){
                observableList1.add(new employeeTableModel(resultSet.getString("Name"), resultSet.getString("USERNAME"),
                        resultSet.getString("PASSWORD")));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        methodCalls.tableSetColumnData(employeeTable, empTableNameCol, "name", observableList1);
        methodCalls.tableSetColumnData(employeeTable, empTableUsernameCol, "username", observableList1);
        methodCalls.tableSetColumnData(employeeTable, empTablePasswordCol, "password", observableList1);
    }

    private void slideshow_manager3() {
        ArrayList<Image> images = new ArrayList<Image>();
        images.add(new Image("/b22.jpg"));
        images.add(new Image("/b33.jpg"));
        images.add(new Image("/b44.jpg"));
        images.add(new Image("/b11.jpg"));

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
            imageView_manager3.setImage(images.get(count));
            count++;
            if (count == 4){
                count = 0;
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void slideshow_manager2() {
        ArrayList<Image> images = new ArrayList<Image>();
        images.add(new Image("/b22.jpg"));
        images.add(new Image("/b33.jpg"));
        images.add(new Image("/b44.jpg"));
        images.add(new Image("/b11.jpg"));

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
            imageView_manager2.setImage(images.get(count));
            count++;
            if (count == 4){
                count = 0;
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void slideshow_manager1() {
        ArrayList<Image> images = new ArrayList<Image>();
        images.add(new Image("/b22.jpg"));
        images.add(new Image("/b33.jpg"));
        images.add(new Image("/b44.jpg"));
        images.add(new Image("/b11.jpg"));

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
            imageView_manager1.setImage(images.get(count));
            count++;
            if (count == 4){
                count = 0;
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public ManagerController() throws SQLException, ClassNotFoundException {
    }

    methodCalls methodCalls = new methodCalls();
    Connection connection = methodCalls.getSQLConnection();
    @FXML private ImageView imageView_manager1, imageView_manager2, imageView_manager3;
    private int count;
    private String selectedOrderEmp, editedUsername, editedPassword, nameRec, usernameRec, passwordRec;
    @FXML private Circle closeCircle_manager;
    @FXML private Pane Pane1, Pane2, Pane3;
    @FXML private Button empBtn_manager, compOrdersBtn_manager, reportBtn_manager, empSaveBtn_manager, empEditBtn_manager, empAddBtn_manager, compOrdersSaveBtn_manager;
    @FXML private TableView<employeeTableModel> employeeTable;
    @FXML private TableView<completedOrdersTableModel> completedOrdersTable;
    @FXML private TableColumn<employeeTableModel, String> empTableNameCol, empTableUsernameCol, empTablePasswordCol;
    @FXML private TableColumn<completedOrdersTableModel, String> compOrdersTableRefCol, compOrdersTableEmpCol;
    ObservableList<employeeTableModel> observableList1 = FXCollections.observableArrayList();
    ObservableList<completedOrdersTableModel> observableList2 = FXCollections.observableArrayList();
    ObservableList<String> completedOrdersEmp;
    ObservableList<String> empNameList;

    public void closeCircleClicked(MouseEvent event) {
        methodCalls.closeCircleClicked(closeCircle_manager);
    }

    public void managerBtnClicked(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {
        if (event.getSource() == empBtn_manager){
            Pane2.setVisible(false);
            Pane2.managedProperty().bind(Pane2.visibleProperty());
            Pane3.setVisible(false);
            Pane3.managedProperty().bind(Pane3.visibleProperty());
            Pane1.setVisible(true);
        }
        else if (event.getSource() == compOrdersBtn_manager){
            Pane1.setVisible(false);
            Pane1.managedProperty().bind(Pane1.visibleProperty());
            Pane3.setVisible(false);
            Pane3.managedProperty().bind(Pane3.visibleProperty());
            Pane2.setVisible(true);
        }
        else if (event.getSource() == reportBtn_manager){
            Pane1.setVisible(false);
            Pane1.managedProperty().bind(Pane1.visibleProperty());
            Pane2.setVisible(false);
            Pane2.managedProperty().bind(Pane2.visibleProperty());
            Pane3.setVisible(true);
            methodCalls.generateReport();
            methodCalls.informationAlert(reportBtn_manager, "Report Generated at the project folder...");
        }
        else if (event.getSource() == empSaveBtn_manager){
            String name = employeeTable.getSelectionModel().getSelectedItem().getName();
            String query = "UPDATE `employee` SET `USERNAME`='"+editedUsername+"', `PASSWORD`='"+editedPassword+"' WHERE Name='"+name+"'";
            methodCalls.executeSQLStatement(query, connection);
            System.out.println("name is : "+name);
            System.out.println("username is : "+editedUsername);
            System.out.println("password is : "+editedPassword);
            methodCalls.informationAlert(empSaveBtn_manager, "Details updated");
        }
        else if (event.getSource() == empEditBtn_manager){
            methodCalls.makeTableEditable(employeeTable);
            methodCalls.makeTableStringColumnEditable(empTableUsernameCol);
            methodCalls.makeTableStringColumnEditable(empTablePasswordCol);
        }
        else if (event.getSource() == empAddBtn_manager){
            Dialog<CustomPair<String, String, String>> dialog = new Dialog<>();
            dialog.setTitle("Add Employee");
            dialog.setHeaderText("Please input Employee details");
            ButtonType buttonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(buttonType, ButtonType.CANCEL);
            GridPane gridPane = new GridPane();
            gridPane.setHgap(10);
            gridPane.setVgap(10);
            gridPane.setPadding(new Insets(20, 150, 10, 10));
            TextField name = new TextField();
            name.setPromptText("Enter Name");
            TextField username = new TextField();
            username.setPromptText("Enter Username");
            PasswordField password = new PasswordField();
            password.setPromptText("Enter Password");
            gridPane.add(new Label("Name "), 0, 0);
            gridPane.add(name, 1, 0);
            gridPane.add(new Label("Username "), 0, 1);
            gridPane.add(username, 1, 1);
            gridPane.add(new Label("Password "), 0, 2);
            gridPane.add(password, 1, 2);
            dialog.getDialogPane().setContent(gridPane);
            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == buttonType) {
                    return new CustomPair<>(name.getText(), username.getText(), password.getText());
                }
                return null;
            });
            Optional<CustomPair<String, String, String>> result = dialog.showAndWait();
            result.ifPresent(usernamePassword -> {
                nameRec = usernamePassword.getFirst();
                usernameRec = usernamePassword.getSecond();
                passwordRec = usernamePassword.getThird();
                String query = "INSERT INTO `employee` VALUES(null, '"+nameRec+"', '"+usernameRec+"', '"+passwordRec+"')";
                try {
                    methodCalls.executeSQLStatement(query, connection);
                } catch (SQLException e){
                    e.printStackTrace();
                }
                System.out.println(nameRec+" "+usernameRec+" "+passwordRec);
                methodCalls.informationAlert(empAddBtn_manager, "Employee Added");
            });
        }
        else if (event.getSource() == compOrdersSaveBtn_manager){
            String selectedOrderRefNo = completedOrdersTable.getSelectionModel().getSelectedItem().orderRefNo;
            String query = "UPDATE orderHandler SET `Employee`='"+selectedOrderEmp+"' WHERE OrderRefNo='"+selectedOrderRefNo+"'";
            methodCalls.executeSQLStatement(query, connection);
            methodCalls.informationAlert(compOrdersSaveBtn_manager, "Employee changed");
        }
    }

    public void onEmpChanged(TableColumn.CellEditEvent<completedOrdersTableModel, String> completedOrdersTableModelStringCellEditEvent) {
        selectedOrderEmp = completedOrdersTableModelStringCellEditEvent.getNewValue();
    }

    public void onEditColUsername(TableColumn.CellEditEvent<employeeTableModel, String> employeeTableModelStringCellEditEvent) {
        employeeTableModel entry = employeeTable.getSelectionModel().getSelectedItem();
        entry.setUsername(employeeTableModelStringCellEditEvent.getNewValue());
        editedUsername = employeeTable.getSelectionModel().getSelectedItem().getUsername();
    }

    public void onEditColPassword(TableColumn.CellEditEvent<employeeTableModel, String> employeeTableModelStringCellEditEvent) {
        employeeTableModel entry = employeeTable.getSelectionModel().getSelectedItem();
        entry.setPassword(employeeTableModelStringCellEditEvent.getNewValue());
        editedPassword = employeeTable.getSelectionModel().getSelectedItem().getPassword();
    }
}
