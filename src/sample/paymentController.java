package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class paymentController implements Initializable {
    public paymentController() throws SQLException, ClassNotFoundException {
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        slideshow_payment();
    }

    private void slideshow_payment() {
        ArrayList<Image> images = new ArrayList<Image>();
        images.add(new Image("/b2.jpg"));
        images.add(new Image("/b3.jpg"));
        images.add(new Image("/b4.jpg"));
        images.add(new Image("/b1.jpg"));

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
            imageView_payment.setImage(images.get(count));
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
    @FXML private ImageView imageView_payment;
    private int count;
    @FXML private Circle closeCircle_payment;
    @FXML private Button cardBtn_payment, cashBtn_payment;

    public void transferMessage(String message){
        orderRefNoRec = message;
    }

    public void closeCircleClicked(MouseEvent event) {
        methodCalls.closeCircleClicked(closeCircle_payment);
    }

    public void btnClicked_payment(MouseEvent event) {
        methodCalls.informationAlert(cashBtn_payment, "Thank you !\nYour order number is "+orderRefNoRec+"\nEnjoy your meal");
        methodCalls.closeButtonClicked(cardBtn_payment);
    }
}
