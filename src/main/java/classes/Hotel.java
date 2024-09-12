package classes;

public class Hotel {

    private int id;
    private String name;
    private String address;
    private String phone;

    public Hotel(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "\nHotel {" +
                "id: " + id +
                ", name: '" + name + '\'' +
                ", address: '" + address + '\'' +
                ", phone: '" + phone + '\'' +
                '}';
    }
}
