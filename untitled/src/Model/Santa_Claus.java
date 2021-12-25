package Model;

public class Santa_Claus {
    private String Name;
    private volatile static Santa_Claus instance;

    private Santa_Claus() {
        Name = "Santa Claus";
    }

    public synchronized static Santa_Claus getInstance() {
        if (instance == null) {
            instance = new Santa_Claus();
        }
        return instance;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
