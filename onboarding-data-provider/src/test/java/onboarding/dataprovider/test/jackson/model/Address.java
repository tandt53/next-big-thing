package onboarding.dataprovider.test.jackson.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class Address {
    private String street;
    private String city;
    private long zipcode;
}