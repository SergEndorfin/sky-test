package click.itkon.skytest.domain;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class UserName {

    private String prefix;
    private String firstName;
    private String lastName;
}
