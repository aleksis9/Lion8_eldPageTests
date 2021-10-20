import jdk.jshell.execution.Util;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class LogInPageClass {

    private static WebDriver driver;
    private String loginEmailXpath = "//input[@id='email']";
    private String loginPasswordXpath = "//input[@name='password']";
    private String loginButtonXpath = "//span[text()='LOG IN']/ancestor::button";

    public LogInPageClass(WebDriver chromeDriver){
        this.driver = chromeDriver;
        PageFactory.initElements(driver, this);
    }

    public ELDPageClass logIn(String email, String password) throws Exception {
        return inputEmail(email).inputPassword(password).clickLogInButton();
    }

    private LogInPageClass inputEmail(String userEmail)throws Exception {
        Common.editInputField(loginEmailXpath,userEmail);
        Thread.sleep(1000);
        return this;
    }

    private LogInPageClass inputPassword(String userPassword) throws Exception {
        Common.editInputField(loginPasswordXpath,userPassword);
        Thread.sleep(1000);
        return this;
    }

    private ELDPageClass clickLogInButton() throws Exception {
        WebElement loginButton = Common.getElement(loginButtonXpath,"");
        loginButton.click();
        Thread.sleep(2000);
        return new ELDPageClass(driver);
    }

}
