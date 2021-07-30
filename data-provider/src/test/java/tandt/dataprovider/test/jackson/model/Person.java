package tandt.dataprovider.test.jackson.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class Person {
    private int id;
    private String name;
    private boolean permanent;
    private Address address;
    private List<String> phoneNumbers;
    private String role;
    private List<String> cities;
    private Properties properties;
}
