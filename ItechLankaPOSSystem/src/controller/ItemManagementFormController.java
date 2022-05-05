package controller;

import Model.Item;
import Util.NotificationUtil;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import dao.ItemCRUDController;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import sun.net.www.content.image.png;
import tray.animations.AnimationType;
import tray.notification.NotificationType;

import java.io.File;
import java.nio.file.Files;
import java.sql.SQLException;

public class ItemManagementFormController {
    public JFXTextField txtCode;
    public JFXTextField txtQty;
    public JFXTextField txtBuyingPrice;
    public JFXButton btnAddItem;
    public JFXTextField txtSellingPrice;
    public JFXComboBox cmbCategory;
    public JFXTextField txtName;
    public AnchorPane nchrPnImage;
    public JFXTextArea txtrDetails;
    public ImageView imgSelectedImage;
    public AnchorPane ItemMainPane;
    File file;

    public void ResetFormOnAction(ActionEvent actionEvent) {
    }

    public void OpenFileSelector(MouseEvent mouseEvent) {
        FileChooser selectFile = new FileChooser();
        selectFile.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Images", "*.png", "*.jpeg", "*.jpg"));
        file = selectFile.showOpenDialog(ItemMainPane.getScene().getWindow());
        if (file != null) {
            setImageProperties(new Image("file:" + file.getPath()), 1, 145, 152, 0, 0);
        } else {
            setImageProperties(new Image("Assets/image_FILL0_wght400_GRAD0_opsz48.png"), 0.65, 52, 48, 49, 52);
        }
    }

    public void setImageProperties(Image img, double opacity, double height, double width, double layoutY, double layoutX) {
        imgSelectedImage.setImage(img);
        imgSelectedImage.setOpacity(opacity);
        imgSelectedImage.setFitHeight(height);
        imgSelectedImage.setFitWidth(width);
        imgSelectedImage.setLayoutY(layoutY);
        imgSelectedImage.setLayoutX(layoutX);
    }

    public void addItemOnAction(ActionEvent actionEvent) {
        try {
            if (ItemCRUDController.saveItem(new Item(txtCode.getText(), txtName.getText(), String.valueOf(cmbCategory.getValue()), Double.valueOf(txtBuyingPrice.getText()), Double.valueOf(txtSellingPrice.getText()), Double.valueOf(txtQty.getText()), txtrDetails.getText(), file.getPath()))) {
                NotificationUtil.playNotification(AnimationType.POPUP, "Item Successfully Added!", NotificationType.SUCCESS, Duration.millis(3000));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void initialize(){
        cmbCategory.getItems().addAll("CCTV","SOLAR PANEL");

    }
}
