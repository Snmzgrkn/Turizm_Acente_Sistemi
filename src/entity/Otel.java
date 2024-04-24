package entity;

import java.util.List;

public class Otel {
    private int id;
    private String name;
    private String address;
    private String mail;
    private String phoneno;
    private int star;
    private Pension pensiontype;
    private List<Feature> features;
    private Room roomtype;

    public Otel() {

    }

    public Room getRoomtype() {
        return roomtype;
    }

    public void setRoomtype(Room roomtype) {
        this.roomtype = roomtype;
    }

    public Otel(int id, String name, String address, String mail, String phoneno, int star, Pension pensiontype, List<Feature> features, Room roomtype) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.mail = mail;
        this.phoneno = phoneno;
        this.star = star;
        this.pensiontype = pensiontype;
        this.features = features;
        this.roomtype=roomtype;
    }
    public Otel(String name, String address, String mail, String phoneno, int star, Pension pensiontype, List<Feature> features, Room roomtype) {

        this.name = name;
        this.address = address;
        this.mail = mail;
        this.phoneno = phoneno;
        this.star = star;
        this.pensiontype = pensiontype;
        this.features = features;
        this.roomtype=roomtype;
    }


    public Pension getPensiontype() {
        return pensiontype;
    }

    public void setPensiontype(Pension pensiontype) {
        this.pensiontype = pensiontype;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
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

    @Override
    public String toString() {
        return  name + '\'' + address + star + pensiontype + features + roomtype;
    }
}
