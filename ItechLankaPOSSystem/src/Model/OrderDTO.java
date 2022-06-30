package Model;

import entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDTO {
    private String Id;
    private Customer customer;
    private LocalDate date;
    private double total;
    private String paymentStatus;
    private double payedAmount;
}
