package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class PurchaseDetail {
    @Id
    private String id;
    @ManyToOne
    private Purchase purchase;
    @ManyToOne
    private Item item;
    private double buyingPrice;
    private int Qty;

    public PurchaseDetail() {
    }

    public PurchaseDetail(String id, Purchase purchase, Item item, double buyingPrice, int qty) {
        this.id = id;
        this.purchase = purchase;
        this.item = item;
        this.buyingPrice = buyingPrice;
        Qty = qty;
    }

    @Override
    public String toString() {
        return "PurchaseDetail{" +
                "id='" + id + '\'' +
                ", purchase=" + purchase +
                ", item=" + item +
                ", buyingPrice=" + buyingPrice +
                ", Qty=" + Qty +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public double getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(double buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public int getQty() {
        return Qty;
    }

    public void setQty(int qty) {
        Qty = qty;
    }
}
