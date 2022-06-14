package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Item {
    @Id
    private String code;
    private String name;
    private String category;
    private double buyingPrice;
    private double sellingPrice;
    private int qty;
    private String details;
    private String imageLocation;
    @OneToMany(mappedBy = "item")
    private List<PurchaseDetail> purchaseDetailList = new ArrayList<>();
    @OneToMany(mappedBy = "item")
    private List<OrderDetail> orderDetailList = new ArrayList<>();
    public Item(String code, String name, String category, double buyingPrice, double sellingPrice, int qty, String details, String imageLocation, List<PurchaseDetail> purchaseDetailList) {
        this.code = code;
        this.name = name;
        this.category = category;
        this.buyingPrice = buyingPrice;
        this.sellingPrice = sellingPrice;
        this.qty = qty;
        this.details = details;
        this.imageLocation = imageLocation;
        this.purchaseDetailList = purchaseDetailList;
    }
    public Item(String code, String name, String category, double buyingPrice, double sellingPrice, int qty, String details, String imageLocation, List<PurchaseDetail> purchaseDetailList, List<OrderDetail> orderDetailList) {
        this.code = code;
        this.name = name;
        this.category = category;
        this.buyingPrice = buyingPrice;
        this.sellingPrice = sellingPrice;
        this.qty = qty;
        this.details = details;
        this.imageLocation = imageLocation;
        this.purchaseDetailList = purchaseDetailList;
        this.orderDetailList = orderDetailList;
    }

    public Item() {
    }

    public Item(String code, String name, String category, double buyingPrice, double sellingPrice, int qty, String details, String imageLocation) {
        this.code = code;
        this.name = name;
        this.category = category;
        this.buyingPrice = buyingPrice;
        this.sellingPrice = sellingPrice;
        this.qty = qty;
        this.details = details;
        this.imageLocation = imageLocation;
    }

    public List<PurchaseDetail> getPurchaseDetailList() {
        return purchaseDetailList;
    }

    public void setPurchaseDetailList(List<PurchaseDetail> purchaseDetailList) {
        this.purchaseDetailList = purchaseDetailList;
    }

    public List<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

    @Override
    public String toString() {
        return "Item{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", buyingPrice=" + buyingPrice +
                ", sellingPrice=" + sellingPrice +
                ", qty=" + qty +
                ", details='" + details + '\'' +
                ", imageLocation='" + imageLocation + '\'' +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(double buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getImageLocation() {
        return imageLocation;
    }

    public void setImageLocation(String imageLocation) {
        this.imageLocation = imageLocation;
    }
}
