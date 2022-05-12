package dao.Custom;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class ItemVIewPane {

    public static AnchorPane getItemView(String name, String desc, String price, String imageURL, String topLabel,double layoutX, double layoutY){

        AnchorPane pane = new AnchorPane();
        pane.setStyle("-fx-background-color:  #FFFFFF;" + "-fx-background-radius: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: #DADADA;" + "-fx-border-width:2;");
        pane.setPrefSize(262, 333);

        Label label = new Label();
        label.setText(topLabel);
        label.setPrefSize(68, 19);
        ImageView imageView = new ImageView("Assets/star_FILL0_wght400_GRAD0_opsz48.png");
        imageView.setOpacity(0.56);
        imageView.setFitWidth(14);
        imageView.setFitHeight(14);
        label.setGraphic(imageView);
        label.setStyle("-fx-border-color: #DADADA;" + "-fx-border-width: 1;" + "-fx-background-radius: 3;" + "-fx-border-radius: 3;");
        label.setLayoutX(14);
        label.setLayoutY(29);
        pane.getChildren().add(label);

        ImageView view = new ImageView("Assets/bookmark_FILL0_wght400_GRAD0_opsz48.png");
        view.setOpacity(0.59);
        view.setFitWidth(28);
        view.setFitHeight(30);
        view.setLayoutX(223);
        view.setLayoutY(25);

        pane.getChildren().add(view);

        ImageView ItemView = new ImageView("file:"+imageURL);
        ItemView.setFitWidth(132);
        ItemView.setFitHeight(95);
        ItemView.setLayoutX(66);
        ItemView.setLayoutY(53);
        ItemView.setSmooth(true);

        pane.getChildren().add(ItemView);

        HBox box1 = new HBox();
        box1.setPrefSize(252, 13);
        box1.setLayoutX(4);
        box1.setLayoutY(157);

        Label label1 = new Label(name);
        label.setTextFill(Paint.valueOf("#615959"));
        label1.setTextAlignment(TextAlignment.CENTER);
        label1.setFont(new Font("Arial", 17));

        box1.getChildren().add(label1);
        box1.setAlignment(Pos.CENTER);
        pane.getChildren().add(box1);

        HBox box2 = new HBox();
        box2.setPrefSize(252, 13);
        box2.setLayoutX(4);
        box2.setLayoutY(180);

        Label label2 = new Label(desc);
        label2.setTextFill(Paint.valueOf("#807b7b"));
        label2.setTextAlignment(TextAlignment.CENTER);
        label2.setFont(new Font("Arial", 14));

        box2.getChildren().add(label2);
        box2.setAlignment(Pos.CENTER);

        pane.getChildren().add(box2);

        HBox box3 = new HBox();
        box3.setPrefSize(252, 13);
        box3.setLayoutX(4);
        box3.setLayoutY(203);

        Label label3 = new Label(price);
        label3.setTextFill(Paint.valueOf("#615959"));
        label3.setTextAlignment(TextAlignment.CENTER);
        label3.setFont(new Font("Arial", 18));

        box3.getChildren().add(label3);
        box3.setAlignment(Pos.CENTER);

        pane.getChildren().add(box3);

        JFXButton btn1 = new JFXButton("-");
        btn1.setStyle("-fx-border-color: #BCBCBC;" + "-fx-border-radius:4;");
        btn1.setPrefSize(51, 30);
        btn1.setLayoutX(27);
        btn1.setLayoutY(230);

        pane.getChildren().add(btn1);

        JFXButton btn2 = new JFXButton("+");
        btn2.setStyle("-fx-border-color: #BCBCBC;" + "-fx-border-radius:4;");
        btn2.setPrefSize(51, 30);
        btn2.setLayoutX(185);
        btn2.setLayoutY(230);

        pane.getChildren().add(btn2);

        JFXButton btn3 = new JFXButton("Add To Cart");
        btn3.setContentDisplay(ContentDisplay.RIGHT);
        btn3.setGraphicTextGap(4);
        btn3.setTextFill(Paint.valueOf("#cdd9e1"));
        btn3.setPrefSize(213, 34);
        btn3.setLayoutX(25);
        btn3.setLayoutY(276);
        btn3.setStyle("-fx-background-color: #003171");
        ImageView img4 = new ImageView("Assets/add_shopping_cart_FILL0_wght400_GRAD0_opsz48.png");
        ColorAdjust ca = new ColorAdjust();
        ca.setBrightness(0.95);
        img4.setEffect(ca);
        img4.setFitHeight(24);
        img4.setFitWidth(25);
        btn3.setGraphic(img4);

        pane.getChildren().add(btn3);

        JFXTextField field = new JFXTextField();
        field.setFocusColor(Paint.valueOf("#4059a500"));
        field.setUnFocusColor(Paint.valueOf("#4d4d4d00"));
        field.setPromptText("1");
        field.setFont(new Font("System",12));
        field.setAlignment(Pos.CENTER);
        field.setStyle("-fx-border-color: #BCBCBC;"+"-fx-border-radius:4");
        field.setPrefSize(93,34);
        field.setLayoutX(85);
        field.setLayoutY(229);

        pane.getChildren().add(field);
        pane.setLayoutX(layoutX);
        pane.setLayoutY(layoutY);
        return pane;

    }
}
