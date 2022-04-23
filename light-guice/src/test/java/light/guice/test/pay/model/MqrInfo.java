package light.guice.test.pay.model;

import lombok.Getter;
import lombok.Setter;
import light.guice.scan.annotations.NamedBinder;

@Setter
@Getter
@NamedBinder(name = "mqr", bind = CheckoutInfo.class)
public class MqrInfo implements CheckoutInfo {

    private String name;
}
