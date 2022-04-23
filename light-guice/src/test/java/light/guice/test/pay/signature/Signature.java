package light.guice.test.pay.signature;


import light.guice.test.pay.model.CheckoutInfo;

public abstract class Signature<T extends CheckoutInfo> {

    public void sign(String text){
        System.out.println("Sign " + text);
    }

    protected abstract String template(T checkoutinfo);

}
