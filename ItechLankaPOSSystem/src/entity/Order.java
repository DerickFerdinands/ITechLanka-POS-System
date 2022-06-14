package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity (name = "`Order`")
public class Order {
    @Id
    private String Id;
    @ManyToOne
    private Customer customer;
    private LocalDate date;
    private double total;
    @OneToMany(mappedBy = "Order")
    private List<OrderDetail> orderDetailList = new ArrayList<>();

    public Order(String id, Customer customer, LocalDate date, double total, List<OrderDetail> orderDetailList) {
        Id = id;
        this.customer = customer;
        this.date = date;
        this.total = total;
        this.orderDetailList = orderDetailList;
    }

    public Order() {
    }

    public Order(String id, Customer customer, LocalDate date, double total) {
        Id = id;
        this.customer = customer;
        this.date = date;
        this.total = total;
    }

    public List<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

    @Override
    public String toString() {
        return "Order{" +
                "Id='" + Id + '\'' +
                ", customer=" + customer +
                ", date=" + date +
                ", total=" + total +
                '}';
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
