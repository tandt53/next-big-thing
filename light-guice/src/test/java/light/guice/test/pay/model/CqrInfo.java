package light.guice.test.pay.model;

import lombok.Getter;
import lombok.Setter;
import light.guice.scan.annotations.NamedBinder;

@Setter
@Getter
@NamedBinder(name = "cqr", bind = CheckoutInfo.class)
public class CqrInfo implements CheckoutInfo {
    private String name;
}
