package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.converter.DefaultStringConverter;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class methodCalls {

    public static Connection getSQLConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/ERESTO", "root", "");
        return connection;
    }

    public void closeCircleClicked(Circle clickedCircle){
        Stage stage = (Stage) clickedCircle.getScene().getWindow();
        stage.close();
    }

    public void closeButtonClicked(Button clickedButton){
        Stage stage = (Stage) clickedButton.getScene().getWindow();
        stage.close();
    }

    public void informationAlert(Button ownerButton, String contentMessage){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(ownerButton.getScene().getWindow());
        alert.setHeaderText(null);
        alert.setContentText(contentMessage);
        alert.showAndWait();
        alert.setX(alert.getOwner().getX() + (alert.getOwner().getWidth() - alert.getWidth()) / 2);
        alert.setY(alert.getOwner().getY() + (alert.getOwner().getHeight() - alert.getHeight()) / 2);
    }

    public void loadNewFXML(Button initButton, String fxmlSource) throws IOException {
        Stage stage = (Stage) initButton.getScene().getWindow();
        stage.close();
        Stage newStage = new Stage();
        newStage.setScene(new Scene(FXMLLoader.load(getClass().getResource(fxmlSource))));
        newStage.initStyle(StageStyle.UNDECORATED);
        newStage.show();
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        newStage.setX(dimension.getWidth()/2 - newStage.getWidth()/2);
        newStage.setY(dimension.getHeight()/2 - newStage.getHeight()/2);
    }

    public void loadNewFXMLDifferentController(Button initButton, String fxmlSource, String username, String orderRefNo, String orderRefNoRec, String chefName) throws IOException {
        Stage stage = (Stage) initButton.getScene().getWindow();
        stage.close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlSource));
        Parent root = loader.load();
        if (fxmlSource.equals("menu.fxml")){
            MenuController menuController = loader.getController();
            menuController.transferMessage(username);
        }
        else if (fxmlSource.equals("orderList.fxml")){
            OrderListController orderListController = loader.getController();
            orderListController.transferMessage(orderRefNo);
        }
        else if (fxmlSource.equals("payment.fxml")){
            paymentController paymentController = loader.getController();
            paymentController.transferMessage(orderRefNoRec);
        }
        else if (fxmlSource.equals("chef.fxml")){
            ChefController chefController = loader.getController();
            chefController.transferMessage(chefName);
        }
        Stage newStage = new Stage();
        newStage.initStyle(StageStyle.UNDECORATED);
        newStage.setScene(new Scene(root));
        newStage.show();
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        newStage.setX(dimension.getWidth()/2 - newStage.getWidth()/2);
        newStage.setY(dimension.getHeight()/2 - newStage.getHeight()/2);
    }

    static String getReferenceKey(){
        String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvwxyz";
        StringBuilder stringBuilder = new StringBuilder(5);
        for (int i = 0; i < 5; i++){
            int index = (int)(alphaNumericString.length()*Math.random());
            stringBuilder.append(alphaNumericString.charAt(index));
        }
        return stringBuilder.toString();
    }

    public void tableSetColumnData(TableView tableView, TableColumn tableColumn, String tableModelColumnValue,
                                   ObservableList observableList){
        // load data to the tableColumn
        tableColumn.setCellValueFactory(new PropertyValueFactory<>(tableModelColumnValue));

        // fill data to the tableView
        tableView.setItems(null);
        tableView.setItems(observableList);
    }

    public void tableSetColoumnComboBox(TableColumn tableColumn, ObservableList comboBoxValue){
        // make a comboBox column editable and fill comboBox value to the tableColumn
        tableColumn.setCellFactory(ComboBoxTableCell.forTableColumn(new DefaultStringConverter(), comboBoxValue));
    }

    public void executeSQLStatement(String query, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.execute();
    }

    public ResultSet captureSQLResults(String query, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

    public void makeTableEditable(TableView tableView){
        tableView.setEditable(true);
    }

    public void makeTableStringColumnEditable(TableColumn tableColumn){
        tableColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    public int getPriceOfMenuItem(String foodName){
        int pricePerQty = 0;
        if (foodName.equals("NUTTY CHICKEN NOODLES"))
            pricePerQty = 45;
        else if (foodName.equals("FRIED PRAWN NOODLES"))
            pricePerQty = 45;
        else if (foodName.equals("VEGETABLE NOODLES"))
            pricePerQty = 45;
        else if (foodName.equals("STIRE-FRY NOODLES"))
            pricePerQty = 45;
        else if (foodName.equals("SPICY VEGI NOODLES"))
            pricePerQty = 45;
        else if (foodName.equals("SAUSAGE NOODLES"))
            pricePerQty = 45;
        else if (foodName.equals("CHICKEN FRIED RICE"))
            pricePerQty = 35;
        else if (foodName.equals("SEAFOOD FRIED RICE"))
            pricePerQty = 35;
        else if (foodName.equals("BIRIYANI"))
            pricePerQty = 35;
        else if (foodName.equals("NASI GORENG"))
            pricePerQty = 35;
        else if (foodName.equals("MIXED FRIED RICE"))
            pricePerQty = 35;
        else if (foodName.equals("EGG FRIED RICE"))
            pricePerQty = 35;
        else if (foodName.equals("CARAMEL PUDDING"))
            pricePerQty = 25;
        else if (foodName.equals("WATALAPPAN"))
            pricePerQty = 25;
        else if (foodName.equals("FRUIT SALAD"))
            pricePerQty = 25;
        else if (foodName.equals("FRUIT PLATTER"))
            pricePerQty = 25;
        else if (foodName.equals("ICE CREAM"))
            pricePerQty = 25;
        else if (foodName.equals("LAVA CAKE"))
            pricePerQty = 25;
        else if (foodName.equals("CHOCOLATE DRINK"))
            pricePerQty = 15;
        else if (foodName.equals("MANGO COCKTAIL"))
            pricePerQty = 15;
        else if (foodName.equals("NUTELLA"))
            pricePerQty = 15;
        else if (foodName.equals("ORANGE JUICE"))
            pricePerQty = 15;
        else if (foodName.equals("OREO MILKSHAKE"))
            pricePerQty = 15;
        else if (foodName.equals("STRAWBERRY MILKSHAKE"))
            pricePerQty = 15;
        return pricePerQty;
    }

    public void generateReport() throws IOException, SQLException, ClassNotFoundException {
        File file = new File("Report.txt");
        if (!file.exists())
            file.createNewFile();
        PrintWriter printWriter = new PrintWriter(file);
        printWriter.println("----- Popular Menu Items - Report -----");
        printWriter.println("_______________________________________");
        printWriter.println("");
        printWriter.println("Menu Item                       Quantity");
        ResultSet resultSet0 = methodCalls.getSQLConnection().prepareStatement("SELECT DISTINCT `FoodName` FROM `orders` ORDER BY `Quantity`").executeQuery();
        while (resultSet0.next()){
            int quantity = 0;
            if (resultSet0.getString("FoodName").equals("NUTTY CHICKEN NOODLES")){
                ResultSet resultSet = methodCalls.getSQLConnection().prepareStatement("SELECT `Quantity` FROM `orders` WHERE `FoodName`='NUTTY CHICKEN NOODLES'").executeQuery();
                while (resultSet.next()){
                    quantity = quantity + Integer.parseInt(resultSet.getString("Quantity"));
                }
                printWriter.println("NUTTY CHICKEN NOODLES           " + quantity);
            }
            else if (resultSet0.getString("FoodName").equals("FRIED PRAWN NOODLES")){
                ResultSet resultSet = methodCalls.getSQLConnection().prepareStatement("SELECT `Quantity` FROM `orders` WHERE `FoodName`='FRIED PRAWN NOODLES'").executeQuery();
                while (resultSet.next()){
                    quantity = quantity + Integer.parseInt(resultSet.getString("Quantity"));
                }
                printWriter.println("FRIED PRAWN NOODLES             " + quantity);
            }
            else if (resultSet0.getString("FoodName").equals("VEGETABLE NOODLES")){
                ResultSet resultSet = methodCalls.getSQLConnection().prepareStatement("SELECT `Quantity` FROM `orders` WHERE `FoodName`='VEGETABLE NOODLES'").executeQuery();
                while (resultSet.next()){
                    quantity = quantity + Integer.parseInt(resultSet.getString("Quantity"));
                }
                printWriter.println("VEGETABLE NOODLES               " + quantity);
            }
            else if (resultSet0.getString("FoodName").equals("STIRE-FRY NOODLES")){
                ResultSet resultSet = methodCalls.getSQLConnection().prepareStatement("SELECT `Quantity` FROM `orders` WHERE `FoodName`='STIRE-FRY NOODLES'").executeQuery();
                while (resultSet.next()){
                    quantity = quantity + Integer.parseInt(resultSet.getString("Quantity"));
                }
                printWriter.println("STIRE-FRY NOODLES               " + quantity);
            }
            else if (resultSet0.getString("FoodName").equals("SPICY VEGI NOODLES")){
                ResultSet resultSet = methodCalls.getSQLConnection().prepareStatement("SELECT `Quantity` FROM `orders` WHERE `FoodName`='SPICY VEGI NOODLES'").executeQuery();
                while (resultSet.next()){
                    quantity = quantity + Integer.parseInt(resultSet.getString("Quantity"));
                }
                printWriter.println("SPICY VEGI NOODLES              " + quantity);
            }
            else if (resultSet0.getString("FoodName").equals("SAUSAGE NOODLES")){
                ResultSet resultSet = methodCalls.getSQLConnection().prepareStatement("SELECT `Quantity` FROM `orders` WHERE `FoodName`='SAUSAGE NOODLES'").executeQuery();
                while (resultSet.next()){
                    quantity = quantity + Integer.parseInt(resultSet.getString("Quantity"));
                }
                printWriter.println("SAUSAGE NOODLES                 " + quantity);
            }
            else if (resultSet0.getString("FoodName").equals("CHICKEN FRIED RICE")){
                ResultSet resultSet = methodCalls.getSQLConnection().prepareStatement("SELECT `Quantity` FROM `orders` WHERE `FoodName`='CHICKEN FRIED RICE'").executeQuery();
                while (resultSet.next()){
                    quantity = quantity + Integer.parseInt(resultSet.getString("Quantity"));
                }
                printWriter.println("CHICKEN FRIED RICE              " + quantity);
            }
            else if (resultSet0.getString("FoodName").equals("SEAFOOD FRIED RICE")){
                ResultSet resultSet = methodCalls.getSQLConnection().prepareStatement("SELECT `Quantity` FROM `orders` WHERE `FoodName`='SEAFOOD FRIED RICE'").executeQuery();
                while (resultSet.next()){
                    quantity = quantity + Integer.parseInt(resultSet.getString("Quantity"));
                }
                printWriter.println("SEAFOOD FRIED RICE              " + quantity);
            }
            else if (resultSet0.getString("FoodName").equals("BIRIYANI")){
                ResultSet resultSet = methodCalls.getSQLConnection().prepareStatement("SELECT `Quantity` FROM `orders` WHERE `FoodName`='BIRIYANI'").executeQuery();
                while (resultSet.next()){
                    quantity = quantity + Integer.parseInt(resultSet.getString("Quantity"));
                }
                printWriter.println("BIRIYANI                        " + quantity);
            }
            else if (resultSet0.getString("FoodName").equals("NASI GORENG")){
                ResultSet resultSet = methodCalls.getSQLConnection().prepareStatement("SELECT `Quantity` FROM `orders` WHERE `FoodName`='NASI GORENG'").executeQuery();
                while (resultSet.next()){
                    quantity = quantity + Integer.parseInt(resultSet.getString("Quantity"));
                }
                printWriter.println("NASI GORENG                     " + quantity);
            }
            else if (resultSet0.getString("FoodName").equals("MIXED FRIED RICE")){
                ResultSet resultSet = methodCalls.getSQLConnection().prepareStatement("SELECT `Quantity` FROM `orders` WHERE `FoodName`='MIXED FRIED RICE'").executeQuery();
                while (resultSet.next()){
                    quantity = quantity + Integer.parseInt(resultSet.getString("Quantity"));
                }
                printWriter.println("MIXED FRIED RICE                " + quantity);
            }
            else if (resultSet0.getString("FoodName").equals("EGG FRIED RICE")){
                ResultSet resultSet = methodCalls.getSQLConnection().prepareStatement("SELECT `Quantity` FROM `orders` WHERE `FoodName`='EGG FRIED RICE'").executeQuery();
                while (resultSet.next()){
                    quantity = quantity + Integer.parseInt(resultSet.getString("Quantity"));
                }
                printWriter.println("EGG FRIED RICE                  " + quantity);
            }
            else if (resultSet0.getString("FoodName").equals("CARAMEL PUDDING")){
                ResultSet resultSet = methodCalls.getSQLConnection().prepareStatement("SELECT `Quantity` FROM `orders` WHERE `FoodName`='CARAMEL PUDDING'").executeQuery();
                while (resultSet.next()){
                    quantity = quantity + Integer.parseInt(resultSet.getString("Quantity"));
                }
                printWriter.println("CARAMEL PUDDING                 " + quantity);
            }
            else if (resultSet0.getString("FoodName").equals("WATALAPPAN")){
                ResultSet resultSet = methodCalls.getSQLConnection().prepareStatement("SELECT `Quantity` FROM `orders` WHERE `FoodName`='WATALAPPAN'").executeQuery();
                while (resultSet.next()){
                    quantity = quantity + Integer.parseInt(resultSet.getString("Quantity"));
                }
                printWriter.println("WATALAPPAN                      " + quantity);
            }
            else if (resultSet0.getString("FoodName").equals("FRUIT SALAD")){
                ResultSet resultSet = methodCalls.getSQLConnection().prepareStatement("SELECT `Quantity` FROM `orders` WHERE `FoodName`='FRUIT SALAD'").executeQuery();
                while (resultSet.next()){
                    quantity = quantity + Integer.parseInt(resultSet.getString("Quantity"));
                }
                printWriter.println("FRUIT SALAD                     " + quantity);
            }
            else if (resultSet0.getString("FoodName").equals("FRUIT PLATTER")){
                ResultSet resultSet = methodCalls.getSQLConnection().prepareStatement("SELECT `Quantity` FROM `orders` WHERE `FoodName`='FRUIT PLATTER'").executeQuery();
                while (resultSet.next()){
                    quantity = quantity + Integer.parseInt(resultSet.getString("Quantity"));
                }
                printWriter.println("FRUIT PLATTER                   " + quantity);
            }
            else if (resultSet0.getString("FoodName").equals("ICE CREAM")){
                ResultSet resultSet = methodCalls.getSQLConnection().prepareStatement("SELECT `Quantity` FROM `orders` WHERE `FoodName`='ICE CREAM'").executeQuery();
                while (resultSet.next()){
                    quantity = quantity + Integer.parseInt(resultSet.getString("Quantity"));
                }
                printWriter.println("ICE CREAM                       " + quantity);
            }
            else if (resultSet0.getString("FoodName").equals("LAVA CAKE")){
                ResultSet resultSet = methodCalls.getSQLConnection().prepareStatement("SELECT `Quantity` FROM `orders` WHERE `FoodName`='LAVA CAKE'").executeQuery();
                while (resultSet.next()){
                    quantity = quantity + Integer.parseInt(resultSet.getString("Quantity"));
                }
                printWriter.println("LAVA CAKE                       " + quantity);
            }
            else if (resultSet0.getString("FoodName").equals("CHOCOLATE DRINK")){
                ResultSet resultSet = methodCalls.getSQLConnection().prepareStatement("SELECT `Quantity` FROM `orders` WHERE `FoodName`='CHOCOLATE DRINK'").executeQuery();
                while (resultSet.next()){
                    quantity = quantity + Integer.parseInt(resultSet.getString("Quantity"));
                }
                printWriter.println("CHOCOLATE DRINK                 " + quantity);
            }
            else if (resultSet0.getString("FoodName").equals("MANGO COCKTAIL")){
                ResultSet resultSet = methodCalls.getSQLConnection().prepareStatement("SELECT `Quantity` FROM `orders` WHERE `FoodName`='MANGO COCKTAIL'").executeQuery();
                while (resultSet.next()){
                    quantity = quantity + Integer.parseInt(resultSet.getString("Quantity"));
                }
                printWriter.println("MANGO COCKTAIL                  " + quantity);
            }
            else if (resultSet0.getString("FoodName").equals("NUTELLA")){
                ResultSet resultSet = methodCalls.getSQLConnection().prepareStatement("SELECT `Quantity` FROM `orders` WHERE `FoodName`='NUTELLA'").executeQuery();
                while (resultSet.next()){
                    quantity = quantity + Integer.parseInt(resultSet.getString("Quantity"));
                }
                printWriter.println("NUTELLA                         " + quantity);
            }
            else if (resultSet0.getString("FoodName").equals("ORANGE JUICE")){
                ResultSet resultSet = methodCalls.getSQLConnection().prepareStatement("SELECT `Quantity` FROM `orders` WHERE `FoodName`='ORANGE JUICE'").executeQuery();
                while (resultSet.next()){
                    quantity = quantity + Integer.parseInt(resultSet.getString("Quantity"));
                }
                printWriter.println("ORANGE JUICE                    " + quantity);
            }
            else if (resultSet0.getString("FoodName").equals("OREO MILKSHAKE")){
                ResultSet resultSet = methodCalls.getSQLConnection().prepareStatement("SELECT `Quantity` FROM `orders` WHERE `FoodName`='OREO MILKSHAKE'").executeQuery();
                while (resultSet.next()){
                    quantity = quantity + Integer.parseInt(resultSet.getString("Quantity"));
                }
                printWriter.println("OREO MILKSHAKE                  " + quantity);
            }
            else if (resultSet0.getString("FoodName").equals("STRAWBERRY MILKSHAKE")){
                ResultSet resultSet = methodCalls.getSQLConnection().prepareStatement("SELECT `Quantity` FROM `orders` WHERE `FoodName`='STRAWBERRY MILKSHAKE'").executeQuery();
                while (resultSet.next()){
                    quantity = quantity + Integer.parseInt(resultSet.getString("Quantity"));
                }
                printWriter.println("STRAWBERRY MILKSHAKE            " + quantity);
            }
        }
        printWriter.close();
    }
}
