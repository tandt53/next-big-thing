package tandt.mobile.page;

import com.google.inject.Inject;
import tandt.common.exceptions.CommonException;
import tandt.dataprovider.exceptions.PropertiesException;
import tandt.mobile.drivermanager.MobileDriverManager;

import java.net.MalformedURLException;

public class DefaultPageFactory implements PageFactory {

    private MobileDriverManager _driver;

    @Inject
    public DefaultPageFactory(MobileDriverManager driver) {
        _driver = driver;
    }


    @Override
    public void create() throws PropertiesException, CommonException, MalformedURLException {
        _driver.initDriver();
    }

}
