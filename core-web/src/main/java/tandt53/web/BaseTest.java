package tandt53.web;

/**
 * Created by thetan.do on 12/28/2016.
 */

public class BaseTest<TTest extends BaseTest<?>> {

	public BaseTest() throws IllegalAccessException {
		BrowserFactory.initPages(this);
	}


}
