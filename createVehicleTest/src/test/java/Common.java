import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Common {

    public static WebDriver driver;

    public static final String HOME_PAGE = "http://167.71.51.167:8080/#/auth/login";
    public static final String CHROME_DRIVER = "chromedriver";


    public static WebElement getElement(String xpath, String value){
        if(!xpath.contains("%VALUE"))
            return driver.findElement(By.xpath(xpath));

        return driver.findElement(By.xpath(xpath.replace("%VALUE",value)));
    }

    public static void editInputField(String xpath, String fieldValue) throws Exception {
        WebElement webElement = getElement(xpath,"");
        inputText(webElement, fieldValue);
        Thread.sleep(1000);
    }

    public static void editDropdownField(String fieldXpath, String choiceXpath, String fieldValue) throws Exception {
        WebElement webElement = getElement(fieldXpath, "");
        webElement.click();
        Thread.sleep(1000);

        webElement = getElement(choiceXpath, fieldValue);
        webElement.click();
        Thread.sleep(1000);
    }

    public static void editSliderField(String sliderXpath) throws Exception {
        WebElement webElement = getElement(sliderXpath, "");
        webElement.click();
        Thread.sleep(1000);
    }

    private static void inputText(WebElement element, String text){
        element.click();
        element.clear();
        element.sendKeys(text);
    }

}
