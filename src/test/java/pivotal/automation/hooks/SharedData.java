package pivotal.automation.hooks;

public class SharedData {

    private String id;

    public void saveId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }
}
