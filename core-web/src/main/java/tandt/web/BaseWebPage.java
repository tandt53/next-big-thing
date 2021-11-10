package tandt.web;

import com.google.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import tandt.web.drivermanager.DriverManager;

import java.util.List;

/**
 * The base class for all pages.
 */
public abstract class BaseWebPage<TPage extends BaseWebPage> {


    public String url;

    protected WebDriver driver;

    @Inject
    public void initElements(DriverManager manager){
        driver = manager.getDriver();
        ElementFactory.initElements(driver, this);
    }

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
