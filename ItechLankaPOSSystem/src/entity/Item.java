package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Item {
    @Id
    private String code;
    private String name;
    private String category;
    private BigDecimal buyingPrice;
    private BigDecimal sellingPrice;
    private int qty;
    private String details;
    private String imageLocation;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "item", fetch = FetchType.EAGER)
    private List<PurchaseDetail> purchaseDetailList = new ArrayList<>();
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "item")
    private List<OrderDetail> orderDetailList = new ArrayList<>();

    public Item(String code, String name, String category, BigDecimal buyingPrice, BigDecimal sellingPrice, int qty, String details, String imageLocation) {
        this.code = code;
        this.name = name;
        this.category = category;
        this.buyingPrice = buyingPrice;
        this.sellingPrice = sellingPrice;
        this.qty = qty;
        this.details = details;
        this.imageLocation = imageLocation;
    }
}
