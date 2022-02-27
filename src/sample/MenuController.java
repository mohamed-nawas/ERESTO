package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    public MenuController() throws SQLException, ClassNotFoundException {
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        slideshow_menu();
        drinksPane.setVisible(true);
        desertsPane.setVisible(false);
        desertsPane.managedProperty().bind(desertsPane.visibleProperty());
        ricePane.setVisible(false);
        ricePane.managedProperty().bind(ricePane.visibleProperty());
        noodlesPane.setVisible(false);
        noodlesPane.managedProperty().bind(noodlesPane.visibleProperty());
    }

    private void slideshow_menu() {
        ArrayList<Image> images = new ArrayList<Image>();
        images.add(new Image("/b2.jpg"));
        images.add(new Image("/b3.jpg"));
        images.add(new Image("/b4.jpg"));
        images.add(new Image("/b1.jpg"));

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
            imageView_menu.setImage(images.get(count));
            count++;
            if (count == 4)
                count = 0;
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    methodCalls methodCalls = new methodCalls();
    Connection connection = methodCalls.getSQLConnection();

    private String loggedInUsername, orderRefNo;
    private int count, itemsInList, Pane4Item1Qty, Pane4Item2Qty, Pane4Item3Qty, Pane4Item4Qty, Pane4Item5Qty, Pane4Item6Qty,
            Pane3Item1Qty, Pane3Item2Qty, Pane3Item3Qty, Pane3Item4Qty, Pane3Item5Qty, Pane3Item6Qty,
            Pane2Item1Qty, Pane2Item2Qty, Pane2Item3Qty, Pane2Item4Qty, Pane2Item5Qty, Pane2Item6Qty,
            Pane1Item1Qty, Pane1Item2Qty, Pane1Item3Qty, Pane1Item4Qty, Pane1Item5Qty, Pane1Item6Qty;
    @FXML private ImageView imageView_menu;
    @FXML private Circle closeCircle_menu;
    @FXML private Button drinksBtn_menu, desertsBtn_menu, riceBtn_menu, noodlesBtn_menu, addToOrderBtn_menu, cancelOrderBtn_menu,
            Pane4Item1Plus, Pane4Item2Plus, Pane4Item3Plus, Pane4Item4Plus, Pane4Item5Plus, Pane4Item6Plus,
            Pane4Item1Minus, Pane4Item2Minus, Pane4Item3Minus, Pane4Item4Minus, Pane4Item5Minus, Pane4Item6Minus,
            Pane3Item1Plus, Pane3Item2Plus, Pane3Item3Plus, Pane3Item4Plus, Pane3Item5Plus, Pane3Item6Plus,
            Pane3Item1Minus, Pane3Item2Minus, Pane3Item3Minus, Pane3Item4Minus, Pane3Item5Minus, Pane3Item6Minus,
            Pane2Item1Plus, Pane2Item2Plus, Pane2Item3Plus, Pane2Item4Plus, Pane2Item5Plus, Pane2Item6Plus,
            Pane2Item1Minus, Pane2Item2Minus, Pane2Item3Minus, Pane2Item4Minus, Pane2Item5Minus, Pane2Item6Minus,
            Pane1Item1Plus, Pane1Item2Plus, Pane1Item3Plus, Pane1Item4Plus, Pane1Item5Plus, Pane1Item6Plus,
            Pane1Item1Minus, Pane1Item2Minus, Pane1Item3Minus, Pane1Item4Minus, Pane1Item5Minus, Pane1Item6Minus;
    @FXML private Pane drinksPane, desertsPane, ricePane, noodlesPane;
    @FXML private Label itemsInTheListLabel_menu;


    public void transferMessage(String message){
        loggedInUsername = message;
    }

    public void closeCircleClicked(MouseEvent event) {
        methodCalls.closeCircleClicked(closeCircle_menu);
    }

    public void menuBtnClicked(MouseEvent event) throws SQLException, IOException {
        if (event.getSource() == drinksBtn_menu){
            desertsPane.setVisible(false);
            desertsPane.managedProperty().bind(desertsPane.visibleProperty());
            ricePane.setVisible(false);
            ricePane.managedProperty().bind(ricePane.visibleProperty());
            noodlesPane.setVisible(false);
            noodlesPane.managedProperty().bind(noodlesPane.visibleProperty());
            drinksPane.setVisible(true);
        }
        else if (event.getSource() == desertsBtn_menu){
            drinksPane.setVisible(false);
            drinksPane.managedProperty().bind(drinksPane.visibleProperty());
            ricePane.setVisible(false);
            ricePane.managedProperty().bind(ricePane.visibleProperty());
            noodlesPane.setVisible(false);
            noodlesPane.managedProperty().bind(noodlesPane.visibleProperty());
            desertsPane.setVisible(true);
        }
        else if (event.getSource() == riceBtn_menu){
            desertsPane.setVisible(false);
            desertsPane.managedProperty().bind(desertsPane.visibleProperty());
            drinksPane.setVisible(false);
            drinksPane.managedProperty().bind(drinksPane.visibleProperty());
            noodlesPane.setVisible(false);
            noodlesPane.managedProperty().bind(noodlesPane.visibleProperty());
            ricePane.setVisible(true);
        }
        else if (event.getSource() == noodlesBtn_menu){
            desertsPane.setVisible(false);
            desertsPane.managedProperty().bind(desertsPane.visibleProperty());
            ricePane.setVisible(false);
            ricePane.managedProperty().bind(ricePane.visibleProperty());
            drinksPane.setVisible(false);
            drinksPane.managedProperty().bind(drinksPane.visibleProperty());
            noodlesPane.setVisible(true);
        }
        else if (event.getSource() == cancelOrderBtn_menu)
            methodCalls.closeButtonClicked(cancelOrderBtn_menu);
        else if (event.getSource() == addToOrderBtn_menu){
            if (itemsInList > 0){
                orderRefNo = methodCalls.getReferenceKey();
                System.out.println(loggedInUsername);
                String query = "INSERT INTO "+loggedInUsername+" VALUES(null, '"+orderRefNo+"')";
                methodCalls.executeSQLStatement(query, connection);
                System.out.println(orderRefNo);

                if (Pane4Item1Qty > 0){
                    String query1 = "INSERT INTO orders VALUES(null, '"+loggedInUsername+"', '"+orderRefNo+"', 'NUTTY CHICKEN NOODLES', '"+Pane4Item1Qty+"', '"+methodCalls.getPriceOfMenuItem("NUTTY CHICKEN NOODLES")*Pane4Item1Qty+"')";
                    methodCalls.executeSQLStatement(query1, connection);
                }
                if (Pane4Item2Qty > 0){
                    String query1 = "INSERT INTO orders VALUES(null, '"+loggedInUsername+"', '"+orderRefNo+"', 'FRIED PRAWN NOODLES', '"+Pane4Item2Qty+"', '"+methodCalls.getPriceOfMenuItem("FRIED PRAWN NOODLES")*Pane4Item2Qty+"')";
                    methodCalls.executeSQLStatement(query1, connection);
                }
                if (Pane4Item3Qty > 0){
                    String query1 = "INSERT INTO orders VALUES(null, '"+loggedInUsername+"', '"+orderRefNo+"', 'VEGETABLE NOODLES', '"+Pane4Item3Qty+"', '"+methodCalls.getPriceOfMenuItem("VEGETABLE NOODLES")*Pane4Item3Qty+"')";
                    methodCalls.executeSQLStatement(query1, connection);
                }
                if (Pane4Item4Qty > 0){
                    String query1 = "INSERT INTO orders VALUES(null, '"+loggedInUsername+"', '"+orderRefNo+"', 'STIRE-FRY NOODLES', '"+Pane4Item4Qty+"', '"+methodCalls.getPriceOfMenuItem("STIRE-FRY NOODLES")*Pane4Item4Qty+"')";
                    methodCalls.executeSQLStatement(query1, connection);
                }
                if (Pane4Item5Qty > 0){
                    String query1 = "INSERT INTO orders VALUES(null, '"+loggedInUsername+"', '"+orderRefNo+"', 'SPICY VEGI NOODLES', '"+Pane4Item5Qty+"', '"+methodCalls.getPriceOfMenuItem("SPICY VEGI NOODLES")*Pane4Item5Qty+"')";
                    methodCalls.executeSQLStatement(query1, connection);
                }
                if (Pane4Item6Qty > 0){
                    String query1 = "INSERT INTO orders VALUES(null, '"+loggedInUsername+"', '"+orderRefNo+"', 'SAUSAGE NOODLES', '"+Pane4Item6Qty+"', '"+methodCalls.getPriceOfMenuItem("SAUSAGE NOODLES")*Pane4Item6Qty+"')";
                    methodCalls.executeSQLStatement(query1, connection);
                }

                if (Pane3Item1Qty > 0){
                    String query1 = "INSERT INTO orders VALUES(null, '"+loggedInUsername+"', '"+orderRefNo+"', 'CHICKEN FRIED RICE', '"+Pane3Item1Qty+"', '"+methodCalls.getPriceOfMenuItem("CHICKEN FRIED RICE")*Pane3Item1Qty+"')";
                    methodCalls.executeSQLStatement(query1, connection);
                }
                if (Pane3Item2Qty > 0){
                    String query1 = "INSERT INTO orders VALUES(null, '"+loggedInUsername+"', '"+orderRefNo+"', 'SEAFOOD FRIED RICE', '"+Pane3Item2Qty+"', '"+methodCalls.getPriceOfMenuItem("SEAFOOD FRIED RICE")*Pane3Item2Qty+"')";
                    methodCalls.executeSQLStatement(query1, connection);
                }
                if (Pane3Item3Qty > 0){
                    String query1 = "INSERT INTO orders VALUES(null, '"+loggedInUsername+"', '"+orderRefNo+"', 'BIRIYANI', '"+Pane3Item3Qty+"', '"+methodCalls.getPriceOfMenuItem("BIRIYANI")*Pane3Item3Qty+"')";
                    methodCalls.executeSQLStatement(query1, connection);
                }
                if (Pane3Item4Qty > 0){
                    String query1 = "INSERT INTO orders VALUES(null, '"+loggedInUsername+"', '"+orderRefNo+"', 'NASI GORENG', '"+Pane3Item4Qty+"', '"+methodCalls.getPriceOfMenuItem("NASI GORENG")*Pane3Item4Qty+"')";
                    methodCalls.executeSQLStatement(query1, connection);
                }
                if (Pane3Item5Qty > 0){
                    String query1 = "INSERT INTO orders VALUES(null, '"+loggedInUsername+"', '"+orderRefNo+"', 'MIXED FRIED RICE', '"+Pane3Item5Qty+"', '"+methodCalls.getPriceOfMenuItem("MIXED FRIED RICE")*Pane3Item5Qty+"')";
                    methodCalls.executeSQLStatement(query1, connection);
                }
                if (Pane3Item6Qty > 0){
                    String query1 = "INSERT INTO orders VALUES(null, '"+loggedInUsername+"', '"+orderRefNo+"', 'EGG FRIED RICE', '"+Pane3Item6Qty+"', '"+methodCalls.getPriceOfMenuItem("EGG FRIED RICE")*Pane3Item6Qty+"')";
                    methodCalls.executeSQLStatement(query1, connection);
                }

                if (Pane2Item1Qty > 0){
                    String query1 = "INSERT INTO orders VALUES(null, '"+loggedInUsername+"', '"+orderRefNo+"', 'CARAMEL PUDDING', '"+Pane2Item1Qty+"', '"+methodCalls.getPriceOfMenuItem("CARAMEL PUDDING")*Pane2Item1Qty+"')";
                    methodCalls.executeSQLStatement(query1, connection);
                }
                if (Pane2Item2Qty > 0){
                    String query1 = "INSERT INTO orders VALUES(null, '"+loggedInUsername+"', '"+orderRefNo+"', 'WATALAPPAN', '"+Pane2Item2Qty+"', '"+methodCalls.getPriceOfMenuItem("WATALAPPAN")*Pane2Item2Qty+"')";
                    methodCalls.executeSQLStatement(query1, connection);
                }
                if (Pane2Item3Qty > 0){
                    String query1 = "INSERT INTO orders VALUES(null, '"+loggedInUsername+"', '"+orderRefNo+"', 'FRUIT SALAD', '"+Pane2Item3Qty+"', '"+methodCalls.getPriceOfMenuItem("FRUIT SALAD")*Pane2Item3Qty+"')";
                    methodCalls.executeSQLStatement(query1, connection);
                }
                if (Pane2Item4Qty > 0){
                    String query1 = "INSERT INTO orders VALUES(null, '"+loggedInUsername+"', '"+orderRefNo+"', 'FRUIT PLATTER', '"+Pane2Item4Qty+"', '"+methodCalls.getPriceOfMenuItem("FRUIT PLATTER")*Pane2Item4Qty+"')";
                    methodCalls.executeSQLStatement(query1, connection);
                }
                if (Pane2Item5Qty > 0){
                    String query1 = "INSERT INTO orders VALUES(null, '"+loggedInUsername+"', '"+orderRefNo+"', 'ICE CREAM', '"+Pane2Item5Qty+"', '"+methodCalls.getPriceOfMenuItem("ICE CREAM")*Pane2Item5Qty+"')";
                    methodCalls.executeSQLStatement(query1, connection);
                }
                if (Pane2Item6Qty > 0){
                    String query1 = "INSERT INTO orders VALUES(null, '"+loggedInUsername+"', '"+orderRefNo+"', 'LAVA CAKE', '"+Pane2Item6Qty+"', '"+methodCalls.getPriceOfMenuItem("LAVA CAKE")*Pane2Item6Qty+"')";
                    methodCalls.executeSQLStatement(query1, connection);
                }

                if (Pane1Item1Qty > 0){
                    String query1 = "INSERT INTO orders VALUES(null, '"+loggedInUsername+"', '"+orderRefNo+"', 'CHOCOLATE DRINK', '"+Pane1Item1Qty+"', '"+methodCalls.getPriceOfMenuItem("CHOCOLATE DRINK")*Pane1Item1Qty+"')";
                    methodCalls.executeSQLStatement(query1, connection);
                }
                if (Pane1Item2Qty > 0){
                    String query1 = "INSERT INTO orders VALUES(null, '"+loggedInUsername+"', '"+orderRefNo+"', 'MANGO COCKTAIL', '"+Pane1Item2Qty+"', '"+methodCalls.getPriceOfMenuItem("MANGO COCKTAIL")*Pane1Item2Qty+"')";
                    methodCalls.executeSQLStatement(query1, connection);
                }
                if (Pane1Item3Qty > 0){
                    String query1 = "INSERT INTO orders VALUES(null, '"+loggedInUsername+"', '"+orderRefNo+"', 'NUTELLA', '"+Pane1Item3Qty+"', '"+methodCalls.getPriceOfMenuItem("NUTELLA")*Pane1Item3Qty+"')";
                    methodCalls.executeSQLStatement(query1, connection);
                }
                if (Pane1Item4Qty > 0){
                    String query1 = "INSERT INTO orders VALUES(null, '"+loggedInUsername+"', '"+orderRefNo+"', 'ORANGE JUICE', '"+Pane1Item4Qty+"', '"+methodCalls.getPriceOfMenuItem("ORANGE JUICE")*Pane1Item4Qty+"')";
                    methodCalls.executeSQLStatement(query1, connection);
                }
                if (Pane1Item5Qty > 0){
                    String query1 = "INSERT INTO orders VALUES(null, '"+loggedInUsername+"', '"+orderRefNo+"', 'OREO MILKSHAKE', '"+Pane1Item5Qty+"', '"+methodCalls.getPriceOfMenuItem("OREO MILKSHAKE")*Pane1Item5Qty+"')";
                    methodCalls.executeSQLStatement(query1, connection);
                }
                if (Pane1Item6Qty > 0){
                    String query1 = "INSERT INTO orders VALUES(null, '"+loggedInUsername+"', '"+orderRefNo+"', 'STRAWBERRY MILKSHAKE', '"+Pane1Item6Qty+"', '"+methodCalls.getPriceOfMenuItem("STRAWBERRY MILKSHAKE")*Pane1Item6Qty+"')";
                    methodCalls.executeSQLStatement(query1, connection);
                }

                String query2 = "INSERT INTO orderHandler VALUES(null, '"+orderRefNo+"', 'Pending', '')";
                methodCalls.executeSQLStatement(query2, connection);
                methodCalls.informationAlert(addToOrderBtn_menu, "Order Saved !!!");
                methodCalls.loadNewFXMLDifferentController(addToOrderBtn_menu, "orderList.fxml", null, orderRefNo, null, null);
            }
            else {
                methodCalls.informationAlert(addToOrderBtn_menu, "Order is Empty !!!");
            }
        }
    }

    public void pane1ItemClicked(MouseEvent event) {
        if (event.getSource() == Pane1Item1Plus){
            Pane1Item1Qty = Pane1Item1Qty + 1;
            itemsInList = itemsInList + 1;
            itemsInTheListLabel_menu.setText(Integer.toString(itemsInList));
        }
        else if (event.getSource() == Pane1Item1Minus){
            if (Pane1Item1Qty == 0){
                Pane1Item1Qty = 0;
            }
            else {
                Pane1Item1Qty = Pane1Item1Qty - 1;
                itemsInList = itemsInList - 1;
                itemsInTheListLabel_menu.setText(Integer.toString(itemsInList));
            }
        }
        else if (event.getSource() == Pane1Item2Plus){
            Pane1Item2Qty = Pane1Item2Qty + 1;
            itemsInList = itemsInList + 1;
            itemsInTheListLabel_menu.setText(Integer.toString(itemsInList));
        }
        else if (event.getSource() == Pane1Item2Minus){
            if (Pane1Item2Qty == 0){
                Pane1Item2Qty = 0;
            }
            else {
                Pane1Item2Qty = Pane1Item2Qty - 1;
                itemsInList = itemsInList - 1;
                itemsInTheListLabel_menu.setText(Integer.toString(itemsInList));
            }
        }
        else if (event.getSource() == Pane1Item3Plus){
            Pane1Item3Qty = Pane1Item3Qty + 1;
            itemsInList = itemsInList + 1;
            itemsInTheListLabel_menu.setText(Integer.toString(itemsInList));
        }
        else if (event.getSource() == Pane1Item3Minus){
            if (Pane1Item3Qty == 0){
                Pane1Item3Qty = 0;
            }
            else {
                Pane1Item3Qty = Pane1Item3Qty - 1;
                itemsInList = itemsInList - 1;
                itemsInTheListLabel_menu.setText(Integer.toString(itemsInList));
            }
        }
        else if (event.getSource() == Pane1Item4Plus){
            Pane1Item4Qty = Pane1Item4Qty + 1;
            itemsInList = itemsInList + 1;
            itemsInTheListLabel_menu.setText(Integer.toString(itemsInList));
        }
        else if (event.getSource() == Pane1Item4Minus){
            if (Pane1Item4Qty == 0){
                Pane1Item4Qty = 0;
            }
            else {
                Pane1Item4Qty = Pane1Item4Qty - 1;
                itemsInList = itemsInList - 1;
                itemsInTheListLabel_menu.setText(Integer.toString(itemsInList));
            }
        }
        else if (event.getSource() == Pane1Item5Plus){
            Pane1Item5Qty = Pane1Item5Qty + 1;
            itemsInList = itemsInList + 1;
            itemsInTheListLabel_menu.setText(Integer.toString(itemsInList));
        }
        else if (event.getSource() == Pane1Item5Minus){
            if (Pane1Item5Qty == 0){
                Pane1Item5Qty = 0;
            }
            else {
                Pane1Item5Qty = Pane1Item5Qty - 1;
                itemsInList = itemsInList - 1;
                itemsInTheListLabel_menu.setText(Integer.toString(itemsInList));
            }
        }
        else if (event.getSource() == Pane1Item6Plus){
            Pane1Item6Qty = Pane1Item6Qty + 1;
            itemsInList = itemsInList + 1;
            itemsInTheListLabel_menu.setText(Integer.toString(itemsInList));
        }
        else if (event.getSource() == Pane1Item6Minus){
            if (Pane1Item6Qty == 0){
                Pane1Item6Qty = 0;
            }
            else {
                Pane1Item6Qty = Pane1Item6Qty - 1;
                itemsInList = itemsInList - 1;
                itemsInTheListLabel_menu.setText(Integer.toString(itemsInList));
            }
        }
    }

    public void pane2ItemClicked(MouseEvent event) {
        if (event.getSource() == Pane2Item1Plus){
            Pane2Item1Qty = Pane2Item1Qty + 1;
            itemsInList = itemsInList + 1;
            itemsInTheListLabel_menu.setText(Integer.toString(itemsInList));
        }
        else if (event.getSource() == Pane2Item1Minus){
            if (Pane2Item1Qty == 0){
                Pane2Item1Qty = 0;
            }
            else {
                Pane2Item1Qty = Pane2Item1Qty - 1;
                itemsInList = itemsInList - 1;
                itemsInTheListLabel_menu.setText(Integer.toString(itemsInList));
            }
        }
        else if (event.getSource() == Pane2Item2Plus){
            Pane2Item2Qty = Pane2Item2Qty + 1;
            itemsInList = itemsInList + 1;
            itemsInTheListLabel_menu.setText(Integer.toString(itemsInList));
        }
        else if (event.getSource() == Pane2Item2Minus){
            if (Pane2Item2Qty == 0){
                Pane2Item2Qty = 0;
            }
            else {
                Pane2Item2Qty = Pane2Item2Qty - 1;
                itemsInList = itemsInList - 1;
                itemsInTheListLabel_menu.setText(Integer.toString(itemsInList));
            }
        }
        else if (event.getSource() == Pane2Item3Plus){
            Pane2Item3Qty = Pane2Item3Qty + 1;
            itemsInList = itemsInList + 1;
            itemsInTheListLabel_menu.setText(Integer.toString(itemsInList));
        }
        else if (event.getSource() == Pane2Item3Minus){
            if (Pane2Item3Qty == 0){
                Pane2Item3Qty = 0;
            }
            else {
                Pane2Item3Qty = Pane2Item3Qty - 1;
                itemsInList = itemsInList - 1;
                itemsInTheListLabel_menu.setText(Integer.toString(itemsInList));
            }
        }
        else if (event.getSource() == Pane2Item4Plus){
            Pane2Item4Qty = Pane2Item4Qty + 1;
            itemsInList = itemsInList + 1;
            itemsInTheListLabel_menu.setText(Integer.toString(itemsInList));
        }
        else if (event.getSource() == Pane2Item4Minus){
            if (Pane2Item4Qty == 0){
                Pane2Item4Qty = 0;
            }
            else {
                Pane2Item4Qty = Pane2Item4Qty - 1;
                itemsInList = itemsInList - 1;
                itemsInTheListLabel_menu.setText(Integer.toString(itemsInList));
            }
        }
        else if (event.getSource() == Pane2Item5Plus){
            Pane2Item5Qty = Pane2Item5Qty + 1;
            itemsInList = itemsInList + 1;
            itemsInTheListLabel_menu.setText(Integer.toString(itemsInList));
        }
        else if (event.getSource() == Pane2Item5Minus){
            if (Pane2Item5Qty == 0){
                Pane2Item5Qty = 0;
            }
            else {
                Pane2Item5Qty = Pane2Item5Qty - 1;
                itemsInList = itemsInList - 1;
                itemsInTheListLabel_menu.setText(Integer.toString(itemsInList));
            }
        }
        else if (event.getSource() == Pane2Item6Plus){
            Pane2Item6Qty = Pane2Item6Qty + 1;
            itemsInList = itemsInList + 1;
            itemsInTheListLabel_menu.setText(Integer.toString(itemsInList));
        }
        else if (event.getSource() == Pane2Item6Minus){
            if (Pane2Item6Qty == 0){
                Pane2Item6Qty = 0;
            }
            else {
                Pane2Item6Qty = Pane2Item6Qty - 1;
                itemsInList = itemsInList - 1;
                itemsInTheListLabel_menu.setText(Integer.toString(itemsInList));
            }
        }
    }

    public void pane3ItemClicked(MouseEvent event) {
        if (event.getSource() == Pane3Item1Plus){
            Pane3Item1Qty = Pane3Item1Qty + 1;
            itemsInList = itemsInList + 1;
            itemsInTheListLabel_menu.setText(Integer.toString(itemsInList));
        }
        else if (event.getSource() == Pane3Item1Minus){
            if (Pane3Item1Qty == 0){
                Pane3Item1Qty = 0;
            }
            else {
                Pane3Item1Qty = Pane3Item1Qty - 1;
                itemsInList = itemsInList - 1;
                itemsInTheListLabel_menu.setText(Integer.toString(itemsInList));
            }
        }
        else if (event.getSource() == Pane3Item2Plus){
            Pane3Item2Qty = Pane3Item2Qty + 1;
            itemsInList = itemsInList + 1;
            itemsInTheListLabel_menu.setText(Integer.toString(itemsInList));
        }
        else if (event.getSource() == Pane3Item2Minus){
            if (Pane3Item2Qty == 0){
                Pane3Item2Qty = 0;
            }
            else {
                Pane3Item2Qty = Pane3Item2Qty - 1;
                itemsInList = itemsInList - 1;
                itemsInTheListLabel_menu.setText(Integer.toString(itemsInList));
            }
        }
        else if (event.getSource() == Pane3Item3Plus){
            Pane3Item3Qty = Pane3Item3Qty + 1;
            itemsInList = itemsInList + 1;
            itemsInTheListLabel_menu.setText(Integer.toString(itemsInList));
        }
        else if (event.getSource() == Pane3Item3Minus){
            if (Pane3Item3Qty == 0){
                Pane3Item3Qty = 0;
            }
            else {
                Pane3Item3Qty = Pane3Item3Qty - 1;
                itemsInList = itemsInList - 1;
                itemsInTheListLabel_menu.setText(Integer.toString(itemsInList));
            }
        }
        else if (event.getSource() == Pane3Item4Plus){
            Pane3Item4Qty = Pane3Item4Qty + 1;
            itemsInList = itemsInList + 1;
            itemsInTheListLabel_menu.setText(Integer.toString(itemsInList));
        }
        else if (event.getSource() == Pane3Item4Minus){
            if (Pane3Item4Qty == 0){
                Pane3Item4Qty = 0;
            }
            else {
                Pane3Item4Qty = Pane3Item4Qty - 1;
                itemsInList = itemsInList - 1;
                itemsInTheListLabel_menu.setText(Integer.toString(itemsInList));
            }
        }
        else if (event.getSource() == Pane3Item5Plus){
            Pane3Item5Qty = Pane3Item5Qty + 1;
            itemsInList = itemsInList + 1;
            itemsInTheListLabel_menu.setText(Integer.toString(itemsInList));
        }
        else if (event.getSource() == Pane3Item5Minus){
            if (Pane3Item5Qty == 0){
                Pane3Item5Qty = 0;
            }
            else {
                Pane3Item5Qty = Pane3Item5Qty - 1;
                itemsInList = itemsInList - 1;
                itemsInTheListLabel_menu.setText(Integer.toString(itemsInList));
            }
        }
        else if (event.getSource() == Pane3Item6Plus){
            Pane3Item6Qty = Pane3Item6Qty + 1;
            itemsInList = itemsInList + 1;
            itemsInTheListLabel_menu.setText(Integer.toString(itemsInList));
        }
        else if (event.getSource() == Pane3Item6Minus){
            if (Pane3Item6Qty == 0){
                Pane3Item6Qty = 0;
            }
            else {
                Pane3Item6Qty = Pane3Item6Qty - 1;
                itemsInList = itemsInList - 1;
                itemsInTheListLabel_menu.setText(Integer.toString(itemsInList));
            }
        }
    }

    public void pane4ItemClicked(MouseEvent event) {
        if (event.getSource() == Pane4Item1Plus){
            Pane4Item1Qty = Pane4Item1Qty + 1;
            itemsInList = itemsInList + 1;
            itemsInTheListLabel_menu.setText(Integer.toString(itemsInList));
        }
        else if (event.getSource() == Pane4Item1Minus){
            if (Pane4Item1Qty == 0){
                Pane4Item1Qty = 0;
            }
            else {
                Pane4Item1Qty = Pane4Item1Qty - 1;
                itemsInList = itemsInList - 1;
                itemsInTheListLabel_menu.setText(Integer.toString(itemsInList));
            }
        }
        else if (event.getSource() == Pane4Item2Plus){
            Pane4Item2Qty = Pane4Item2Qty + 1;
            itemsInList = itemsInList + 1;
            itemsInTheListLabel_menu.setText(Integer.toString(itemsInList));
        }
        else if (event.getSource() == Pane4Item2Minus){
            if (Pane4Item2Qty == 0){
                Pane4Item2Qty = 0;
            }
            else {
                Pane4Item2Qty = Pane4Item2Qty - 1;
                itemsInList = itemsInList - 1;
                itemsInTheListLabel_menu.setText(Integer.toString(itemsInList));
            }
        }
        else if (event.getSource() == Pane4Item3Plus){
            Pane4Item3Qty = Pane4Item3Qty + 1;
            itemsInList = itemsInList + 1;
            itemsInTheListLabel_menu.setText(Integer.toString(itemsInList));
        }
        else if (event.getSource() == Pane4Item3Minus){
            if (Pane4Item3Qty == 0){
                Pane4Item3Qty = 0;
            }
            else {
                Pane4Item3Qty = Pane4Item3Qty - 1;
                itemsInList = itemsInList - 1;
                itemsInTheListLabel_menu.setText(Integer.toString(itemsInList));
            }
        }
        else if (event.getSource() == Pane4Item4Plus){
            Pane4Item4Qty = Pane4Item4Qty + 1;
            itemsInList = itemsInList + 1;
            itemsInTheListLabel_menu.setText(Integer.toString(itemsInList));
        }
        else if (event.getSource() == Pane4Item4Minus){
            if (Pane4Item4Qty == 0){
                Pane4Item4Qty = 0;
            }
            else {
                Pane4Item4Qty = Pane4Item4Qty - 1;
                itemsInList = itemsInList - 1;
                itemsInTheListLabel_menu.setText(Integer.toString(itemsInList));
            }
        }
        else if (event.getSource() == Pane4Item5Plus){
            Pane4Item5Qty = Pane4Item5Qty + 1;
            itemsInList = itemsInList + 1;
            itemsInTheListLabel_menu.setText(Integer.toString(itemsInList));
        }
        else if (event.getSource() == Pane4Item5Minus){
            if (Pane4Item5Qty == 0){
                Pane4Item5Qty = 0;
            }
            else {
                Pane4Item5Qty = Pane4Item5Qty - 1;
                itemsInList = itemsInList - 1;
                itemsInTheListLabel_menu.setText(Integer.toString(itemsInList));
            }
        }
        else if (event.getSource() == Pane4Item6Plus){
            Pane4Item6Qty = Pane4Item6Qty + 1;
            itemsInList = itemsInList + 1;
            itemsInTheListLabel_menu.setText(Integer.toString(itemsInList));
        }
        else if (event.getSource() == Pane4Item6Minus){
            if (Pane4Item6Qty == 0){
                Pane4Item6Qty = 0;
            }
            else {
                Pane4Item6Qty = Pane4Item6Qty - 1;
                itemsInList = itemsInList - 1;
                itemsInTheListLabel_menu.setText(Integer.toString(itemsInList));
            }
        }
    }
}
