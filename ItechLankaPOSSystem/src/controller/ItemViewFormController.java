package controller;

import bo.ItemBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dao.Custom.ItemVIewPane;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.sql.SQLException;

public class ItemViewFormController {
    private final ItemBO IBO = new ItemBO();
    public AnchorPane ItemViewMainPane;
    public AnchorPane ScrollerPane;

    public void searchMatchingItems(KeyEvent keyEvent) {

    }

    public void initialize() {
        ScrollerPane.getChildren().clear();
        loadAllItems();
    }

    private void loadAllItems() {
        try {
            IBO.SetItemView(ScrollerPane);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
