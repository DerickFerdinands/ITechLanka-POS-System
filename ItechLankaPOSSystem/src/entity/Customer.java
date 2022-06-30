package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Customer {
    @Id
    private String Id;
    private String name;
    private String nic;
    private String mobile;
    private String address;

    @OneToMany(mappedBy = "customer")
    private List<Orders> ordersList = new ArrayList<>();


    public Customer(String id, String name, String nic, String mobile, String address) {
        Id = id;
        this.name = name;
        this.nic = nic;
        this.mobile = mobile;
        this.address = address;
    }
}
