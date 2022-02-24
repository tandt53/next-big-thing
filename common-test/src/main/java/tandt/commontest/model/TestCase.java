package tandt.commontest.model;

import lombok.Getter;
import lombok.Setter;
import tandt.commontest.exceptions.TestException;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class TestCase {


    private final long id = System.currentTimeMillis();
    private String testcaseId;
    private String name;
    private String description;
    private long duration;
    private long start;
    private long end;
    private List<TestStep> testSteps = new ArrayList<>();
    private TestResult result;
    private Meta<? extends Meta<?>> meta;
    private byte[] screenshot;

    public void addStep(TestStep testStep) throws TestException {
        if (testStep == null) {
            throw new TestException("Test step should not be null");
        }
        testSteps.add(testStep);
    }

}
