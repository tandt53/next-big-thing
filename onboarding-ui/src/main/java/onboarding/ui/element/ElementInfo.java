package onboarding.ui.element;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ElementInfo {
    private String name;
    private String value;
    private WaitStrategy strategy;
    private long timeout;
}
