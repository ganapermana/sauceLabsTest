import dev.failsafe.internal.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Main  {
    WebDriver driver;


    public void launchBrowser() {
        System.setProperty("webdriver.chrome.driver", "/Users/ganapermana/Downloads/chromedriver-mac-arm64/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
    }

    public static void main(String[] args){
        Main obj = new Main();
        obj.launchBrowser();

        Login loginPage = new Login(obj.driver);
        Transactions transactionsPage = new Transactions(obj.driver);


        // select one of these testcase to be run
        loginPage.loginBenar();   //login using correct credentials testcase
//        loginPage.loginIncorrectUsername();   //login using incorrect username testcase
//        loginPage.loginIncorrectPassword();   //login using incorrect password testcase
//        loginPage.nullLogin();                //login when username and password was null
//        loginPage.nullUsernameLogin();        //login when username was null
//        loginPage.nullPasswordLogin();        //login when password was null
//        transactionsPage.completeTransaction();                                   //do complete transaction in positive way
        transactionsPage.completeTransactionWithMultipleProducts();                 //do complete transaction with multiple items
//        transactionsPage.transactionWithNullFirstNameOnInputInformation();        //input null first name on input information
//        transactionsPage.transactionWithNullLaststNameOnInputInformation();       //input null last name on input information
//        transactionsPage.transactionWithNullPostalCodeeOnInputInformation();      //input null postal code on input information
        obj.driver.quit();
    }

}
