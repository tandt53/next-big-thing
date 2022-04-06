package tandt.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class AuthRequestInfo {
    private String des;
    private Input input;
    private List<Output> output;
}
