package classes;

public class Hotel {

    private int id;
    private String name;
    private String address;
    private String phone;

    public Hotel(int id, String name, String address, String phone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;

    }
    public int getId() {
        return id;
    }
    private void setId(int id) {
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
        return "\n Hotel" +
                "id:" + id +
                ", name:'" + name + '\'' +
                ", address:'" + address + '\'' +
                ", phone:'" + phone + '\''
                ;
    }
}
