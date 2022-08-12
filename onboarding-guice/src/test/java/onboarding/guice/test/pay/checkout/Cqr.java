package onboarding.guice.test.pay.checkout;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import onboarding.guice.test.pay.model.CheckoutInfo;
import onboarding.guice.test.pay.signature.Signature;

public class Cqr {

    @Inject
    @Named("cqr")
    private CheckoutInfo checkoutInfo;

    @Inject
    @Named("cqr")
    private Signature signature;


    public void init(){
        signature.sign(checkoutInfo.toString());
    }

}
