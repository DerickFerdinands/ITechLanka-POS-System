package Model;

public class Director {
    private String id;
    private String name;
    private String nic;
    private String mobile;
    private String address;
    private double profitMargin;

    public Director() {
    }

    public Director(String id, String name, String nic, String mobile, String address, double profitMargin) {
        this.id = id;
        this.name = name;
        this.nic = nic;
        this.mobile = mobile;
        this.address = address;
        this.profitMargin = profitMargin;
    }

    @Override
    public String toString() {
        return "Director{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", nic='" + nic + '\'' +
                ", mobile='" + mobile + '\'' +
                ", address='" + address + '\'' +
                ", profitMargin=" + profitMargin +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getProfitMargin() {
        return profitMargin;
    }

    public void setProfitMargin(double profitMargin) {
        this.profitMargin = profitMargin;
    }
}
