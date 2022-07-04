package onboarding.commontest.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TestResult {
    private final long id = System.currentTimeMillis();
    private ResultType result;
    private String message;
    private String log;
}
