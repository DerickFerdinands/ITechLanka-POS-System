package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
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
}
