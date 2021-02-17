package tandt.commontest.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TestStep {

    private final long id = System.currentTimeMillis();
    private String name;
    private String des;
    private long duration;
    private long start;
    private long end;
    private TestResult result;
    private ExpectedResult expectations;
    private byte[] screenshot;
}

