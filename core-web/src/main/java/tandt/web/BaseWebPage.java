package tandt.web;

import com.google.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * The base class for all pages.
 */
public abstract class BaseWebPage<TPage extends BaseWebPage> {

    /**
     * url
     */
    public String url;

    @Inject
    protected WebDriver driver; // DriverManager must be injected and initialized first

//    public void setDriver(WebDriver driver) {
//        this.driver = driver;
//    }

    public WebElement findElement(By by) {
        return driver.findElement(by);
    }

    public List<WebElement> findElements(By by) {
        return driver.findElements(by);
    }

    public TPage open() {
        if (url != null)
            return open(url);
        return null;
    }

    /**
     * open page with a specific url
     */
    @SuppressWarnings("unchecked")
    public TPage open(String url) {
        driver.get(url);
        return (TPage) this;
    }

    /**
     * When overridden in subclasses, releases all resources being used by the page if necessary.
     */
    public TPage close() {
        driver.close();
        driver = null;
        return (TPage) this;
    }


    /**
     * getTitle
     *
     * @return title of page
     */
    public String getPageTitle() {
        return driver.getTitle();
    }

}
