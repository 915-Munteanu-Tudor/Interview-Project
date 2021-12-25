package Model;

public class Child{
    public static Integer lastID = 0;
    private Integer id;

    public Child() {setId();}

    public synchronized void setId() {
        lastID++;
        id = lastID;
    }
    private String Name;
    private String date_of_birth;
    private Address address;
    private Letter letter;
    private Behavior bhv;

    public Child(String name, String date_of_birth, Address address, Behavior bhv, Letter letter){
        setId();
        Name = name;
        this.date_of_birth = date_of_birth;
        this.address = address;
        this.bhv = bhv;
        this.letter = letter;
    }


    @Override
    public String toString() {
        return "Child " + getId().toString() + ":" + "\n\t" + "Name: " + getName() + "\n\t"
                + "Birth date: " + getDate_of_birth() + "\n\t" + "Address: " + getAddress().toString() +
                "\n\t" + "Behaviour: " + getBhv().toString() + "\n\t" + "Letter: " + getLetter().toString();
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return Name;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public Address getAddress() {
        return address;
    }

    public Behavior getBhv() {
        return bhv;
    }


    public Letter getLetter() {
        return letter;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setLetter(Letter letter) {
        this.letter = letter;
    }

    public void setBhv(Behavior bhv) {
        this.bhv = bhv;
    }
}
