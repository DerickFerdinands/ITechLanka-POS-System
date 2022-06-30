package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Orders {
    @Id
    private String Id;
    @ManyToOne
    private Customer customer;
    private LocalDate date;
    private double total;
    private String paymentStatus;
    private BigDecimal payedAmount;
    @OneToMany(mappedBy = "Order")
    private List<OrderDetail> orderDetailList = new ArrayList<>();

}
