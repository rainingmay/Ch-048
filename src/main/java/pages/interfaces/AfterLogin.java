package pages.interfaces;

import pages.allUsers.BasePage;


/**
 * Created by ytomktc on 07.04.2017.
 */
public interface AfterLogin {
    BasePage loginAsAdmin(String email, String password) throws InterruptedException;
    BasePage loginAsManager(String email, String password) throws InterruptedException;
    BasePage loginAsDoctor(String email, String password);
    BasePage loginAsPatient(String email, String password);
}
