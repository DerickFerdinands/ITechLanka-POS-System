package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Director {
    @Id
    private String Id;
    private String name;
    private String nic;
    private String mobile;
    private String address;
    private double profitMargin;


}
