package sgcp.transactions.manager.fimpe.operation.service.impl;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sgcp.transactions.manager.fimpe.operation.dto.OperationDTO;
import sgcp.transactions.manager.fimpe.operation.dto.OperationRequestDTO;
import sgcp.transactions.manager.fimpe.operation.repository.OperationMapper;
import sgcp.transactions.manager.fimpe.operation.service.OperationService;

import java.util.List;
import java.util.Optional;

@Service
public class OperationServiceImpl implements OperationService {

    @Autowired
    private OperationMapper operationMapper;

    @Override
    public Maybe<OperationDTO> findById(long id) {
        return Maybe.fromCallable(() -> operationMapper.findById(id))
                .subscribeOn(Schedulers.io())
                .filter(Optional::isPresent)
                .map(Optional::get)
                .switchIfEmpty(Maybe.defer(Maybe::empty));
    }

    @Override
    @Transactional
    public Maybe<Integer> save(OperationRequestDTO operationRequest) {
        return Maybe.fromCallable(() -> operationMapper.save(operationRequest))
                .subscribeOn(Schedulers.io());
    }

    @Override
    @Transactional
    public Maybe<Integer> update(OperationRequestDTO operationRequest) {
        return Maybe.fromCallable(() -> operationMapper.update(operationRequest))
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Flowable<List<OperationDTO>> findAllOperationsByOperationTypeAndCollaborator(Long idOperationType, Long idCollaborator) {
        return Flowable.fromCallable(() -> operationMapper.findAllOperationsByOperationTypeAndCollaborator(idOperationType, idCollaborator))
                .subscribeOn(Schedulers.io())
                .switchIfEmpty(Flowable.defer(Flowable::empty));
    }

    @Override
    public Flowable<List<OperationDTO>> findAllOperationsByACollaborator(Long idCollaborator) {
        return Flowable.fromCallable(() -> operationMapper.findAllOperationsByACollaborator(idCollaborator))
                .subscribeOn(Schedulers.io())
                .switchIfEmpty(Flowable.defer(Flowable::empty));
    }

}
