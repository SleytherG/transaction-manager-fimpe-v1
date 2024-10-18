package sgcp.transactions.manager.fimpe.person.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sgcp.transactions.manager.fimpe.operation.dto.PersonDTO;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListPersonResponseDTO {

    List<PersonDTO> persons;

}
