package sgcp.transactions.manager.fimpe.operation.repository;

import org.apache.ibatis.annotations.Mapper;
import sgcp.transactions.manager.fimpe.operation.dto.OperationDTO;
import sgcp.transactions.manager.fimpe.operation.dto.OperationRequestDTO;

import java.util.List;
import java.util.Optional;

@Mapper
public interface OperationMapper {

    Optional<OperationDTO> findById(Long id);

    Integer save(OperationRequestDTO request);

    Integer update(OperationRequestDTO operationRequest);

    List<OperationDTO> findAllOperationsByOperationTypeAndCollaborator(Long idOperationType, Long idCollaborator);

    List<OperationDTO> findAllOperationsByACollaborator(Long idCollaborator);
}
