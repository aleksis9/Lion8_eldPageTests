import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class AddVehiclePageClass {

    private WebDriver driver;

    private String vehicleIdXpath = "//input[@name=\"vehicleId\"]";
    private String vehicleVINXpath = "//input[@formcontrolname=\"vin\"]";
    private String yearFieldXpath = "//mat-select[@formcontrolname=\"year\"]";
    private String yearChoiceXpath = "//span[text()='%VALUE']/ancestor::mat-option";
    private String makeXpath = "//input[@formcontrolname='make']";
    private String modelXpath = "//input[@formcontrolname='model']";
    private String colorXpath = "//input[@formcontrolname='color']";
    private String fuelTypeFieldXpath = "//mat-select[@formcontrolname=\"fuelType\"]";
    private String fuelTypeChoiceXpath = "//span[normalize-space(text())='%VALUE']";
    private String licenceStateFieldXpath = "//mat-select[@formcontrolname='state']";
    private String licenceStateChoiceXpath = "//span[normalize-space(text())='%VALUE']/ancestor::mat-option";
    private String licencePlateNumXpath = "//input[@formcontrolname='licenseNum']";
    private String companyOwnedSliderXpath = "//mat-slide-toggle[@formcontrolname='companyOwned']";
    private String eldSNFieldXpath = "//mat-select[@formcontrolname='eldId']";
    private String eldSNChoiceXpath = "//span[text()='No ELD device']/ancestor::mat-option";
    private String gpsSNFieldXpath = "//mat-select[@formcontrolname='gpsId']";
    private String gpsSNChoiceXpath = "//span[text()='No GPS device']/ancestor::mat-option";
    private String fleetRequestedDistanceCustomValueChoiceXpath = "//span[normalize-space(text())='Custom value']/ancestor::mat-radio-button";
    private String fleetRequestedDistanceCustomValueInputXpath = "//input[@formcontrolname='fleetRequestedDistance']";

    private String confirmButtonXpath = "//span[contains(text(),'Confirm')]/ancestor::button";


    public AddVehiclePageClass(WebDriver chromeDriver) throws Exception {
        this.driver = chromeDriver;
        PageFactory.initElements(driver, this);
    }

    public VehiclePageClass addNewVehicle(String id, String vin, String year, String make, String model, String color, String fuelType,
                                            String licenceIssuingState, String licencePlateNumber, Boolean companyOwned, String eldSN, String gpsSN,
                                            Boolean fleetDefaultRequestedDistance, String fleetCustomRequestedDistanceOPTIONAL) throws Exception {

        return inputId(id).inputVIN(vin).inputYear(year).inputMake(make).inputModel(model).
                inputColor(color).inputFuelType(fuelType).inputLicenceIssuingState(licenceIssuingState).
                inputLicencePlateNumber(licencePlateNumber).inputCompanyOwned(companyOwned).inputELDSN(eldSN).
                inputGPSSN(gpsSN).inputFleetRequestedDistance(fleetDefaultRequestedDistance,fleetCustomRequestedDistanceOPTIONAL).
                clickConfirmButton();
    }

    private AddVehiclePageClass inputId(String vehicleId) throws Exception {
        Common.editInputField(vehicleIdXpath,vehicleId);
        return this;
    }

    private AddVehiclePageClass inputVIN(String vehicleVIN) throws Exception {
        Common.editInputField(vehicleVINXpath, vehicleVIN);
        return this;
    }

    private AddVehiclePageClass inputYear(String vehicleYear) throws Exception {
        Common.editDropdownField(yearFieldXpath, yearChoiceXpath,vehicleYear);
        return this;
    }

    private AddVehiclePageClass inputMake(String vehicleMake) throws Exception {
        Common.editInputField(makeXpath,vehicleMake);
        return this;
    }

    private AddVehiclePageClass inputModel(String vehicleModel) throws Exception {
        Common.editInputField(modelXpath, vehicleModel);
        return this;
    }

    private AddVehiclePageClass inputColor(String vehicleColor) throws Exception {
        Common.editInputField(colorXpath, vehicleColor);
        return this;
    }

    private AddVehiclePageClass inputFuelType(String vehicleFuelType) throws Exception {
        Common.editDropdownField(fuelTypeFieldXpath, fuelTypeChoiceXpath, vehicleFuelType);
        return this;
    }

    private AddVehiclePageClass inputLicenceIssuingState(String vehicleLicenceIssuingState) throws Exception {
        Common.editDropdownField(licenceStateFieldXpath,licenceStateChoiceXpath,vehicleLicenceIssuingState);
        return this;
    }

    private AddVehiclePageClass inputLicencePlateNumber(String vehicleLicencePlateNumber) throws Exception {
        Common.editInputField(licencePlateNumXpath,vehicleLicencePlateNumber);
        return this;
    }

    private AddVehiclePageClass inputELDSN(String eldSN) throws Exception {
        Common.editDropdownField(eldSNFieldXpath,eldSNChoiceXpath,eldSN);
        return this;
    }

    private AddVehiclePageClass inputGPSSN(String gpsSN) throws Exception {
        Common.editDropdownField(gpsSNFieldXpath,gpsSNChoiceXpath,gpsSN);
        return this;
    }

    private AddVehiclePageClass inputCompanyOwned(Boolean companyOwned) throws Exception {
        if(!companyOwned)
            Common.editSliderField(companyOwnedSliderXpath);
        return this;
    }

    private AddVehiclePageClass inputFleetRequestedDistance(Boolean defaultDistance, String fleetRequestedDistance) throws Exception {
        if(!defaultDistance) {
            Common.editSliderField(fleetRequestedDistanceCustomValueChoiceXpath);
            if (fleetRequestedDistance != null)
                Common.editInputField(fleetRequestedDistanceCustomValueInputXpath, fleetRequestedDistance);
        }
        return this;
    }

    private VehiclePageClass clickConfirmButton() throws Exception {
        WebElement webElement = Common.getElement(confirmButtonXpath, "");
        webElement.click();
        Thread.sleep(1000);
        return new VehiclePageClass(driver);
    }

}
