package sgcp.transactions.manager.fimpe.person.repository;

import io.reactivex.rxjava3.annotations.Nullable;
import org.apache.ibatis.annotations.Mapper;
import sgcp.transactions.manager.fimpe.operation.dto.PersonDTO;
import sgcp.transactions.manager.fimpe.person.dto.PersonRequestDTO;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PersonMapper {

    Optional<PersonDTO> findById(Long idPerson);

    Integer save(PersonRequestDTO personRequestDTO);

    List<PersonDTO> findAllByPersonType(long idPersonType);

    Integer update(PersonRequestDTO personRequestDTO);
}
