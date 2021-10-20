import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class ELDTestClass {

    private static final WebDriver driver = new ChromeDriver();
    private final String id="v9279", vin="1C3CCBBG5CN176774",licencePlateNumber="AL-171-25-5557";

    @BeforeSuite
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", Common.CHROME_DRIVER);
    }

    @Test()
    public void testAddDeactivateActivateVehicle() throws Exception{
        try {
            WebElement webElement;

            driver.get(Common.HOME_PAGE);
            Common.driver = driver;
            LogInPageClass loginPage = new LogInPageClass(driver);
            driver.manage().window().maximize();

            ELDPageClass eldPage = loginPage.logIn("amarkovic@lioneight.com","seve");
            FleetManagerPageClass fleetManagerPage = eldPage.getFleetManagerPage();
            AddFleetManagerPageClass addFleetManagerPage = fleetManagerPage.addFleetManager();

            addFleetManagerPage.inputFleetManagerData("Mike","Jason","amarkovic+101@lioneight.com",
                    "0606666667","Admin",Boolean.TRUE,Boolean.FALSE,Boolean.TRUE,"Unassigned");
            eldPage.logout();
            loginPage.logIn("amarkovic+101@lioneight.com","seve");
            VehiclePageClass vehiclePage = eldPage.getVehiclePage();
            AddVehiclePageClass addVehiclePage = vehiclePage.addVehicle();

            addVehiclePage.addNewVehicle(id,vin,"2021","VW","Some model","Red",
                                                        "Propane","Alabama", licencePlateNumber,
                                                        Boolean.TRUE,"No ELD device","No GPS device",
                                                        Boolean.FALSE,"750");
            vehiclePage.filterVehicles("All").deactivateVehicleAndCheck(id).activateVehicleAndCheck(id);
        }
        catch (Exception e) {
            takeScreenshot(driver,"testAddDeactivateActivateVehicle");
        }

    }

    public void takeScreenshot(WebDriver webDriver, String fileName) throws IOException {
        TakesScreenshot scrShot =((TakesScreenshot)webDriver);
        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
        File DestFile=new File("..\\scrShots\\" + fileName + ".jpg");
        FileUtils.copyFile(SrcFile, DestFile);
    }

    @AfterSuite
    public static void cleanUp(){
        driver.manage().deleteAllCookies();
        driver.close();
    }

}
