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

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OrderListController implements Initializable {
    public OrderListController() throws SQLException, ClassNotFoundException {
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        slideshow_orderList();
    }

    private void populateTable() {
        try {
            String query = "SELECT `FoodName`, `Quantity`, `Price` FROM `orders` WHERE OrderRefNo='"+orderRefNoRec+"'";
            ResultSet resultSet = methodCalls.captureSQLResults(query, connection);
            while (resultSet.next()){
                observableList.add(new orderListTableModel(resultSet.getString("FoodName"), resultSet.getString("Quantity"),
                        resultSet.getString("Price")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        methodCalls.tableSetColumnData(orderListTableModel, food_OrderListTableModel, "foodName", observableList);
        methodCalls.tableSetColumnData(orderListTableModel, quantity_OrderListTableModel, "qty", observableList);
        methodCalls.tableSetColumnData(orderListTableModel, price_OrderListTableModel, "price", observableList);
    }

    private void slideshow_orderList() {
        ArrayList<Image> images = new ArrayList<Image>();
        images.add(new Image("/b2.jpg"));
        images.add(new Image("/b3.jpg"));
        images.add(new Image("/b4.jpg"));
        images.add(new Image("/b1.jpg"));

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
            imageView_orderList.setImage(images.get(count));
            count++;
            if (count == 4)
                count = 0;
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    methodCalls methodCalls = new methodCalls();
    Connection connection = methodCalls.getSQLConnection();
    private String orderRefNoRec;
    @FXML private ImageView imageView_orderList;
    private int count;
    @FXML private Circle closeCircle_orderList;
    @FXML private Button removeBtn_orderList, editBtn_orderList, checkoutBtn_orderList;
    private ObservableList<orderListTableModel> observableList = FXCollections.observableArrayList();
    @FXML private TableView<orderListTableModel> orderListTableModel;
    @FXML private TableColumn<orderListTableModel, String> food_OrderListTableModel, quantity_OrderListTableModel, price_OrderListTableModel;

    public void transferMessage(String message){
        orderRefNoRec = message;
    }

    public void closeCircleClicked(MouseEvent event) {
        methodCalls.closeCircleClicked(closeCircle_orderList);
    }

    public void btnClicked_orderList(MouseEvent event) throws SQLException, IOException {
        if (event.getSource() == removeBtn_orderList){
            ObservableList<orderListTableModel> allEntry, singleEntry;
            allEntry = orderListTableModel.getItems();
            singleEntry = orderListTableModel.getSelectionModel().getSelectedItems();
            String itemName = orderListTableModel.getSelectionModel().getSelectedItem().foodName;
            singleEntry.forEach(allEntry::remove);
            System.out.println(itemName);
            System.out.println(orderRefNoRec);
            String query = "DELETE FROM orders WHERE FoodName='"+itemName+"' AND OrderRefNo='"+orderRefNoRec+"'";
            methodCalls.executeSQLStatement(query, connection);
        }
        else if (event.getSource() == editBtn_orderList){
            methodCalls.makeTableEditable(orderListTableModel);
            methodCalls.makeTableStringColumnEditable(quantity_OrderListTableModel);
            methodCalls.makeTableStringColumnEditable(price_OrderListTableModel);
        }
        else if (event.getSource() == checkoutBtn_orderList){
            System.out.println("Your Order REF No is : " + orderRefNoRec);
            methodCalls.loadNewFXMLDifferentController(checkoutBtn_orderList, "payment.fxml", null, null, orderRefNoRec, null);
        }
    }

    void setPriceDisplay(String price){
        orderListTableModel.getSelectionModel().getSelectedItem().setPrice(price);
    }

    public void onEditChanged(TableColumn.CellEditEvent<sample.orderListTableModel, String> orderListTableModelStringCellEditEvent) throws SQLException {
        orderListTableModel entry = orderListTableModel.getSelectionModel().getSelectedItem();
        entry.setQty(orderListTableModelStringCellEditEvent.getNewValue());
        String food = orderListTableModel.getSelectionModel().getSelectedItem().getFoodName();
        String qty = orderListTableModel.getSelectionModel().getSelectedItem().getQty();
        int qtyInt = Integer.parseInt(qty);
        int price = 0;
        int pricePerQty = 0;

        if (food.equals("NUTTY CHICKEN NOODLES")){
            pricePerQty = methodCalls.getPriceOfMenuItem("NUTTY CHICKEN NOODLES");
            price = pricePerQty * qtyInt;
        }
        if (food.equals("FRIED PRAWN NOODLES")){
            pricePerQty = methodCalls.getPriceOfMenuItem("FRIED PRAWN NOODLES");
            price = pricePerQty * qtyInt;
        }
        if (food.equals("VEGETABLE NOODLES")){
            pricePerQty = methodCalls.getPriceOfMenuItem("VEGETABLE NOODLES");
            price = pricePerQty * qtyInt;
        }
        if (food.equals("STIRE-FRY NOODLES")){
            pricePerQty = methodCalls.getPriceOfMenuItem("STIRE-FRY NOODLES");
            price = pricePerQty * qtyInt;
        }
        if (food.equals("SPICY VEGI NOODLES")){
            pricePerQty = methodCalls.getPriceOfMenuItem("SPICY VEGI NOODLES");
            price = pricePerQty * qtyInt;
        }
        if (food.equals("SAUSAGE NOODLES")){
            pricePerQty = methodCalls.getPriceOfMenuItem("SAUSAGE NOODLES");
            price = pricePerQty * qtyInt;
        }

        if (food.equals("CHICKEN FRIED RICE")){
            pricePerQty = methodCalls.getPriceOfMenuItem("CHICKEN FRIED RICE");
            price = pricePerQty * qtyInt;
        }
        if (food.equals("SEAFOOD FRIED RICE")){
            pricePerQty = methodCalls.getPriceOfMenuItem("SEAFOOD FRIED RICE");
            price = pricePerQty * qtyInt;
        }
        if (food.equals("BIRIYANI")){
            pricePerQty = methodCalls.getPriceOfMenuItem("BIRIYANI");
            price = pricePerQty * qtyInt;
        }
        if (food.equals("NASI GORENG")){
            pricePerQty = methodCalls.getPriceOfMenuItem("NASI GORENG");
            price = pricePerQty * qtyInt;
        }
        if (food.equals("MIXED FRIED RICE")){
            pricePerQty = methodCalls.getPriceOfMenuItem("MIXED FRIED RICE");
            price = pricePerQty * qtyInt;
        }
        if (food.equals("EGG FRIED RICE")){
            pricePerQty = methodCalls.getPriceOfMenuItem("EGG FRIED RICE");
            price = pricePerQty * qtyInt;
        }

        if (food.equals("CARAMEL PUDDING")){
            pricePerQty = methodCalls.getPriceOfMenuItem("CARAMEL PUDDING");
            price = pricePerQty * qtyInt;
        }
        if (food.equals("WATALAPPAN")){
            pricePerQty = methodCalls.getPriceOfMenuItem("WATALAPPAN");
            price = pricePerQty * qtyInt;
        }
        if (food.equals("FRUIT SALAD")){
            pricePerQty = methodCalls.getPriceOfMenuItem("FRUIT SALAD");
            price = pricePerQty * qtyInt;
        }
        if (food.equals("FRUIT PLATTER")){
            pricePerQty = methodCalls.getPriceOfMenuItem("FRUIT PLATTER");
            price = pricePerQty * qtyInt;
        }
        if (food.equals("ICE CREAM")){
            pricePerQty = methodCalls.getPriceOfMenuItem("ICE CREAM");
            price = pricePerQty * qtyInt;
        }
        if (food.equals("LAVA CAKE")){
            pricePerQty = methodCalls.getPriceOfMenuItem("LAVA CAKE");
            price = pricePerQty * qtyInt;
        }

        if (food.equals("CHOCOLATE DRINK")){
            pricePerQty = methodCalls.getPriceOfMenuItem("CHOCOLATE DRINK");
            price = pricePerQty * qtyInt;
        }
        if (food.equals("MANGO COCKTAIL")){
            pricePerQty = methodCalls.getPriceOfMenuItem("MANGO COCKTAIL");
            price = pricePerQty * qtyInt;
        }
        if (food.equals("NUTELLA")){
            pricePerQty = methodCalls.getPriceOfMenuItem("NUTELLA");
            price = pricePerQty * qtyInt;
        }
        if (food.equals("ORANGE JUICE")){
            pricePerQty = methodCalls.getPriceOfMenuItem("ORANGE JUICE");
            price = pricePerQty * qtyInt;
        }
        if (food.equals("OREO MILKSHAKE")){
            pricePerQty = methodCalls.getPriceOfMenuItem("OREO MILKSHAKE");
            price = pricePerQty * qtyInt;
        }
        if (food.equals("STRAWBERRY MILKSHAKE")){
            pricePerQty = methodCalls.getPriceOfMenuItem("STRAWBERRY MILKSHAKE");
            price = pricePerQty * qtyInt;
        }

        String priceString = Integer.toString(price);
        setPriceDisplay(priceString);
        String query = "UPDATE orders SET Quantity='"+qtyInt+"', Price='"+price+"' WHERE OrderRefNo='"+orderRefNoRec+"' AND FoodName='"+food+"'";
        methodCalls.executeSQLStatement(query, connection);
        System.out.println(food);
        System.out.println(qty);
        System.out.println(priceString);
    }

    public void loadBtnClicked_orderList(MouseEvent event) {
        count++;
        if (count == 1){
            populateTable();
        }
    }
}
