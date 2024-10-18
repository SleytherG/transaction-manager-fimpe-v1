package sgcp.transactions.manager.fimpe.operation.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ListOperationResponseDTO {

    private List<OperationDTO> operations;

}
