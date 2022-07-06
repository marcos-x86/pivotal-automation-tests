package pivotal.automation.hooks;

import io.cucumber.java.After;
import pivotal.automation.RequestManager;

public class RequestHooks {

    SharedData sharedData;

    public RequestHooks(SharedData sharedData) {
        this.sharedData = sharedData;
    }

    @After(value = "@deleteProjectPostCondition")
    public void deleteProject() {
        String projectID = sharedData.getId();
        if (projectID != null) {
            String endpoint = "https://www.pivotaltracker.com/services/v5/projects/" + projectID;
            RequestManager.sendDeleteRequest(endpoint);
        }
    }
}
