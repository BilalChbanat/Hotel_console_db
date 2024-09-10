package classes;

public class Customer {
    private int id;
    private String cin;
    private String name;

    public Customer(int id, String cin, String name) {
        this.id = id;
        this.cin = cin;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "\nCustomer:" +
                ", cin:'" + cin + '\'' +
                ", name:'" + name + '\'';
    }
}
