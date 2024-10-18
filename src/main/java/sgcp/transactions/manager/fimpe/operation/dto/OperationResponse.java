package sgcp.transactions.manager.fimpe.operation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class OperationResponse {

    private String message;
    private Integer code;

}
