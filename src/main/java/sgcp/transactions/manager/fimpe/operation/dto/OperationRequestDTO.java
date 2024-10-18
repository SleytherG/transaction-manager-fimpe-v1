package sgcp.transactions.manager.fimpe.operation.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OperationRequestDTO {

    private Long id;
    private String operationDate;
    private String operationTime;
    private Double amount;
    private Long idCollaborate;
    private Long idOperationType;
    private Long idAffiliate;


}
