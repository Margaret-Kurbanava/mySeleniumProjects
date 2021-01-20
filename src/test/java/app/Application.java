package app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.AdminPanelLoginPage;
import pages.CountriesPage;
import pages.CustomerListPage;
import pages.RegistrationPage;
import testData.Customer;
import tests.TestBase;


import java.util.Set;

public class Application  {

    private WebDriver driver;




    private RegistrationPage registrationPage;
    private AdminPanelLoginPage adminPanelLoginPage;
    private CustomerListPage customerListPage;
    private CountriesPage countriesPage;

    public Application() {

        driver = new ChromeDriver();
        registrationPage = new RegistrationPage(driver);
        adminPanelLoginPage = new AdminPanelLoginPage(driver);
        customerListPage = new CustomerListPage(driver);
        countriesPage = new CountriesPage(driver);
    }

    public void quit() {
        driver.quit();
    }

    public void registerNewCustomer(Customer customer) {
        registrationPage.open();
        registrationPage.firstnameInput.sendKeys(customer.getFirstname());
        registrationPage.lastnameInput.sendKeys(customer.getLastname());
        registrationPage.address1Input.sendKeys(customer.getAddress());
        registrationPage.postcodeInput.sendKeys(customer.getPostcode());
        registrationPage.cityInput.sendKeys(customer.getCity());
        registrationPage.selectCountry(customer.getCountry());
        registrationPage.selectZone(customer.getZone());
        registrationPage.emailInput.sendKeys(customer.getEmail());
        registrationPage.phoneInput.sendKeys(customer.getPhone());
        registrationPage.passwordInput.sendKeys(customer.getPassword());
        registrationPage.confirmedPasswordInput.sendKeys(customer.getPassword());
        registrationPage.createAccountButton.click();
    }

    public Set<String> getCustomerIds() {
        if (adminPanelLoginPage.open().isOnThisPage()) {
            adminPanelLoginPage.enterUsername("admin").enterPassword("admin").submitLogin();
        }

        return customerListPage.open().getCustomerIds();
    }


    public void loginToAdmin() {
        adminPanelLoginPage.open().enterUsername("admin").enterPassword("admin").submitLogin();
    }




    public void openCountryEditMode() {
        countriesPage.open();
        countriesPage.firstCountry.click();
    }




    public void checkExternalLinks(){

        countriesPage.externalLinks.forEach((link) -> {
            link.click();
        });
}






}