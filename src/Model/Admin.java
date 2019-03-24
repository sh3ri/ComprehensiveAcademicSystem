package Model;

public class Admin extends Person {
    private static Admin instance = new Admin();
    //TODO: CONSTRUCTOR

    public static Admin getInstance() {
        return instance;
    }
}
