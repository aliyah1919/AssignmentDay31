package tests;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class BankingProjectTest {
    public static void main(String[] args) {

        String driverPath = "/src/test/java/drivers/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + driverPath);

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        System.out.println("Browser launched");

        driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/");
        System.out.println("Website opened");

        driver.findElement(By.xpath("//button[text()='Customer Login']")).click();
        System.out.println("Clicked Customer Login");

        WebElement nameDropdown = driver.findElement(By.id("userSelect"));
        Select selectName = new Select(nameDropdown);
        selectName.selectByVisibleText("Harry Potter");
        System.out.println("Selected customer name");

        // 4. Click Login
        driver.findElement(By.xpath("//button[text()='Login']")).click();
        System.out.println("Clicked Login");

        // 5. Click Deposit
        driver.findElement(By.xpath("//button[@ng-class='btnClass2']")).click();
        System.out.println("Clicked Deposit");

        // 6. Input deposit amount
        String depositAmount = "500";
        WebElement amountInput = driver.findElement(By.xpath("//input[@ng-model='amount']"));
        amountInput.sendKeys(depositAmount);
        System.out.println("Entered deposit amount: " + depositAmount);

        // 7. Click Deposit Submit
        driver.findElement(By.xpath("//button[text()='Deposit']")).click();
        System.out.println("Clicked Deposit Submit");

        // 8. Assert deposit successful
        String successMessage = driver.findElement(By.xpath("//span[@ng-show='message']")).getText();
        if (successMessage.equals("Deposit Successful")) {
            System.out.println("Deposit success message is correct: " + successMessage);
        } else {
            System.out.println("Deposit message mismatch: " + successMessage);
        }

        // 9. Assert balance equals deposit amount
        String balance = driver.findElement(By.xpath("//strong[2]")).getText();
        if (balance.equals(depositAmount)) {
            System.out.println("Balance is correct: " + balance);
        } else {
            System.out.println("Balance mismatch. Expected: " + depositAmount + " but got: " + balance);
        }

        // Close browser
        driver.quit();
        System.out.println("Browser closed");
    }
}