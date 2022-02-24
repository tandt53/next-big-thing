package tandt.commontest.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class TestSuite {

    private final long id = System.currentTimeMillis();
    private String suiteId;
    private String name;
    private String description;
    private List<TestCase> testcases = new ArrayList<>();

}
