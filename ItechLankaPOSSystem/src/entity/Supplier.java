package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Supplier {
    @Id
    private String Id;
    private String name;
    private String email;
    private String address;
    private String mobile;

    @Override
    public String toString() {
        return "Supplier{" +
                "Id='" + Id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", mobile='" + mobile + '\'' +
                ", purchaseList=" + purchaseList +
                '}';
    }

    public List<Purchase> getPurchaseList() {
        return purchaseList;
    }

    public void setPurchaseList(List<Purchase> purchaseList) {
        this.purchaseList = purchaseList;
    }

    public Supplier(String id, String name, String email, String address, String mobile, List<Purchase> purchaseList) {
        Id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.mobile = mobile;
        this.purchaseList = purchaseList;
    }

    @OneToMany(mappedBy = "supplier")
    private  List<Purchase> purchaseList = new ArrayList<>();

    public Supplier() {
    }

    public Supplier(String id, String name, String email, String address, String mobile) {
        Id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.mobile = mobile;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
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
}
