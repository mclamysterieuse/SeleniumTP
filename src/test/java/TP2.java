import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class TP2 {
    WebDriver driver;
    @Test
    public void testAmazon1(){
        String Keywords = "PlayStation 5";
        String  realResults = "PlayStation 5";
        String expectedTitle = "Sony, PlayStation 5 Édition Standard, PS5 avec 1 Manette Sans Fil DualSense, Couleur : Blanche";
        driver.get("https://amazon.fr");
        WebElement cookieButton = driver.findElement(By.xpath("//input[@id='sp-cc-accept']"));
        cookieButton.click();
        WebElement researchButton = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
        researchButton.sendKeys("PlayStation 5");
        WebElement researchOnglet = driver.findElement(By.xpath("//input[@id='nav-search-submit-button']"));
        researchOnglet.click();
        WebElement searchResultTitle = driver.findElement(By.cssSelector("span.a-color-state.a-text-bold"));
        String realResult = searchResultTitle.getText();
        Assert.assertEquals(realResult,"\"" + Keywords + "\"");
        // cas avec articles sponsorisés
        List<WebElement> searchResultList = driver.findElements(By.cssSelector("[data-component-type='s-search-result']:not(.AdHolder)"));
        String firstTitle = searchResultList.get(0).getText();
        Assert.assertTrue(firstTitle.contains(expectedTitle));
    }
        // cas sans articles sponsorisés
        //WebElement researchTitle = driver.findElement(By.cssSelector("div.a-section.a-spacing-none.a-spacing-top-small.s-title-instructions-style > h2 > a"));
        // String realresearchTitle = researchTitle.getText();
        //Assert.assertEquals(realresearchTitle,  expectedTitle);

    @BeforeMethod
    public void setup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    @AfterMethod
    public void teardown(){
        driver.quit();
    }
}
