package tandt.commontest;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;

public class TestModule extends AbstractModule {

    private TestContext testContext;

    @Override
    protected void configure() {
        TestContext testContext = TestContext.getInstance();
//        bindListener(Matchers.any(), new PropertyTypeListener(context.getCapability()));
    }

}
