package entity;

public class Room {
    private int id;
    private String name;
    private int price;
    private int stock;

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Room(String name, int price, int stock) {
        this.name = name;
        this.price = price;
        this.stock=stock;
    }
    public Room(int id, String name, int price,int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock=stock;
    }

    public Room() {

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
