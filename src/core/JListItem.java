package core;

public class JListItem {
    private int key;
    private String value;

    public JListItem(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
    public String getDeger(){
        return this.value;
    }

    @Override
    public String toString() {
        return value;
    }
}