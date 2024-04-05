import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class AutoTestAudimas {
    WebDriver _globalDriver;
    String _email;
    String _user;

    public static String generateRandomEmail() {
        String[] domains = {"gmail.com", "yahoo.com", "hotmail.com", "outlook.com", "example.com"};
        String[] characters = {"abcdefghijklmnopqrstuvwxyz", "0123456789"};

        Random random = new Random();

        StringBuilder email = new StringBuilder();

        // Generate username part
        int usernameLength = random.nextInt(10) + 5; // Random length between 5 to 14 characters
        for (int i = 0; i < usernameLength; i++) {
            String characterSet = characters[random.nextInt(2)]; // Selecting either alphabets or numbers
            char randomChar = characterSet.charAt(random.nextInt(characterSet.length()));
            email.append(randomChar);
        }

        // Adding '@' symbol
        email.append("@");

        // Selecting random domain
        String randomDomain = domains[random.nextInt(domains.length)];
        email.append(randomDomain);

        return email.toString();
    }
    public String generateUsername() {
        String[] characters = {"abcdefghijklmnopqrstuvwxyz", "0123456789"};

        Random random = new Random();

        StringBuilder username = new StringBuilder();

        // Generate username part
        int usernameLength = random.nextInt(10) + 5; // Random length between 5 to 14 characters
        for (int i = 0; i < usernameLength; i++) {
            String characterSet = characters[random.nextInt(2)]; // Selecting either alphabets or numbers
            char randomChar = characterSet.charAt(random.nextInt(characterSet.length()));
            username.append(randomChar);

        }
        return username.toString();
    }
    @BeforeClass
    public void SetupUserDetails() {
        _email = generateRandomEmail();
        _user = generateUsername();
    }
    @BeforeTest
    public void SetupWebDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        _globalDriver = new ChromeDriver(options);
        _globalDriver.get("https://www.audimas.lt/");
        _globalDriver.manage().window().maximize();
    }
    @Test //Naujienlaisio uzsisakymas su atsitiktinai sugeneruotais duomenimis
    public void TS1 (){

        _globalDriver.findElement(By.id("CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll")).click();//Atsidaryti narsykle ir sutikti su slapukais
        try {
            TimeUnit.SECONDS.sleep(15);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Alert alert = _globalDriver.switchTo().alert();
        alert.dismiss();

        //Uzsisakyti naujienlaiskius su nuolaida pirmam apsipirkimui
        _globalDriver.findElement(By.xpath("/html/body/div[7]/div/div/div/div/div/div[2]/div/form/div[1]/div[1]/input")).sendKeys(_user);//Irasyti random varda
        _globalDriver.findElement(By.xpath("/html/body/div[7]/div/div/div/div/div/div[2]/div/form/div[1]/div[2]/input")).sendKeys(_email);//Irasyti random el.pasta
        _globalDriver.findElement(By.xpath("/html/body/div[7]/div/div/div/div/div/div[2]/div/form/div[1]/div[4]/div/label/span")).click();//Sutinkame gauti naujienlaisius
        _globalDriver.findElement(By.xpath("/html/body/div[7]/div/div/div/div/div/div[2]/div/form/button")).click();//Spaudziame prenumeruoti
//        _globalDriver.findElement(By.id("Password2")).sendKeys("FiktyviAnketa123");
//
//        WebElement registrationButton2 = _globalDriver.findElement(By.xpath("/html/body/div[1]/form/fieldset/table/tbody/tr[11]/td[2]/input"));
//        registrationButton2.click();
//        WebElement resultText = _globalDriver.findElement(By.xpath("/html/body/div[1]/div[2]/h1/b"));
//        Assert.assertEquals(resultText.getText(),"Jūs sėkmingai prisiregistravote!");
//        _globalDriver.close();
    }



}
