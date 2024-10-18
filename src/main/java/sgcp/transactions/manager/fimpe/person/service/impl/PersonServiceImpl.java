package sgcp.transactions.manager.fimpe.person.service.impl;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sgcp.transactions.manager.fimpe.operation.dto.PersonDTO;
import sgcp.transactions.manager.fimpe.person.dto.PersonRequestDTO;
import sgcp.transactions.manager.fimpe.person.repository.PersonMapper;
import sgcp.transactions.manager.fimpe.person.service.PersonService;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonMapper personMapper;

    @Override
    public Maybe<PersonDTO> findById(long idPerson) {
        return Maybe.fromCallable(() -> personMapper.findById(idPerson))
                .subscribeOn(Schedulers.io())
                .filter(Optional::isPresent)
                .map(Optional::get)
                .switchIfEmpty(Maybe.defer(Maybe::empty));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Maybe<Integer> save(PersonRequestDTO personRequestDTO) {
        return Maybe.fromCallable(() -> personMapper.save(personRequestDTO))
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Flowable<List<PersonDTO>> findAllByPersonType(long idPersonType) {
        return Flowable.fromCallable(() -> personMapper.findAllByPersonType(idPersonType))
                .subscribeOn(Schedulers.io())
                .switchIfEmpty(Flowable.defer(Flowable::empty));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Maybe<Integer> update(PersonRequestDTO personRequestDTO) {
        return Maybe.fromCallable(() -> personMapper.update(personRequestDTO))
                .subscribeOn(Schedulers.io());
    }
}
