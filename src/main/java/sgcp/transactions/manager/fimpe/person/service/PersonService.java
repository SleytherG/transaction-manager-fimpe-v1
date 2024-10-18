package sgcp.transactions.manager.fimpe.person.service;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import org.springframework.http.ResponseEntity;
import sgcp.transactions.manager.fimpe.operation.dto.PersonDTO;
import sgcp.transactions.manager.fimpe.person.dto.ListPersonResponseDTO;
import sgcp.transactions.manager.fimpe.person.dto.PersonRegisterResponseDTO;
import sgcp.transactions.manager.fimpe.person.dto.PersonRequestDTO;

import java.util.List;

public interface PersonService {
    Maybe<PersonDTO> findById(long idPerson);

    Maybe<Integer> save(PersonRequestDTO personRequestDTO);

    Flowable<List<PersonDTO>> findAllByPersonType(long idPersonType);

    Maybe<Integer> update(PersonRequestDTO personRequestDTO);
}
