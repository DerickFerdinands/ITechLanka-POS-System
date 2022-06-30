package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Data
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


}
