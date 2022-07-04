package onboarding.guice.test.pay.signature;


import onboarding.guice.test.pay.model.CheckoutInfo;

public abstract class Signature<T extends CheckoutInfo> {

    public void sign(String text){
        System.out.println("Sign " + text);
    }

    protected abstract String template(T checkoutinfo);

}
