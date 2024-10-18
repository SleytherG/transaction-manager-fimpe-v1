package sgcp.transactions.manager.fimpe.person.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonRequestDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Long idPersonType;


}
