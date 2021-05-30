package model;

public class Staff {
    private int id;
    private String name;
    private String address;
    private Category category;

    public Staff(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public Staff(int id, String name, String address, Category category) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.category = category;
    }

    public Staff() {

    }

    public Staff(String name, String address) {
        this.name = name;
        this.address = address;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
