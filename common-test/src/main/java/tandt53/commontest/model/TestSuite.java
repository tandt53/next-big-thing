package tandt53.commontest.model;

import tandt53.commontest.exceptions.TestException;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class TestSuite {

    private final long id = System.currentTimeMillis();
    private String name;
    private String des;
    private List<TestCase> testcases = new ArrayList<>();

    public void addCase(TestCase testCase) throws TestException {
        if (testCase == null) {
            throw new TestException("Test case should not be null");
        }
        testcases.add(testCase);
    }

}