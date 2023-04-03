import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

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

        final int TIMEOUT_SIDE_PANEL = 10;
        String Visible = "Passer la commande";
        String expectedTitle = "iPhone 13";

        //Ouvrir page de amazon.fr
        driver.get("https://www.amazon.fr");

        //- fermer les cookies
        driver.findElement(By.cssSelector("#sp-cc-accept")).click();

        // - saisir mot cle dans la barre de recherche "iphone 13"
        driver.findElement(By.cssSelector("#twotabsearchtextbox")).sendKeys("iphone 13");
        driver.findElement(By.cssSelector("#nav-search-submit-button")).click();

        //- click loupe and  premier article and   //- assert que mot cle "iphone 13" est present dans le titre du premier element
        WebElement searchResultList = driver.findElement(By.cssSelector("div.a-section.a-spacing-none.a-spacing-top-small.s-title-instructions-style > h2 > a > span"));
        String firstTitle = searchResultList.getText();
        Assert.assertTrue(firstTitle.contains(expectedTitle));
        searchResultList.click();

        //- click bouton ajouter au panier
        driver.findElement(By.cssSelector("#add-to-cart-button")).click();

        // - click non merci
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_SIDE_PANEL));
        WebElement Nothanks = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#attachSiNoCoverage")));
        Nothanks.click();

        // - click panier
        WebElement Card = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#attach-sidesheet-view-cart-button")));
        Card.click();

        //assert passer la commande visible
        WebElement searchRealVisible = driver.findElement(By.cssSelector("#sc-buy-box-ptc-button"));
        Assert.assertTrue(searchRealVisible.isDisplayed(), "Payment button visible");
        String realResult = searchRealVisible.getText();
        Assert.assertEquals(realResult, Visible);

       //- (optionnel) assert quantité
        WebElement searchRealQuantity = driver.findElement(By.cssSelector("#nav-cart-count"));
        String realResultQuantity = searchRealQuantity.getText();
        Assert.assertEquals(realResultQuantity, "1", "The quantity of phone on the card is equal");
    }
}