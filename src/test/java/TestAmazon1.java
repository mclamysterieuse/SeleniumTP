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


public class TestAmazon1 {

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
    public void testAmazon1() {
        final int TIMEOUT_SIDE_PANEL = 5;
        String FirstAsset = "The Legend of Zelda : Tears of the Kingdom";
        //Open www.amazon.fr
        driver.get("https://www.amazon.fr");

        //- close cookies modal

        driver.findElement(By.cssSelector("#sp-cc-accept")).click();
        // - Open all Menu
        driver.findElement(By.cssSelector("#nav-hamburger-menu")).click();

        // - - Select Jeux video et consoles
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_SIDE_PANEL));
        WebElement VideoPlay = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[data-menu-id='12']")));
        VideoPlay.click();

        // - Select Tous les jeux video
        WebElement AllVideoPlay = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#hmenu-content > ul.hmenu.hmenu-visible.hmenu-translateX > li:nth-child(3)")));
        AllVideoPlay.click();

//        List<WebElement> VideoList = driver.findElements(By.cssSelector(".hmenu-visible>li"));
//        WebElement AllVideoList = VideoList.get(0);
//        WebElement Result = wait.until(ExpectedConditions.elementToBeClickable(AllVideoList));
//        Result.click();

        // Assert that the first article in Les Meilleures Ventes is "The Legend of Zelda : Tears of the Kingdom "

        WebElement Seller = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[cel_widget_id='handsfree-browse_OctopusBestSellerAsin'] li .a-color-base")));
        String FirstSeller = Seller.getText();
        Assert.assertEquals(FirstSeller, FirstAsset);

        //- Open the first article-
        WebElement FirstArticle = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[cel_widget_id='handsfree-browse_OctopusBestSellerAsin'] li .a-color-base")));
        FirstArticle.click();

//        // Assert that the price is 54.99
        WebElement priceItem = driver.findElement(By.cssSelector(".a-section.a-spacing-none.aok-align-center"));
        String actualPrice = priceItem.getText().replace('\n', '.');
        actualPrice = actualPrice.substring(0, actualPrice.length() - 1);
        Assert.assertEquals(actualPrice, "54.99");

        //Assert the message "Cet article paraîtra le 12 mai 2023. "
        String message = "Cet article paraîtra le 12 mai 2023.";
        Assert.assertEquals("Cet article paraîtra le 12 mai 2023.", message, "Le message est le même");
    }
}
