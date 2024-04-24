package entity;

public class Room {
    private int id;
    private String name;
    private int price;

    public Room(String name, int price) {
        this.name = name;
        this.price = price;
    }
    public Room(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
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
