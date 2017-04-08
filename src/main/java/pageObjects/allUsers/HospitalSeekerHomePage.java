package pageObjects.allUsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.headers.BaseHeader;

/**
 * Created by Evgen on 05.04.2017.
 */
public class HospitalSeekerHomePage extends PageObject {


    @FindBy(xpath = "//*[@id=\"carouselHacked\"]/div[2]/div[3]/div/h1")
    private WebElement textHeader;

    @FindBy(xpath = "//*[@id=\"carouselHacked\"]/div[2]/div[2]/div/p")
    private WebElement textParagraph;


    @FindBy(css = "left carousel-control")
    private WebElement leftCarouselButton;

    @FindBy(css = "right carousel-control")
    private WebElement rightCarouselButton;

    @FindBy(css = "img[src=\"/HospitalSeeker/img/slide-four.jpg\"]")
    private WebElement image;


    public HospitalSeekerHomePage(WebDriver driver) {
        super(driver, new BaseHeader(driver));
    }
}
