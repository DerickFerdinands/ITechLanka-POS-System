package Model;

import com.jfoenix.controls.JFXButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DashboardButton {
    private JFXButton btn;
    private ImageView view;
    private Image unselectedImage;

    public DashboardButton() {
    }

    public DashboardButton(JFXButton btn, ImageView view, Image unselectedImage, Image selectedImage) {
        this.btn = btn;
        this.view = view;
        this.unselectedImage = unselectedImage;
        this.selectedImage = selectedImage;
    }

    @Override
    public String toString() {
        return "DashboardButton{" +
                "btn=" + btn +
                ", view=" + view +
                ", unselectedImage=" + unselectedImage +
                ", selectedImage=" + selectedImage +
                '}';
    }

    public JFXButton getBtn() {
        return btn;
    }

    public void setBtn(JFXButton btn) {
        this.btn = btn;
    }

    public ImageView getView() {
        return view;
    }

    public void setView(ImageView view) {
        this.view = view;
    }

    public Image getUnselectedImage() {
        return unselectedImage;
    }

    public void setUnselectedImage(Image unselectedImage) {
        this.unselectedImage = unselectedImage;
    }

    public Image getSelectedImage() {
        return selectedImage;
    }

    public void setSelectedImage(Image selectedImage) {
        this.selectedImage = selectedImage;
    }

    private Image selectedImage;
}
