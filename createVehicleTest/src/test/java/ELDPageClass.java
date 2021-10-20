import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class ELDPageClass {

    private static WebDriver driver;
    private String adminXpath = "//img[@alt='admin']/ancestor::a";
    private String vehiclesTabXpath = "//img[@alt='admin']/ancestor::a/following-sibling::div//div[normalize-space(text())='Vehicles']/ancestor::a";
    private String fleetManagersXpath = "//img[@alt='admin']/ancestor::a/following-sibling::div//div[normalize-space(text())='Fleet Managers']/ancestor::a";

    private String profileButtonXpath = "//img[normalize-space(@alt)='profile']/ancestor::button";
    private String logoutButtonXpath = "//span[normalize-space(text())='Logout']/ancestor::button";

    public ELDPageClass(WebDriver chromeDriver){
        this.driver = chromeDriver;
        PageFactory.initElements(driver, this);
    }

    public VehiclePageClass getVehiclePage() throws Exception{
        clickAdminTab();
        Thread.sleep(1000);
        clickVehiclesTab();
        Thread.sleep(1000);
        return new VehiclePageClass(driver);
    }

    public FleetManagerPageClass getFleetManagerPage() throws Exception {
        clickAdminTab();
        Thread.sleep(1000);
        clickFleetManagerTab();
        Thread.sleep(1000);
        return new FleetManagerPageClass(driver);
    }

    public LogInPageClass logout() throws Exception {
        return clickProfileButton().clickLogoutButton();
    }

    private ELDPageClass clickProfileButton() throws Exception {
        WebElement webElement = Common.getElement(profileButtonXpath,"");
        webElement.click();
        Thread.sleep(1000);
        return this;
    }

    private LogInPageClass clickLogoutButton() throws Exception {
        WebElement webElement = Common.getElement(logoutButtonXpath,"");
        webElement.click();
        Thread.sleep(1000);
        return new LogInPageClass(driver);
    }

    private void clickAdminTab(){
        WebElement element = Common.getElement(adminXpath, "");
        element.click();
    }

    private void clickVehiclesTab(){
        WebElement element = Common.getElement(vehiclesTabXpath, "");
        element.click();
    }

    private void clickFleetManagerTab(){
        WebElement element = Common.getElement(fleetManagersXpath, "");
        element.click();
    }



}
