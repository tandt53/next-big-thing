package light.guice.test.pay.model;

import light.guice.scan.annotations.NamedBinder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@NamedBinder(name = "cqr", bind = CheckoutInfo.class)
public class CqrInfo implements CheckoutInfo {
    private String name;
}
