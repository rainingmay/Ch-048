package pages.allUsers;

import pages.PageInitializer;
import pages.headers.BaseHeader;


/**
 * Created by Yana on 06.04.2017.
 */
public class DoctorInfoPage implements PageInitializer {
    BaseHeader baseHeader;
    public DoctorInfoPage() {
        baseHeader = new BaseHeader();
        pageInitialization();
    }
}
