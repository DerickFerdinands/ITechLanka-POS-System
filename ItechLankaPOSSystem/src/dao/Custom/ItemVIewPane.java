package dao.Custom;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Effect;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.WHITE;

public class ItemVIewPane {

    private static Label getLabel(double prefWidth, double prefHeight, String labelText, ImageView setGraphic, String style, double layoutX, double layoutY) {
        Label label = new Label();
        label.setText(labelText);
        label.setPrefSize(prefWidth, prefHeight);
        label.setGraphic(setGraphic);
        label.setStyle(style);
        label.setLayoutX(layoutX);
        label.setLayoutY(layoutY);

        return label;
    }

    public static ImageView getImageview(double opacity, double fitWidth, double fitHeight, double layoutX, double layoutY, String imageLocation) {
        ImageView view = new ImageView(imageLocation);
        view.setOpacity(opacity);
        view.setFitWidth(fitWidth);
        view.setFitHeight(fitHeight);
        view.setLayoutX(layoutX);
        view.setLayoutY(layoutY);
        view.setSmooth(true);
        return view;
    }

    public static HBox getHBox(double prefWidth, double prefHeight, double layoutX, double layoutY, Pos Allignment, Node... addALl) {
        HBox box1 = new HBox();
        box1.setPrefSize(prefWidth, prefHeight);
        box1.setLayoutX(layoutX);
        box1.setLayoutY(layoutY);
        box1.getChildren().addAll(addALl);
        box1.setAlignment(Allignment);
        return box1;
    }

    public static Label getLabel(String name, Paint value, TextAlignment Allignment, Font font) {
        Label label1 = new Label(name);
        label1.setTextFill(value);
        label1.setTextAlignment(Allignment);
        label1.setFont(font);

        return label1;
    }

    public static JFXButton getButton(String Text, Paint value, double prefWIdth, double prefHeight, double layoutX, double layoutY, String style, ImageView view) {
        JFXButton btn3 = new JFXButton();
        btn3.setText(Text);
        btn3.setContentDisplay(ContentDisplay.RIGHT);
        btn3.setGraphicTextGap(4);
        if (value != null)
            btn3.setTextFill(value);
        btn3.setPrefSize(prefWIdth, prefHeight);
        btn3.setLayoutX(layoutX);
        btn3.setLayoutY(layoutY);
        btn3.setStyle(style);
        ColorAdjust ca = new ColorAdjust();
        ca.setBrightness(0.95);
        if (view != null) {
            view.setEffect(ca);
        }
        btn3.setGraphic(view);

        btn3.setOnMouseExited(event -> {
            btn3.setStyle(style);
            if (value != null) {

            } else {
                btn3.setTextFill(BLACK);
            }
        });

        btn3.setOnMouseEntered(event -> {
            if (value != null) {

            } else {
                btn3.setStyle("-fx-background-color: #BCBCBC");
                btn3.setTextFill(WHITE);
            }
        });
        return btn3;
    }

    public static AnchorPane getItemView(String name, String desc, String price, String imageURL, String topLabel, double layoutX, double layoutY) {

        JFXButton btnAddToCart = getButton("Add To Cart", Paint.valueOf("#cdd9e1"), 213, 34, 25, 276, "-fx-background-color: #003171",
                getImageview(1, 25, 24, 0, 0, "Assets/add_shopping_cart_FILL0_wght400_GRAD0_opsz48.png"));
        JFXButton btnAddQty = getButton("+", null, 51, 30, 185, 230, "-fx-border-color: #BCBCBC;" + "-fx-border-radius:4;", null);
        JFXButton btnSubFromCart = getButton("-", null, 51, 30, 27, 230, "-fx-border-color: #BCBCBC;" + "-fx-border-radius:4;", null);

        AnchorPane pane = new AnchorPane();
        pane.setStyle("-fx-background-color:  #FFFFFF;" + "-fx-background-radius: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: #DADADA;" + "-fx-border-width:2;");
        pane.setPrefSize(262, 333);
        pane.getChildren().addAll(

                getLabel(70, 10, topLabel,
                        getImageview(0.56, 14, 14, 0, 0, "Assets/star_FILL0_wght400_GRAD0_opsz48.png"),
                        "-fx-border-color: #DADADA;" + "-fx-border-width: 1;" + "-fx-background-radius: 3;" + "-fx-border-radius: 3;", 14, 29),

                getImageview(0.59, 28, 30, 223, 25, "Assets/bookmark_FILL0_wght400_GRAD0_opsz48.png"),

                getImageview(1, 132, 95, 66, 53, "file:" + imageURL),

                getHBox(252, 13, 4, 157, Pos.CENTER,
                        getLabel(name, Paint.valueOf("#615959"), TextAlignment.CENTER, new Font("Arial", 19))),

                getHBox(252, 13, 4, 180, Pos.CENTER,
                        getLabel(desc, Paint.valueOf("#807b7b"), TextAlignment.CENTER, new Font("Arial", 14))),

                getHBox(252, 13, 4, 203, Pos.CENTER,
                        getLabel(price, Paint.valueOf("#615959"), TextAlignment.CENTER, new Font("Arial", 18))),
                btnSubFromCart, btnAddQty, btnAddToCart
        );


        JFXTextField field = new JFXTextField();
        field.setFocusColor(Paint.valueOf("#4059a500"));
        field.setUnFocusColor(Paint.valueOf("#4d4d4d00"));
        field.setPromptText("1");
        field.setFont(new Font("System", 12));
        field.setAlignment(Pos.CENTER);
        field.setStyle("-fx-border-color: #BCBCBC;" + "-fx-border-radius:4");
        field.setPrefSize(93, 34);
        field.setLayoutX(85);
        field.setLayoutY(229);

        pane.getChildren().add(field);
        pane.setLayoutX(layoutX);
        pane.setLayoutY(layoutY);
        return pane;

    }
}
