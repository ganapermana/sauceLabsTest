import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LoginPage {
    private WebDriver driver;

    // Constructor to initialize driver
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    public void waitSomeSeconds(Integer seconds) {
        try {
            Thread.sleep(seconds * 1000); // Convert seconds to milliseconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void inputUsername(String userName){
        WebElement inputUsernameColumn = driver.findElement(By.xpath("//input[@id='user-name']"));
        inputUsernameColumn.sendKeys(userName);
    }

    public void inputPassword(String password){
        WebElement inputPasswordColumn = driver.findElement(By.xpath("//input[@id='password']"));
        inputPasswordColumn.sendKeys(password);
    }

    public void clickLoginButton(){
        WebElement buttonLogin = driver.findElement(By.xpath("//input[@id='login-button']"));
        buttonLogin.click();
    }

    public boolean errorMessageIsDisplayed(){
        WebElement errorMessage =  driver.findElement(By.xpath("//h3[@data-test='error']"));
        return errorMessage.isDisplayed();
    }

    public String getErrorMessage(){
        WebElement errorMessage =  driver.findElement(By.xpath("//h3[@data-test='error']"));
        return errorMessage.getText();
    }

    public Boolean isErrorMessageOfWrongUsernamePasswordCorrect(String errorMessage){
        return errorMessage.contains("Username and password do not match any user in this service");
    }

    public Boolean isNullErrorMessageCorrect(String errorMessage) {
        if (getErrorMessage().contains("Username is required")) {
            return true;
        } else if (getErrorMessage().contains("Password is required")) {
            return true;
        } else
            return false;
    }

    public void loginBenar(){
        inputUsername("standard_user");
        inputPassword("secret_sauce");
        waitSomeSeconds(1);
        clickLoginButton();
    }

    public void loginIncorrectUsername(){
        inputUsername("qwerty");
        inputPassword("secret_sauce");
        clickLoginButton();
        waitSomeSeconds(2);
        assertTrue(errorMessageIsDisplayed());
        assertEquals(true, isErrorMessageOfWrongUsernamePasswordCorrect(getErrorMessage()));
    }

    public void loginIncorrectPassword(){
        inputUsername("standard_user");
        inputPassword("qwerty");
        clickLoginButton();
        waitSomeSeconds(1);
        assertTrue(errorMessageIsDisplayed());
        assertEquals(true, isErrorMessageOfWrongUsernamePasswordCorrect(getErrorMessage()));
    }

    public void nullLogin(){
        inputUsername("");
        inputPassword("");
        clickLoginButton();
        waitSomeSeconds(1);
        assertEquals(true, isNullErrorMessageCorrect(getErrorMessage()));
    }

    public void nullUsernameLogin(){
        inputUsername("standard_user");
        inputPassword("");
        clickLoginButton();
        waitSomeSeconds(1);
        assertEquals(true, isNullErrorMessageCorrect(getErrorMessage()));
    }

    public void nullPasswordLogin(){
        inputUsername("");
        inputPassword("secret_sauce");
        clickLoginButton();
        waitSomeSeconds(1);
        assertEquals(true, isNullErrorMessageCorrect(getErrorMessage()));
    }
}
