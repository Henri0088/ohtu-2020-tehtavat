package ohtu;

import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Tester {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        Random rnd = new Random();
        
        driver.get("http://localhost:4567");
        
        sleep(1);
        
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();
        
        // Test 1
        sleep(1);
        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));
        sleep(1);
        element.submit();
        sleep(1);
        
        element = driver.findElement(By.linkText("logout"));
        element.click();
        
        // Test 2
        sleep(1);
        element = driver.findElement(By.linkText("login"));
        element.click();
        sleep(1);
        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep1");
        element = driver.findElement(By.name("login"));
        sleep(1);
        element.submit();
        
        // Test 3
        sleep(1);
        element = driver.findElement(By.linkText("back to home"));
        element.click();
        sleep(1);
        element = driver.findElement(By.linkText("register new user"));
        element.click();
        sleep(1);
        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka"+rnd.nextInt(100000));
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep1");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("akkep1");
        sleep(1);
        element.submit();
        
        //Test 4
        sleep(1);
        element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();
        sleep(1);
        element = driver.findElement(By.linkText("logout"));
        element.click();
        sleep(1);
        
        driver.quit();
    }
    
    private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }
}
