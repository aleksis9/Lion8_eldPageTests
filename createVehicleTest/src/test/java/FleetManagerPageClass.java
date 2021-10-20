import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class FleetManagerPageClass {

    WebDriver driver;

    private String addFleetManagerButtonXpath = "//span[text()='Add Fleet Manager']/ancestor::button";

    public FleetManagerPageClass(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public AddFleetManagerPageClass addFleetManager() throws Exception {
        WebElement addFleetManagerButton = Common.getElement(addFleetManagerButtonXpath,"");
        addFleetManagerButton.click();
        Thread.sleep(1000);
        return new AddFleetManagerPageClass(driver);
    }



}
