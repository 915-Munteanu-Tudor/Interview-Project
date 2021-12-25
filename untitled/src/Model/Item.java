package Model;

public class Item {
    public static Integer lastID = 0;
    private Integer id;
    public synchronized void setId() {
        lastID++;
        id = lastID;
    }
    private final String name;

    @Override
    public String toString(){
        return "Item" + getId().toString() + ": " + getName();
    }



    public Item(String name) {
        setId();
        this.name = name;
    }


    public Integer getId() {
        return id;
    }


    public String getName() {
        return name;
    }

}
