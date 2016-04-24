package learn.rest.AppManager;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Dima on 28.02.2016.
 */
public class ApplicationManager {

    private final Properties properties;

    private RestHelper restHelper;

    public ApplicationManager() {
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }



    public RestHelper rest(){
        if(restHelper == null){
            restHelper = new RestHelper(this);
        }
        return restHelper;
    }
}
