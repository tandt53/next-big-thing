package tandt.guice.test.pay.checkout;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import tandt.guice.test.pay.model.CheckoutInfo;

public class Mqr {

    @Inject
    @Named("mqr")
    private CheckoutInfo mqrInfo;


}
