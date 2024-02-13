import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import static org.junit.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.containsString;

public class Transactions {

    private WebDriver driver;
    Login loginUser = new Login(driver); //to call wait function
    public Transactions (WebDriver driver) {
        this.driver = driver;
    }

    public void clickCartIcon(){
        WebElement iconCartCorner = driver.findElement(By.xpath("//div[@id='shopping_cart_container']"));
        iconCartCorner.click();
    }
    public String getShoppingCartBadgeText(){
        WebElement shoppingCartBadge = driver.findElement(By.xpath("//span[@class='shopping_cart_badge']"));
        return shoppingCartBadge.getText();
    }
    public void selectBackPackProduct(){
        WebElement buttonBackpackAddToCart = driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']"));
        buttonBackpackAddToCart.click();
    }

    public void selectTshirtProduct(){
        WebElement buttonTshirtAddToCart = driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-bolt-t-shirt']"));
        buttonTshirtAddToCart.click();
    }
    public void clickCheckoutButton(){
        WebElement buttonCheckout = driver.findElement(By.xpath("//button[@id='checkout']"));
        buttonCheckout.click();
    }

    public void inputYourInformation(String firstName, String lastName, String zipCode){
        WebElement inputFirstName = driver.findElement(By.xpath("//input[@id='first-name']"));
        WebElement inputLastName = driver.findElement(By.xpath("//input[@id='last-name']"));
        WebElement inputZipCode = driver.findElement(By.xpath("//input[@id='postal-code']"));
        inputFirstName.sendKeys(firstName);
        inputLastName.sendKeys(lastName);
        inputZipCode.sendKeys(zipCode);
    }

    public Boolean isErrorMessageDisplayed(){
        WebElement errorMessage = driver.findElement(By.xpath("//div[@class='error-message-container error']"));
        if (errorMessage.isDisplayed()){
            return true;
        } else {
            return false;
        }
    }

    public void clickContinueButton(){
        WebElement buttonContinue = driver.findElement(By.xpath("//input[@id='continue']"));
        buttonContinue.click();
    }

    public String getBackpackProductNameOnPreview(){
        WebElement labelProductNameBackpack = driver.findElement(By.xpath("//a[@id='item_4_title_link']//div[@class='inventory_item_name']"));
        return labelProductNameBackpack.getText();
    }

    public String getTshirtProductNameOnPreview(){
        WebElement labelProductNameTshirt = driver.findElement(By.xpath("//a[@id='item_1_title_link']//div[@class='inventory_item_name']"));
        return labelProductNameTshirt.getText();
    }

    public void clickButtonFinishCheckout(){
        WebElement buttonFinishCheckout = driver.findElement(By.xpath("//button[@id='finish']"));
        buttonFinishCheckout.click();
    }

    public Boolean isBannerCompleteOrderDisplayed(){
        WebElement bannerCompleteCheckout = driver.findElement(By.xpath("//div[@id='checkout_complete_container']"));
        try {
            return bannerCompleteCheckout.isDisplayed();
        }catch (Exception e){
            return false;
        }
    }

    public void completeTransaction(){
        loginUser.waitSomeSeconds(1);
        selectBackPackProduct();
        assertEquals("1", getShoppingCartBadgeText());
        clickCartIcon();
        clickCheckoutButton();
        inputYourInformation("Buzz", "Lightyear", "14045");
        loginUser.waitSomeSeconds(1);
        clickContinueButton();
        Assert.assertThat(getBackpackProductNameOnPreview(), containsString("Backpack"));
        clickButtonFinishCheckout();
        loginUser.waitSomeSeconds(1);
        assertEquals(true, isBannerCompleteOrderDisplayed());
    }

    public void completeTransactionWithMultipleProducts(){
        loginUser.waitSomeSeconds(1);
        selectBackPackProduct();
        selectTshirtProduct();
        assertEquals("2", getShoppingCartBadgeText());
        clickCartIcon();
        clickCheckoutButton();
        inputYourInformation("Buzz", "Lightyear", "14045");
        loginUser.waitSomeSeconds(1);
        clickContinueButton();
        Assert.assertThat(getBackpackProductNameOnPreview(), containsString("Backpack"));
        Assert.assertThat(getTshirtProductNameOnPreview(), containsString("T-Shirt"));
        clickButtonFinishCheckout();
        loginUser.waitSomeSeconds(1);
        assertEquals(true, isBannerCompleteOrderDisplayed());
    }

    public void transactionWithNullFirstNameOnInputInformation(){
        loginUser.waitSomeSeconds(1);
        selectBackPackProduct();
        assertEquals("1", getShoppingCartBadgeText());
        clickCartIcon();
        clickCheckoutButton();
        inputYourInformation("", "Lightyear", "14045");
        loginUser.waitSomeSeconds(1);
        clickContinueButton();
        assertEquals(true, isErrorMessageDisplayed());
    }

    public void transactionWithNullLaststNameOnInputInformation(){
        loginUser.waitSomeSeconds(1);
        selectBackPackProduct();
        assertEquals("1", getShoppingCartBadgeText());
        clickCartIcon();
        clickCheckoutButton();
        inputYourInformation("Buzz", "", "14045");
        loginUser.waitSomeSeconds(1);
        clickContinueButton();
        assertEquals(true, isErrorMessageDisplayed());
    }

    public void transactionWithNullPostalCodeeOnInputInformation(){
        loginUser.waitSomeSeconds(1);
        selectBackPackProduct();
        assertEquals("1", getShoppingCartBadgeText());
        clickCartIcon();
        clickCheckoutButton();
        inputYourInformation("Buzz", "Lightyear", "");
        loginUser.waitSomeSeconds(1);
        clickContinueButton();
        assertEquals(true, isErrorMessageDisplayed());
    }
}
