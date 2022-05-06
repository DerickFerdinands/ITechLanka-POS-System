package Model;

import com.jfoenix.controls.JFXButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;

public class DashboardButton {
    private JFXButton btn;
    private ImageView view;
    private Image unselectedImage;
    private int imageLayoutY;
    private Image selectedImage;
    private Line line;

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }

    public DashboardButton(JFXButton btn, ImageView view, Image unselectedImage, int imageLayoutY, Image selectedImage, Line line) {
        this.btn = btn;
        this.view = view;
        this.unselectedImage = unselectedImage;
        this.imageLayoutY = imageLayoutY;
        this.selectedImage = selectedImage;
        this.line = line;
    }

    public DashboardButton(JFXButton btn, ImageView view, Image unselectedImage, int imageLayoutY, Image selectedImage) {
        this.btn = btn;
        this.view = view;
        this.unselectedImage = unselectedImage;
        this.imageLayoutY = imageLayoutY;
        this.selectedImage = selectedImage;
    }

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
                ", imageLayoutY=" + imageLayoutY +
                ", selectedImage=" + selectedImage +
                '}';
    }

    public int getImageLayoutY() {
        return imageLayoutY;
    }

    public void setImageLayoutY(int imageLayoutY) {
        this.imageLayoutY = imageLayoutY;
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
}
