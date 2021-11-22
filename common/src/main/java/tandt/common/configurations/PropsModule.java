package tandt.common.configurations;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;

public class PropsModule extends AbstractModule {

    protected Context context;

    public PropsModule() {
        this.context = ContextImpl.createInstance();
    }

    @Override
    protected void configure() {
        bindListener(Matchers.any(), new PropertyTypeListener(context.getCapability()));
    }

}