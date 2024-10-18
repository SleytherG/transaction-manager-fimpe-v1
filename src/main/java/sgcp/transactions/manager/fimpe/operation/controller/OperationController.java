package sgcp.transactions.manager.fimpe.operation.controller;

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
import sgcp.transactions.manager.fimpe.operation.dto.ListOperationResponseDTO;
import sgcp.transactions.manager.fimpe.operation.dto.OperationDTO;
import sgcp.transactions.manager.fimpe.operation.dto.OperationRegisterResponseDTO;
import sgcp.transactions.manager.fimpe.operation.dto.OperationRequestDTO;
import sgcp.transactions.manager.fimpe.operation.dto.OperationUpdateResponseDTO;
import sgcp.transactions.manager.fimpe.operation.service.OperationService;
import sgcp.transactions.manager.fimpe.util.constants.Constants;


@RestController
@Tag(name = "Operation")
@RequestMapping
@Slf4j
@AllArgsConstructor
public class OperationController {

    @Autowired
    private OperationService operationService;


    @GetMapping(value = "/operation/{idOperation}",
            produces = {MediaType.APPLICATION_STREAM_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Busca una operación por su id", method = "GET",
            responses = {
                    @ApiResponse(responseCode = "200", description = "&Eacute;xito.", content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Operation.class))}),
                    @ApiResponse(responseCode = "500", description = "Ha ocurrido un error inesperado.", content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Error.class))})})
    public Maybe<ResponseEntity<OperationDTO>> findOperationById(
            @Parameter(required = true) @PathVariable Long idOperation) {
        return operationService.findById(idOperation)
                .map(ResponseEntity::ok);
    }

    @PostMapping(value = "/operation",
            produces = {MediaType.APPLICATION_STREAM_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Realiza la creación de una operación", method = "POST", responses = {
            @ApiResponse(responseCode = "200", description = "&Eacute;xito.", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = OperationDTO.class))}),
            @ApiResponse(responseCode = "500", description = "Ha ocurrido un error inesperado.", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Error.class))})})
    public Maybe<ResponseEntity<OperationRegisterResponseDTO>> registerAnOperation(
            @Parameter(required = true) @RequestBody OperationRequestDTO operationRequest) {
        return operationService.save(operationRequest)
                .map(code ->
                        OperationRegisterResponseDTO
                                .builder()
                                .message(Constants.OPERATION_REGISTER_SUCCESSFULLY)
                                .code(code)
                                .build())
                .map(ResponseEntity::ok);
    }

    @GetMapping(value = "/operation/{idOperationType}/{idCollaborator}",
            produces = {MediaType.APPLICATION_STREAM_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Obtiene una lista de operaciones filtradas por tipo de operación y colaborador.", method = "GET", responses = {
            @ApiResponse(responseCode = "200", description = "&Eacute;xito.", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = OperationDTO.class))}),
            @ApiResponse(responseCode = "500", description = "Ha ocurrido un error inesperado.", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Error.class))})})
    public Flowable<ResponseEntity<ListOperationResponseDTO>> getAllOperationsByOperationTypeAndCollaborator(
            @Parameter(required = true) @PathVariable Long idOperationType,
            @Parameter(required = true) @PathVariable Long idCollaborator) {
        return operationService.findAllOperationsByOperationTypeAndCollaborator(idOperationType, idCollaborator)
                .map(listOperations ->
                        ResponseEntity.ok(ListOperationResponseDTO.builder().operations(listOperations).build()));
    }

    @GetMapping(value = "/operation/findByCollaborator/{idCollaborator}",
            produces = {MediaType.APPLICATION_STREAM_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Obtiene una lista de operaciones filtradas por el idCollaborator.", method = "GET", responses = {
            @ApiResponse(responseCode = "200", description = "&Eacute;xito.", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = OperationDTO.class))}),
            @ApiResponse(responseCode = "500", description = "Ha ocurrido un error inesperado.", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Error.class))})})
    public Flowable<ResponseEntity<ListOperationResponseDTO>> findAllOperationsByACollaborator(
            @Parameter(required = true) @PathVariable Long idCollaborator) {
        return operationService.findAllOperationsByACollaborator(idCollaborator)
                .map(listOperations -> ResponseEntity.ok(ListOperationResponseDTO.builder().operations(listOperations).build()));
    }


    @PutMapping(value = "/operation",
            produces = {MediaType.APPLICATION_STREAM_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Actualiza una operación filtraba por su identificador.", method = "PUT", responses = {
            @ApiResponse(responseCode = "200", description = "&Eacute;xito.", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = OperationDTO.class))}),
            @ApiResponse(responseCode = "500", description = "Ha ocurrido un error inesperado.", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Error.class))})})
    public Maybe<ResponseEntity<OperationUpdateResponseDTO>> updateAnOperation(
            @Parameter(required = true) @RequestBody OperationRequestDTO operationRequest) {
        return operationService.update(operationRequest)
                .map(code -> ResponseEntity.ok(OperationUpdateResponseDTO.builder().message(Constants.OPERATION_UPDATE_SUCCESSFULLY).code(code).build()));
    }
}
