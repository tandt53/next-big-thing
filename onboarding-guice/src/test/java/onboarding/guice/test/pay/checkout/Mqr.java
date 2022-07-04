package onboarding.guice.test.pay.checkout;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import onboarding.guice.test.pay.model.CheckoutInfo;

public class Mqr {

    @Inject
    @Named("mqr")
    private CheckoutInfo mqrInfo;


}
