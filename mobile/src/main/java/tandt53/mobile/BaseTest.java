package tandt53.mobile;

/**
 * Created by thetan.do on 12/28/2016.
 */

public class BaseTest<TTest extends BaseTest<?>> {


	public BaseTest() throws IllegalAccessException {
		MobileFactory.initPages(this);
	}


}
