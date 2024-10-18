package sgcp.transactions.manager.fimpe.operation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OperationDTO {

    private Long id;
    private String operationDate;
    private String operationTime;
    private Double amount;
    private PersonDTO collaborate;
    private OperationTypeDTO operationType;
    private PersonDTO affiliate;

}
