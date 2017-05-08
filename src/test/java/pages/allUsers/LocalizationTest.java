package pages.allUsers;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.admin.AllUsersPage;
import utils.BaseNavigation;
import utils.BaseTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

/**
 * Created by Evgen on 26.04.2017.
 */
public class LocalizationTest extends BaseTest {

    @Test(dataProvider = "language")
    public void localizationTest(String language) {
        AllUsersPage allUsersPage = BaseNavigation.loginAsAdmin(ADMIN_LOGIN, ADMIN_PASSWORD);
        if (language.equals("ua")) {

        }
    }



    private static LinkedList<String> getProperties(String language) {
        Properties properties = new Properties();
        InputStream inputStream;
        String filename;
        if (language.equals("ua")) filename = "ua_localization.properties";
        else filename = "en_localization.properties";
        try {
            inputStream = new FileInputStream("src/test/resources/localization/" + filename);
            properties.load(inputStream);

            LinkedList<String> linkedList = new LinkedList<>();
            linkedList.add(properties.getProperty("home_button_title"));
            linkedList.add(properties.getProperty("actions_button_title"));
            linkedList.add(properties.getProperty("hospital_list_button_title"));
            linkedList.add(properties.getProperty("enabled_users_label_title"));
            linkedList.add(properties.getProperty("first_name_table_title"));

            return linkedList;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @DataProvider
    public Object[][] language() {
        return new Object[][] {
                {"ua"}
        };
    }
}
