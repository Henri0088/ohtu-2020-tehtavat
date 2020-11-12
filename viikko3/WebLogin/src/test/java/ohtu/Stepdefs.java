package ohtu;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Stepdefs {
    WebDriver driver = new ChromeDriver();
    //WebDriver driver = new HtmlUnitDriver();
    String baseUrl = "http://localhost:4567";
    
    @Given("login is selected")
    public void loginIsSelected() {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("login"));       
        element.click();   
    }
    
    @Given("command new user is selected")
    public void newUserIsSelected() {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();
    }
    
    @When("correct username {string} and password {string} are given")
    public void correctUsernameAndPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }
    
    @When("a valid username {string} and password {string} and matching password confirmation are entered")
    public void correctUsernameAndCorrectPasswordAndPassConf(String username, String password) {
        registerWith(username, password);
    }
    
    @When("a username too short {string} and password {string} are given")
    public void tooShortUsername(String username, String password) {
        registerWith(username, password);
    }
    
    @When("correct username {string} and too short password {string} are given")
    public void tooShortPassword(String username, String password) {
        registerWith(username, password);
    }
    
    @Then("user is logged in")
    public void userIsLoggedIn() {
        pageHasContent("Ohtu Application main page");
    }    
 
    @When("correct username {string} and incorrect password {string} are given")
    public void correctUsernameAndIncorrectPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }
    
    @When("incorrect username {string} and password {string} are given")
    public void incorrectUsernameAndPassword(String username, String password) {
        logInWith(username, password);
    }
    
    @When("incorrect username {string} and incorrect password {string} are given")
    public void incorrectUsernameAndIncorrectPassword(String username, String password) {
        logInWith(username, password);
    }
    
    @When("correct username {string} and password {string} and different password {string} are given")
    public void passwordsDoNotMatch(String username, String password, String passwordConf) {
        registerWithTwoPasswords(username, password, passwordConf);
    }
    
    @Then("user is not created and error \"username should have at least 3 characters\" is reported")
    public void userIsNotCreatedAndErrorUsernameMessageGiven() {
        pageHasContent("username should have at least 3 characters");
    }
    
    @Then("user is not created and error \"password should have at least 8 characters\" is reported")
    public void userIsNotCreatedAndPasswordErrorMessageGiven() {
        pageHasContent("password should have at least 8 characters");
    }
    
    @Then("user is not created and error \"password and password confirmation do not match\" is reported")
    public void userIsNotCreatedAndDifferentPasswordsErrorMessageGiven() {
        pageHasContent("passwords need to match");
    }
    
    @Then("user is not logged in and error message is given")
    public void userIsNotLoggedInAndErrorMessageIsGiven() {
        pageHasContent("invalid username or password");
        pageHasContent("Give your credentials to login");
    }
    
    @Then("a new user is created")
    public void accountCreated() {
        pageHasContent("Welcome to Ohtu Application!");
    }
    
    @When("username {string} and password {string} are given")
    public void usernameAndPasswordAreGiven(String username, String password) throws Throwable {
        logInWith(username, password);
    }   
    
    @Then("system will respond {string}")
    public void systemWillRespond(String pageContent) throws Throwable {
        assertTrue(driver.getPageSource().contains(pageContent));
    }
    
    @When("user with username {string} with password {string} is successfully created")
    public void userIsCreatedSuccesfully(String username, String password) {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();
        registerWith(username, password);
        element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();
        element = driver.findElement(By.linkText("logout"));
        element.click();
    }
    
    @When("user with username {string} and password {string} is tried to be created")
    public void userIsTriedToBeCreated(String username, String password) {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();
        registerWith(username, password);
        element = driver.findElement(By.linkText("back to home"));
        element.click();
    }
    
    @After
    public void tearDown(){
        driver.quit();
    }
        
    /* helper methods */
 
    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }
        
    private void logInWith(String username, String password) {
        assertTrue(driver.getPageSource().contains("Give your credentials to login"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();  
    }
    
    private void registerWith(String username, String password) {
        assertTrue(driver.getPageSource().contains("Create username and give password"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(password);
        element.submit();
    }
    
    private void registerWithTwoPasswords(String username, String password, String passwordConf) {
        assertTrue(driver.getPageSource().contains("Create username and give password"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(passwordConf);
        element.submit();
    }
}
