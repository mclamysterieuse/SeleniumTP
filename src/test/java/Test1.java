import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class Test1 {
    WebDriver driver;
   @Test
    public void testGoogle(){
       //Launch the mvn repository page
       driver.get("https://mvnrepository.com");
       driver.manage().window().maximize();
       driver.manage().deleteAllCookies();
       try {
           Thread.sleep(5000);
       } catch (InterruptedException e) {
           Thread.currentThread().interrupt();
       }
   }
   @Test
    public void testAmazon(){
        //Launch the Amazon page
        driver.get("https://amazon.fr");
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    @BeforeMethod
    public void setup(){
        driver = new ChromeDriver();
    }
//    @AfterMethod
//    public void teardown(){
//        driver.quit();
//    }
}