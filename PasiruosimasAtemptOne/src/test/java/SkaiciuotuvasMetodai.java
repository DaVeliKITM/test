import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class SkaiciuotuvasMetodai {

    public static final String URL_PRISIJUNGTI = "http://localhost:8080/prisijungti";
    public static final String URL_REGISTRUOTIS = "http://localhost:8080/registruoti";
    public static final String PRISIJUNGIMO_VARDAS = "Diva";
    public static final String REGISTRAVIMO_VARDAS = "Ditas";
    public static final String SLAPTAZODIS = "di12";
    public static final String NETEISINGAS_SLAPTAZODIS = "diva12";
    public static final By MYGTUKAS_SUKURTI = By.xpath("//*[@id=\"userForm\"]/button");
    public static final By MYGTUKAS_PRISIJUNGTI = By.xpath("/html/body/div[1]/form/div/button");
    public static final By LAUKELIS_SLAPTAZODIS = By.name("password");
    public static final By LAUKELIS_SLAPTAZODZIO_PATVIRTINIMAS = By.id("passwordConfirm");
    public static final By PRISIJUNGIMO_REZULTATAS = By.partialLinkText("Logout,");
    public static final By NEPAVYKUSIO_PRISIJUNGIMO_ZINUTE = By.cssSelector("div.has-error");
    public static final By VARTOTOJAS_EGZISTUOJA_ZINUTE = By.id("username.errors");
    public static WebDriver browser;

    public static void paleistiNarsykle(String url) {
        System.setProperty("webdriver.chrome.driver", "libs/chromedriver_114.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--start-maximized");
        browser = new ChromeDriver(options);
        browser.get(url);
    }

    public static void priregistruotiVartotoja(String vardas, String slaptazodis, By laukelis, By laukelisPatvirtinimui, By mygtukas) {
        ivestiPrisijungimoVarda(vardas);
        ivestiSlaptazodi(slaptazodis, laukelis);
        ivestiSlaptazodi(slaptazodis, laukelisPatvirtinimui);
        paspaustiFormosMygtuka(mygtukas);
    }

    public static void ivestiPrisijungimoVarda(String vardas) {
        WebElement vardoLaukelis = browser.findElement(By.name("username"));
        Assert.assertTrue("Prisijungimo vardo laukelis yra neaktyvus ir/arba nematomas", vardoLaukelis.isEnabled() && vardoLaukelis.isDisplayed());
        vardoLaukelis.sendKeys(vardas);
    }

    public static void ivestiSlaptazodi(String slaptazodis, By laukelis) {
        WebElement vardoLaukelis = browser.findElement(laukelis);
        Assert.assertTrue("Prisijungimo vardo laukelis yra neaktyvus ir/arba nematomas", vardoLaukelis.isEnabled() && vardoLaukelis.isDisplayed());
        vardoLaukelis.sendKeys(slaptazodis);
    }

    public static void paspaustiFormosMygtuka(By mygtukas) {
        WebElement norimasPaspaustiMygtukas = browser.findElement(mygtukas);
        Assert.assertTrue("Norimas paspausti mygtukas yra nematomas", norimasPaspaustiMygtukas.isDisplayed());
        norimasPaspaustiMygtukas.click();
    }

    public static void prijungtiEsamaVartotoja(String vardas, String slaptazodis, By laukelis, By mygtukas) {
        ivestiPrisijungimoVarda(vardas);
        ivestiSlaptazodi(slaptazodis, laukelis);
        paspaustiFormosMygtuka(mygtukas);
    }

    public static void sulauktiElemento(By elementas) {
        new WebDriverWait(browser, Duration.ofSeconds(15)).until(ExpectedConditions.presenceOfElementLocated(elementas));
    }

    public static void atjungtiVartotoja(By mygtukas){
        sulauktiElemento(PRISIJUNGIMO_REZULTATAS);
        paspaustiFormosMygtuka(mygtukas);
    }

    public static String gautiPrisijungusioVartotojoVarda() {
        WebElement prisijungusioVartotojoVardas = browser.findElement(PRISIJUNGIMO_REZULTATAS);
        String prisijungimoRezultatoTekstas = prisijungusioVartotojoVardas.getText();
        return prisijungimoRezultatoTekstas;
    }

    public static String gautiNesekmingoPrisijungimoZinute() {
        sulauktiElemento(NEPAVYKUSIO_PRISIJUNGIMO_ZINUTE);
        WebElement zinutesTekstas = browser.findElement(By.xpath("//input[@type='password']//following-sibling::span"));
        return zinutesTekstas.getText();
    }

    public static String gautiNesekmingosRegistracijosZinute() {
        sulauktiElemento(VARTOTOJAS_EGZISTUOJA_ZINUTE);
        WebElement zinute = browser.findElement(VARTOTOJAS_EGZISTUOJA_ZINUTE);
        String zinutesTekstas = zinute.getText();
        return zinutesTekstas;
    }

    public static void uzdarytiNarsykle() {
        browser.close();
    }
}