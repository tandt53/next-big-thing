package tandt.guice.test.pay.signature;


import tandt.guice.scan.annotations.NamedBinder;
import tandt.guice.test.pay.model.CqrInfo;

@NamedBinder(name = "cqr", bind = Signature.class)
public class CqrSignature extends Signature<CqrInfo> {

    @Override
    protected String template(CqrInfo cqrInfo) {
        return "CQR: " + cqrInfo.getName();
    }

}
