package bo;

import Model.ItemDTO;
import dao.Custom.ItemDAO;
import dao.Custom.ItemVIewPane;
import dao.Custom.impl.ItemCRUDController;
import entity.Item;
import javafx.scene.layout.AnchorPane;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBO {

    private ItemDAO ItemCrudOps = new ItemCRUDController();

    public void SetItemView(AnchorPane pane) throws Exception {
        ArrayList<Item> itemDTOList = ItemCrudOps.getAll();
        ArrayList<Double> LayoutXValues = new ArrayList<>();

        double layoutX = 0;
        double layoutY = 10;
        for (Item i : itemDTOList) {
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
            pane.getChildren().add(ItemVIewPane.getItemView(i.getName(), i.getDetails(), i.getSellingPrice() + "", i.getImageLocation(), "Latest", layoutX, layoutY));
        }
    }
}
