package sgcp.transactions.manager.fimpe.operation.service;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import sgcp.transactions.manager.fimpe.operation.dto.OperationDTO;
import sgcp.transactions.manager.fimpe.operation.dto.OperationRequestDTO;

import java.util.List;

public interface OperationService {

    Maybe<OperationDTO> findById(long id);

    Maybe<Integer> save(OperationRequestDTO operation);

    Maybe<Integer> update(OperationRequestDTO operationRequest);

    Flowable<List<OperationDTO>> findAllOperationsByOperationTypeAndCollaborator(Long idOperationType, Long idCollaborator);

    Flowable<List<OperationDTO>> findAllOperationsByACollaborator(Long idCollaborator);
}
