package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    private double payedAmount;
    @OneToMany(mappedBy = "Orders")
    private List<OrderDetail> orderDetailList = new ArrayList<>();

    public Orders(String id, Customer customer, LocalDate date, double total, String paymentStatus, double payedAmount) {
        Id = id;
        this.customer = customer;
        this.date = date;
        this.total = total;
        this.paymentStatus = paymentStatus;
        this.payedAmount = payedAmount;
    }
}
