package View.TM;

import com.jfoenix.controls.JFXButton;

public class SupplierTM {
    private String id;
    private String name;
    private String email;
    private String address;
    private String mobile;
    private JFXButton btn;

    public SupplierTM() {
    }

    public SupplierTM(String id, String name, String email, String address, String mobile, JFXButton btn) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.mobile = mobile;
        this.btn = btn;
    }

    @Override
    public String toString() {
        return "SupplierTM{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", mobile='" + mobile + '\'' +
                ", btn=" + btn +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public JFXButton getBtn() {
        return btn;
    }

    public void setBtn(JFXButton btn) {
        this.btn = btn;
    }
}
