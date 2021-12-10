package tandt.guice.test.pay.checkout;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import tandt.guice.test.pay.model.CheckoutInfo;
import tandt.guice.test.pay.signature.Signature;

public class Cqr {

    @Inject
    @Named("cqr")
    private CheckoutInfo checkoutInfo;

    @Inject
    @Named("cqr")
    private Signature signature;


    public void init(){
        signature.sign(checkoutInfo.toString());
        System.out.println("Cqr init");
    }

}
