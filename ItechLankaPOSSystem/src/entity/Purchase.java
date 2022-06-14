package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Purchase {
    @Id
    private String ID;
    @ManyToOne
    private Supplier supplier;
    private double total;
    private LocalDate date;
    @OneToMany(mappedBy = "purchase")
    private List<PurchaseDetail> purchaseDetailList = new ArrayList<>();

    public Purchase(String ID, Supplier supplier, double total, LocalDate date) {
        this.ID = ID;
        this.supplier = supplier;
        this.total = total;
        this.date = date;
    }

    public Purchase(String ID, Supplier supplier, double total, LocalDate date, List<PurchaseDetail> purchaseDetailList) {
        this.ID = ID;
        this.supplier = supplier;
        this.total = total;
        this.date = date;
        this.purchaseDetailList = purchaseDetailList;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "ID='" + ID + '\'' +
                ", supplier=" + supplier +
                ", total=" + total +
                ", date=" + date +
                ", purchaseDetailList=" + purchaseDetailList +
                '}';
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<PurchaseDetail> getPurchaseDetailList() {
        return purchaseDetailList;
    }

    public void setPurchaseDetailList(List<PurchaseDetail> purchaseDetailList) {
        this.purchaseDetailList = purchaseDetailList;
    }
}
