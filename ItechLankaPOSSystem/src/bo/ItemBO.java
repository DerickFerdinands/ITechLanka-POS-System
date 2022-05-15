package bo;

import Model.Item;
import dao.Custom.ItemVIewPane;
import dao.Custom.impl.ItemCRUDController;
import javafx.scene.layout.AnchorPane;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBO {

    public void SetItemView(AnchorPane pane) throws SQLException, ClassNotFoundException {
        ArrayList<Item> ItemList = ItemCRUDController.getAllItems();
        ArrayList<Double> LayoutXValues = new ArrayList<>();

        double layoutX = 0;
        double layoutY = 10;
        for (Item i : ItemList) {
            System.out.println(i);
            if (LayoutXValues.size() == 0) {
                layoutX = 50;
                LayoutXValues.add(layoutX);
            } else if ((LayoutXValues.get(LayoutXValues.size()-1) + 300) >= 1500) {
                LayoutXValues.clear();
                layoutX = 50;
                LayoutXValues.add(layoutX);
                layoutY+=350;

                if((layoutY+333)>pane.getPrefHeight()){
                    double height=pane.getPrefHeight();
                    pane.setPrefHeight(height+333);
                }
            } else {
                layoutX = LayoutXValues.get(LayoutXValues.size()-1) + 300;
                LayoutXValues.add(layoutX);
            }
            System.out.println(layoutX+" "+layoutY);
            pane.getChildren().add(ItemVIewPane.getItemView(i.getName(), i.getDetail(), i.getSellingPrice() + "", i.getImageLocation(), "Latest", layoutX, layoutY));
        }
    }
}
