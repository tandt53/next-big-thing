package onboarding.guice.test.pay.signature;


import onboarding.guice.scan.annotations.NamedBinder;
import onboarding.guice.test.pay.model.CqrInfo;

@NamedBinder(name = "cqr", bind = Signature.class)
public class CqrSignature extends Signature<CqrInfo> {

    @Override
    protected String template(CqrInfo cqrInfo) {
        return "CQR: " + cqrInfo.getName();
    }

}
