package tandt.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Input {
    private String grant_type;

    private String client_id;

    private String client_secret;

    private String audience;

}
