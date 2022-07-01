package Model;

import entity.Supplier;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class PurchaseDTO {
    private String ID;
    private SupplierDTO supplier;
    private double total;
    private LocalDate date;
}
