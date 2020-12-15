package com.tandt53.automation.web;

import com.tandt53.automation.common.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by thetan.do on 12/28/2016.
 */
public class BaseWebPage<TPage extends BaseWebPage> {

    /**
     *  url
     */
    public String url;

    protected WebDriver driver;

    protected WebDriverWait wait;
    protected final int _TIMEOUT = 30;

    @SuppressWarnings("unchecked")
    public Log PLog = new Log(((TPage) BaseWebPage.this).getClass());

    public BaseWebPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, _TIMEOUT);
        PageFactory.initElements(this, this.driver);
    }


    /**
     * open  page with url is not null
     */
    public TPage open() {
        if (url != null)
            return open(url);
        return null;
    }

    /**
     * open page with a specific url
     */
    @SuppressWarnings("unchecked")
    private TPage open(String url) {
        driver.get(url);
        return (TPage) this;
    }

    /**
     * close page and quit driver
     */
    public void close() {
        if (driver != null)
            driver.quit();
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
