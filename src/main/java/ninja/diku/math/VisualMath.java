package ninja.diku.math;

import ninja.diku.main.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class VisualMath {

    /*
    We "hide" the website - Ask the author if you wanna know why
     */
    private final static String baseUrl = Util.rot13(new String(Base64.getDecoder().decode("dWdnY2Y6Ly9qamouamJ5c2Vuem55Y3VuLnBiei92YWNoZy8/dj0=")));
    private final static String className = "_2oVR5";

    private final static WebDriver driver = new FirefoxDriver();
    private WebElement webpage;
    private File image;

    private String query;
    public VisualMath(String query) throws Exception {
        this.query = URLEncoder.encode(query, StandardCharsets.UTF_8);
        this.webpage = loadWebPage(baseUrl + query);
        this.image=renderImage();
    }

    private WebElement loadWebPage(String url) throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.get(url);

        //The _3qz9a element is the last thing to load on this page, so we wait for it to appear
        return wait.until(presenceOfElementLocated(By.className("_3qz9a")));
    }

    private File renderImage() {
        List<WebElement> cookies = driver.findElements(By.className("oaDry"));
        if(cookies.size() > 0) {
            cookies.get(0).findElement(By.tagName("button")).click();
        }
        WebElement element = driver.findElement(By.className(className));
        return element.getScreenshotAs(OutputType.FILE);
    }

    public File getImage() {
        return image;
    }
}
