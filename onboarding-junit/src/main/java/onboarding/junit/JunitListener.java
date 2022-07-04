package onboarding.junit;

import junit.framework.AssertionFailedError;
import junit.framework.Test;
import junit.textui.TestRunner;

public class JunitListener extends TestRunner {
    @Override
    public void addError(Test test, Throwable throwable) {

    }

    @Override
    public void addFailure(Test test, AssertionFailedError assertionFailedError) {

    }

    @Override
    public void endTest(Test test) {

    }

    @Override
    public void startTest(Test test) {

    }
}
