import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class VehiclePageClass {

    private static WebDriver driver;

    private String addVehicleButtonXpath = "//span[text()='Add Vehicle']/ancestor::button";

    private String confirmButtonXpath = "//span[contains(text(),'Confirm')]/ancestor::button";

    private String searchInputXpath = "//input[@name='search']";
    private String searchEnterXpath = "//div[contains(@class,'eld-bottom')]";
    private String checkVehicleStateXpath = "//span[contains(text(),'%VALUE')]/ancestor::tr//td[contains(@class,'active')]";
    private String moreVehicleOptionsXpath = "//span[contains(text(),'%VALUE')]/ancestor::tr//img[@alt='more actions']/ancestor::button";
    private String deactivateButtonXpath = "//span[text()='Deactivate']/ancestor::button";
    private String activateButtonXpath = "//span[text()='Activate']/ancestor::button";
    private String filterByStatusFieldXpath = "//mat-label[text()='Filter by Status']/ancestor::span//preceding-sibling::mat-select";
    private String filterByStatusChoiceXpath = "//span[normalize-space(text())='%VALUE']/ancestor::mat-option";

    public VehiclePageClass(WebDriver chromeDriver){
        this.driver = chromeDriver;
        PageFactory.initElements(driver, this);
    }

    public AddVehiclePageClass addVehicle() throws Exception {
        WebElement addVehicleButton = Common.getElement(addVehicleButtonXpath,"");
        addVehicleButton.click();
        return new AddVehiclePageClass(driver);
    }

    public VehiclePageClass filterVehicles(String statusValue) throws Exception{
        WebElement webElement = Common.getElement( filterByStatusFieldXpath, "");
        webElement.click();
        Thread.sleep(1000);

        webElement = Common.getElement( filterByStatusChoiceXpath, statusValue);
        webElement.click();
        Thread.sleep(1000);

        return this;
    }

    public VehiclePageClass deactivateVehicleAndCheck(String vehicleId) throws Exception{
       return searchVehicle(vehicleId).deactivateVehicle(vehicleId).checkVehicleStatus(vehicleId,"Deactivated");
    }

    public VehiclePageClass activateVehicleAndCheck(String vehicleId) throws Exception{
        return searchVehicle(vehicleId).activateVehicle(vehicleId).checkVehicleStatus(vehicleId,"Active");
    }

    private VehiclePageClass searchVehicle(String vehicleId) throws Exception {
        Common.editInputField(searchInputXpath, vehicleId);
        Thread.sleep(1000);

        //probaj umesto ovoga send keys enter
        WebElement webElement = Common.getElement(searchEnterXpath, "");
        webElement.click();
        Thread.sleep(1000);

        return this;
    }

    private VehiclePageClass deactivateVehicle(String vehicleId) throws Exception {
        return clickMoreOptons(vehicleId).clickDeactivate().clickConfirmButton();
    }

    private VehiclePageClass checkVehicleStatus(String vehicleId, String vehicleStatus){
        WebElement webElement = Common.getElement(checkVehicleStateXpath, vehicleId);
        Assert.assertEquals(webElement.getText(), vehicleStatus);
        return this;
    }

    public VehiclePageClass activateVehicle(String vehicleId) throws Exception {
        return clickMoreOptons(vehicleId).clickActivate().clickConfirmButton();
    }

    private VehiclePageClass clickConfirmButton() throws Exception {
        WebElement webElement = Common.getElement(confirmButtonXpath, "");
        webElement.click();
        Thread.sleep(1000);
        return this;
    }

    private VehiclePageClass clickMoreOptons(String vehicleId) throws Exception {
        WebElement webElement = Common.getElement(moreVehicleOptionsXpath, vehicleId);
        webElement.click();
        Thread.sleep(1000);
        return this;
    }

    private VehiclePageClass clickDeactivate() throws Exception {
        WebElement webElement = Common.getElement(deactivateButtonXpath, "");
        webElement.click();
        Thread.sleep(1000);
        return this;
    }

    private VehiclePageClass clickActivate() throws Exception {
        WebElement webElement = Common.getElement(activateButtonXpath, "");
        webElement.click();
        Thread.sleep(1000);
        return this;
    }

}
