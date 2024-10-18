package sgcp.transactions.manager.fimpe.operation.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OperationRegisterResponseDTO {

    private String message;
    private Integer code;
}
