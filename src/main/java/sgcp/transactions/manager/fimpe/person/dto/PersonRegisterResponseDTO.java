package sgcp.transactions.manager.fimpe.person.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PersonRegisterResponseDTO {

    private String message;
    private Integer code;

}
