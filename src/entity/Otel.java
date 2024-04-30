package entity;

import core.ComboItem;

import java.util.List;

public class Otel {
    private int id;
    private String name;
    private String address;
    private String mail;
    private String phoneno;
    private int star;
    private Pension pensiontype;
    private Room roomtype;
    private String features;

    public Otel() {

    }

    public Room getRoomtype() {
        return roomtype;
    }

    public void setRoomtype(Room roomtype) {
        this.roomtype = roomtype;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public Otel(int id, String name, String address, String mail, String phoneno, int star, Pension pensiontype, Room roomtype, String features) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.mail = mail;
        this.phoneno = phoneno;
        this.star = star;
        this.pensiontype = pensiontype;
        this.roomtype=roomtype;
        this.features = features;
    }
    public Otel(String name, String address, String mail, String phoneno, int star, Pension pensiontype, Room roomtype, String features) {

        this.name = name;
        this.address = address;
        this.mail = mail;
        this.phoneno = phoneno;
        this.star = star;
        this.pensiontype = pensiontype;
        this.roomtype=roomtype;
        this.features = features;
    }


    public Pension getPensiontype() {
        return pensiontype;
    }

    public void setPensiontype(Pension pensiontype) {
        this.pensiontype = pensiontype;
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public ComboItem getComboItem(){
        return new ComboItem(this.getId(),this.getName() + " - "+this.getPensiontype().getName());
    }
    @Override
    public String toString() {
        return "Otel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", mail='" + mail + '\'' +
                ", phoneno='" + phoneno + '\'' +
                ", star=" + star +
                ", pensiontype=" + pensiontype +
                ", roomtype=" + roomtype +
                ", features='" + features + '\'' +
                '}';
    }
}
