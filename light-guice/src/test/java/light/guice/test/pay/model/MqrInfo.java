package light.guice.test.pay.model;

import light.guice.scan.annotations.NamedBinder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@NamedBinder(name = "mqr", bind = CheckoutInfo.class)
public class MqrInfo implements CheckoutInfo {

    private String name;
}
