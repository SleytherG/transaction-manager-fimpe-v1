package sgcp.transactions.manager.fimpe.person.controller;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import sgcp.transactions.manager.fimpe.operation.dto.PersonDTO;
import sgcp.transactions.manager.fimpe.person.dto.ListPersonResponseDTO;
import sgcp.transactions.manager.fimpe.person.dto.PersonRegisterResponseDTO;
import sgcp.transactions.manager.fimpe.person.dto.PersonRequestDTO;
import sgcp.transactions.manager.fimpe.person.dto.PersonUpdateResponseDTO;
import sgcp.transactions.manager.fimpe.person.service.PersonService;
import sgcp.transactions.manager.fimpe.util.constants.Constants;

@RestController
@Tag(name = "Person")
@RequestMapping
@Slf4j
@AllArgsConstructor
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping(value = "/person/{idPerson}",
        produces = { MediaType.APPLICATION_STREAM_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Busca una persona por su id", method = "GET",
            responses = {
                @ApiResponse(responseCode = "200", description = "&Eacute;xito.", content = {
                        @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                schema = @Schema(implementation = PersonDTO.class))}),
                @ApiResponse(responseCode = "500", description = "Ha ocurrido un error inesperado.", content = {
                        @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                schema = @Schema(implementation = Error.class)) }) })
    public Maybe<ResponseEntity<PersonDTO>> findPersonById(
            @Parameter(required = true) @PathVariable("idPerson") long idPerson) {
        return personService.findById(idPerson)
                .map(ResponseEntity::ok);
    }

    @PostMapping(value = "/person",
            produces = {MediaType.APPLICATION_STREAM_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Realiza la creación de una persona", method = "POST", responses = {
            @ApiResponse(responseCode = "200", description = "&Eacute;xito.", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PersonDTO.class))}),
            @ApiResponse(responseCode = "500", description = "Ha ocurrido un error inesperado.", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Error.class)) }) })
    public Maybe<ResponseEntity<PersonRegisterResponseDTO>> registerAnOperation(
            @Parameter(required = true) @RequestBody PersonRequestDTO personRequestDTO) {
        return personService.save(personRequestDTO)
                .map(code ->
                        PersonRegisterResponseDTO
                                .builder()
                                .message(Constants.PERSON_REGISTER_SUCCESSFULLY)
                                .code(code)
                                .build())
                .map(ResponseEntity::ok);
    }

    @GetMapping(value = "/person/findAllByPersonType/{idPersonType}",
            produces = { MediaType.APPLICATION_STREAM_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Obtiene una lista de personas filtradas por el tipo de persona", method = "POST", responses = {
            @ApiResponse(responseCode = "200", description = "&Eacute;xito.", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PersonDTO.class))}),
            @ApiResponse(responseCode = "500", description = "Ha ocurrido un error inesperado.", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Error.class)) }) })
    public Flowable<ResponseEntity<ListPersonResponseDTO>> findAllByPersonType(
            @Parameter(required = true) @PathVariable("idPersonType") long idPersonType) {
        return personService.findAllByPersonType(idPersonType)
                .map(personList -> ResponseEntity.ok(
                        ListPersonResponseDTO
                                .builder()
                                .persons(personList)
                                .build()));

    }

    @PutMapping(value = "/person",
            produces = { MediaType.APPLICATION_STREAM_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Actualiza una persona filtraba por su identificador.", method = "PUT", responses = {
            @ApiResponse(responseCode = "200", description = "&Eacute;xito.", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PersonDTO.class))}),
            @ApiResponse(responseCode = "500", description = "Ha ocurrido un error inesperado.", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Error.class)) }) })
    public Maybe<ResponseEntity<PersonUpdateResponseDTO>> updateAPerson(
            @Parameter(required = true) @RequestBody PersonRequestDTO personRequestDTO) {
        return personService.update(personRequestDTO)
                .map(code -> ResponseEntity.ok(PersonUpdateResponseDTO.builder().message(Constants.PERSON_UPDATE_SUCCESSFULLY).code(code).build()));
    }




}
