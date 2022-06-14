package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class OrderDetail {
    @Id
    private String id;
    @ManyToOne
    private Order Order;
    @ManyToOne
    private Item item;
    private int qty;

    public OrderDetail() {
    }

    public OrderDetail(String id, entity.Order order, Item item, int qty) {
        this.id = id;
        Order = order;
        this.item = item;
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "id='" + id + '\'' +
                ", Order=" + Order +
                ", item=" + item +
                ", qty=" + qty +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public entity.Order getOrder() {
        return Order;
    }

    public void setOrder(entity.Order order) {
        Order = order;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
