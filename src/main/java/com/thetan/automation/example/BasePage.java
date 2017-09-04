package com.thetan.automation.example;

import com.thetan.automation.example.utils.Log;
import org.openqa.selenium.WebDriver;

/**
 * Created by thetan.do on 12/28/2016.
 */
public class BasePage<TPage extends BasePage> {

    /**
     * default url
     */
    public String url;

    public WebDriver driver;

    public Log PLog = new Log(((TPage)BasePage.this).getClass());

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * open default page with url is not null
     */
    public TPage open() {
        if (url != null)
            return open(url);
        return null;
    }

    /**
     * open page with a specific url
     */
    private TPage open(String url) {
        driver.get(url);
        return (TPage) this;
    }

    /**
     * wait time in milliseconds
     */
    public void waitSafety(long time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * close page and quit driver
     */
    public void close() {
        driver.close();
        driver.quit();
    }
}
