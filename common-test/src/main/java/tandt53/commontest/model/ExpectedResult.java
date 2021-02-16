package tandt53.commontest.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
public class ExpectedResult {
    private final long id = System.currentTimeMillis();
    private Map<String, Object> expectations;

}
