package View.TM;

import com.jfoenix.controls.JFXButton;

public class CustomerTM {
    private String id;
    private String name;
    private String nic;
    private String mobile;
    private String address;
    private JFXButton btn;

    @Override
    public String toString() {
        return "CustomerTM{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", nic='" + nic + '\'' +
                ", mobile='" + mobile + '\'' +
                ", address='" + address + '\'' +
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

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public JFXButton getBtn() {
        return btn;
    }

    public void setBtn(JFXButton btn) {
        this.btn = btn;
    }

    public CustomerTM() {
    }

    public CustomerTM(String id, String name, String nic, String mobile, String address, JFXButton btn) {
        this.id = id;
        this.name = name;
        this.nic = nic;
        this.mobile = mobile;
        this.address = address;
        this.btn = btn;
    }
}
