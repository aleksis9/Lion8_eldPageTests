import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.Locale;

public class AddFleetManagerPageClass {

    WebDriver driver;

    private String firstNameXpath = "//input[@formcontrolname='firstName']";
    private String lastNameXpath = "//input[@formcontrolname='lastName']";
    private String emailXpath = "//input[@formcontrolname='email']";
    private String phoneNumXpath = "//input[@formcontrolname='phoneNum']";
    private String roleFieldXpath = "//mat-select[@formcontrolname='role']";
    private String roleChoiceXpath = "//span[normalize-space(text())='%VALUE']/ancestor::mat-option";
    //check aria-checked = true/false before clicking
    private String userCanEditLogsSliderXpath = "//mat-slide-toggle[@formcontrolname='allowedLogsEditing']";
    private String userCanResolveDefectsSliderXpath = "//mat-slide-toggle[@formcontrolname='allowedResolvingDefects']";
    private String userCanManageLoadsSliderXpath = "//mat-slide-toggle[@formcontrolname='allowedLoadManagement']";
    private String groupsFieldXpath = "//mat-select[@formcontrolname='groups']";
    private String groupsChoiceXpath = "//span[normalize-space(text())='%VALUE']/ancestor::mat-option";

    private String confirmButtonXpath = "//span[contains(text(),'Confirm')]/ancestor::button";

    public AddFleetManagerPageClass(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public FleetManagerPageClass inputFleetManagerData(String firstName, String lastName, String email, String phoneNum,
                                                       String role, Boolean canEditLogs, Boolean canResolveDefects,
                                                       Boolean canManageLoads, String groups) throws Exception {

        return inputFirstName(firstName).inputLastName(lastName).inputEmail(email).inputPhoneNum(phoneNum).inputUserRole(role).
                inputCanEditLogs(canEditLogs).inputCanResolveDefects(canResolveDefects).inputCanManageLoads(canManageLoads).
                inputGroup(role,groups).clickConfirmButton();
    }

    private AddFleetManagerPageClass inputFirstName(String firstname) throws Exception {
        Common.editInputField(firstNameXpath,firstname);
        Thread.sleep(500);
        return this;
    }

    private AddFleetManagerPageClass inputLastName(String lastname) throws Exception {
        Common.editInputField(lastNameXpath,lastname);
        Thread.sleep(500);
        return this;
    }

    private AddFleetManagerPageClass inputEmail(String email) throws Exception {
        Common.editInputField(emailXpath,email);
        Thread.sleep(500);
        return this;
    }

    private AddFleetManagerPageClass inputPhoneNum(String phoneNum) throws Exception {
        Common.editInputField(phoneNumXpath,phoneNum);
        Thread.sleep(500);
        return this;
    }

    private AddFleetManagerPageClass inputUserRole(String userRole) throws Exception{
        Common.editDropdownField(roleFieldXpath,roleChoiceXpath,userRole);
        Thread.sleep(500);
        return this;
    }

    private AddFleetManagerPageClass inputCanEditLogs(Boolean canEditLogs){
        WebElement webElement = Common.getElement(userCanEditLogsSliderXpath,"");
        WebElement childInput = Common.getElement(userCanEditLogsSliderXpath + "//input","");
        String currentElementValue = childInput.getAttribute("aria-checked").toLowerCase(Locale.ROOT);
        String wantedElementValue = canEditLogs.toString().toLowerCase(Locale.ROOT);
        if(currentElementValue != wantedElementValue)
            webElement.click();
        return this;
    }

    private AddFleetManagerPageClass inputCanResolveDefects(Boolean canResolveDefects){
        WebElement webElement = Common.getElement(userCanResolveDefectsSliderXpath,"");
        WebElement childInput = Common.getElement(userCanEditLogsSliderXpath + "//input","");
        String currentElementValue = childInput.getAttribute("aria-checked").toLowerCase(Locale.ROOT);
        String wantedElementValue = canResolveDefects.toString().toLowerCase(Locale.ROOT);
        if(currentElementValue != wantedElementValue)
            webElement.click();
        return this;
    }

    private AddFleetManagerPageClass inputCanManageLoads(Boolean canManageLoads){
        WebElement webElement = Common.getElement(userCanManageLoadsSliderXpath,"");
        WebElement childInput = Common.getElement(userCanEditLogsSliderXpath + "//input","");
        String currentElementValue = childInput.getAttribute("aria-checked").toLowerCase(Locale.ROOT);
        String wantedElementValue = canManageLoads.toString().toLowerCase(Locale.ROOT);
        if(currentElementValue != wantedElementValue)
            webElement.click();
        return this;
    }

    private AddFleetManagerPageClass inputGroup(String role, String group) throws Exception{
        if(role == "Admin")
            return this;
        Common.editDropdownField(groupsFieldXpath,groupsChoiceXpath,group);
        Thread.sleep(500);
        return this;
    }


    private FleetManagerPageClass clickConfirmButton() throws Exception {
        WebElement webElement = Common.getElement(confirmButtonXpath, "");
        webElement.click();
        Thread.sleep(1000);
        return new FleetManagerPageClass(driver);
    }

}
