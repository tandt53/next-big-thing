package light.guice.test.pay;

import com.google.inject.Guice;
import com.google.inject.Injector;
import light.guice.test.pay.checkout.Cqr;
import light.guice.scan.NamedScanner;

public class Main {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new NamedScanner());
        Cqr cqr = injector.getInstance(Cqr.class);
        cqr.init();
    }
}