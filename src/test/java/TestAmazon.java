import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;


public class TestAmazon {

    WebDriver driver;
    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    @AfterMethod
    public void teardown() {
        driver.quit();
    }
    @Test

    public void testAmazon2() {
        String Visible = "Passer la commande";
        String expectedTitle = "iPhone 13";

        //Ouvrir page de amazon.fr
        driver.get("https://www.amazon.fr");

        //- fermer les cookies
        driver.findElement(By.cssSelector("#sp-cc-accept")).click();

        // - saisir mot cle dans la barre de recherche "iphone 13"
        driver.findElement(By.name("field-keywords")).sendKeys("iphone 13");
        driver.findElement(By.cssSelector("#nav-search-submit-button")).click();

        //- click loupe and  premier article and   //- assert que mot cle "iphone 13" est present dans le titre du premier element
        WebElement searchResultList = driver.findElement(By.cssSelector("div.a-section.a-spacing-none.a-spacing-top-small.s-title-instructions-style > h2 > a > span"));
        String firstTitle = searchResultList.getText();
        Assert.assertTrue(firstTitle.contains(expectedTitle));
        searchResultList.click();

        //- click bouton ajouter au panier
        driver.findElement(By.cssSelector("#add-to-cart-button")).click();

        // - click non merci
        WebElement Nothanks = driver.findElement(By.cssSelector("#attachSiNoCoverage"));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Nothanks.click();

        // - click panier
        WebElement Card = driver.findElement(By.cssSelector("#attach-sidesheet-view-cart-button"));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Card.click();

        //assert passer la commande visible
        WebElement searchRealVisible = driver.findElement(By.cssSelector("#sc-buy-box-ptc-button"));
        Assert.assertTrue(searchRealVisible.isDisplayed(), "Payment button visible");
        String realResult = searchRealVisible.getText();
        Assert.assertEquals(realResult, Visible);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
       //- (optionnel) assert quantité
        WebElement searchRealQuantity = driver.findElement(By.cssSelector("#nav-cart-count"));
        String realResultQuantity = searchRealQuantity.getText();
        Assert.assertEquals(realResultQuantity, "1", "The quantity of phone on the card is equal");
    }

}