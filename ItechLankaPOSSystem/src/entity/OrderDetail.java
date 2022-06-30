package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class OrderDetail {
    @Id
    private String id;
    @ManyToOne
    private Orders Orders;
    @ManyToOne
    private Item item;
    private int qty;
    private BigDecimal buyingPrice;
    private BigDecimal sellingPrice;
}
