package light.guice.test.pay.signature;


import light.guice.scan.annotations.NamedBinder;
import light.guice.test.pay.model.CqrInfo;

@NamedBinder(name = "cqr", bind = Signature.class)
public class CqrSignature extends Signature<CqrInfo> {

    @Override
    protected String template(CqrInfo cqrInfo) {
        return "CQR: " + cqrInfo.getName();
    }

}
