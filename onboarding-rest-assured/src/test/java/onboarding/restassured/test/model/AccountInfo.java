package onboarding.restassured.test.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AccountInfo {
    private String phoneNumber;
    private String name;
    private int age;
    private boolean isMarried;
}
