package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ChefController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        populateTable();
        slideshow_chef();
    }

    private void populateTable() {
        orderStatusData = FXCollections.observableArrayList();
        orderStatusData.add("Completed");
        orderStatusData.add("Pending");
        try {
            String query = "SELECT `OrderRefNo`, `OrderStatus` FROM `orderHandler`";
            ResultSet resultSet = methodCalls.captureSQLResults(query, connection);
            while (resultSet.next()){
                observableList.add(new orderTableModel(resultSet.getString("OrderRefNo"), resultSet.getString("OrderStatus")));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        methodCalls.tableSetColumnData(orderTable, orderTableRefCol, "orderRefNo", observableList);
        methodCalls.tableSetColumnData(orderTable, orderTableStatusCol, "orderStatus", observableList);
        methodCalls.tableSetColoumnComboBox(orderTableStatusCol, orderStatusData);
        methodCalls.makeTableEditable(orderTable);
    }

    private void slideshow_chef() {

        ArrayList<Image> images = new ArrayList<Image>();
        images.add(new Image("/b2.jpg"));
        images.add(new Image("/b3.jpg"));
        images.add(new Image("/b4.jpg"));
        images.add(new Image("/b1.jpg"));

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
            imageView_chef.setImage(images.get(count));
            count++;
            if (count == 4)
                count = 0;
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public ChefController() throws SQLException, ClassNotFoundException {
    }

    methodCalls methodCalls = new methodCalls();
    Connection connection = methodCalls.getSQLConnection();
    private String empUsername, selectedOrderStatus;
    @FXML private ImageView imageView_chef;
    @FXML private Circle closeCircle_chef;
    private int count;
    @FXML private Button saveBtn_chef;
    @FXML private TableView<orderTableModel> orderTable;
    @FXML private TableColumn<orderTableModel, String> orderTableRefCol, orderTableStatusCol;
    ObservableList<orderTableModel> observableList = FXCollections.observableArrayList();
    ObservableList<String> orderStatusData;


    public void transferMessage(String message){
        empUsername = message;
    }

    public void closeCircleClicked(MouseEvent event) {
        methodCalls.closeCircleClicked(closeCircle_chef);
    }

    public void chefBtnClicked(MouseEvent event) throws SQLException {
        String query = "SELECT `Name` FROM `employee` WHERE USERNAME='"+empUsername+"'";
        ResultSet resultSet = methodCalls.captureSQLResults(query, connection);
        String name = "";
        while (resultSet.next()){
            name = resultSet.getString("Name");
        }
        String selectedOrderRefNo = orderTable.getSelectionModel().getSelectedItem().orderRefNo;
        String query1 = "UPDATE orderHandler SET `OrderStatus`='"+selectedOrderStatus+"', `Employee`='"+name+"' WHERE OrderRefNo='"+selectedOrderRefNo+"'";
        methodCalls.executeSQLStatement(query1, connection);
        methodCalls.informationAlert(saveBtn_chef, "Order status saved");
    }

    public void onStatusChanged(TableColumn.CellEditEvent<orderTableModel, String> orderTableModelStringCellEditEvent) {
        selectedOrderStatus = orderTableModelStringCellEditEvent.getNewValue();
    }
}
