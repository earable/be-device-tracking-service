package ai.earable.platform.device.tracking.api;

import ai.earable.platform.common.data.exception.ErrorDetails;
import ai.earable.platform.device.tracking.model.CheckVersionRequestDTO;
import ai.earable.platform.device.tracking.model.CheckVersionResponseDTO;
import ai.earable.platform.device.tracking.model.CreateVersionRequestDTO;
import ai.earable.platform.device.tracking.model.CreateVersionResponseDTO;
import ai.earable.platform.device.tracking.model.StoreAppInfoRequestDTO;
import ai.earable.platform.device.tracking.model.StoreAppInfoResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

public interface DeviceAPI {
    String HEALTH = "/health";
    String STORE_APP_INFO = "/app-info";
    String VERSION_INFO = "/versions";

    @GetMapping(value = HEALTH)
    Mono<String> checkHealth();

    @Operation(operationId = "DTS01")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content =
            @Content(mediaType = "application/json", schema = @Schema(implementation = StoreAppInfoResponseDTO.class))
            ),
            @ApiResponse(responseCode = "400", content =
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class),
                    examples = {
                            @ExampleObject(name = "APP_ID_REQUIRED", ref = "/swagger/components/examples/AppIdRequired.json")
                    })
            ),
            @ApiResponse(responseCode = "405", content =
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class),
                    examples = @ExampleObject(name = "METHOD_NOT_ALLOWED", ref = "/swagger/components/examples/MethodNotAllowed" +
                            ".json"))
            ),
            @ApiResponse(responseCode = "406", content =
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class),
                    examples = @ExampleObject(name = "NOT_ACCEPTABLE", ref = "/swagger/components/examples/NotAcceptable.json"))
            ),
            @ApiResponse(responseCode = "415", content =
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class),
                    examples = @ExampleObject(name = "UNSUPPORTED_MEDIA_TYPE", ref = "/swagger/components/examples" +
                            "/UnsupportedMediaType.json"))
            )
    })
    @PutMapping(path = STORE_APP_INFO, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Mono<StoreAppInfoResponseDTO> storeAppInfo(@RequestBody @Valid StoreAppInfoRequestDTO storeAppInfoDTO);

    @Operation(operationId = "DTS02")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content =
            @Content(mediaType = "application/json",  array = @ArraySchema(schema = @Schema(implementation = CheckVersionResponseDTO.class)))
            ),
            @ApiResponse(responseCode = "400", content =
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class),
                    examples = {
                            @ExampleObject(name = "APP_ID_REQUIRED", ref = "/swagger/components/examples/AppIdRequired.json")
                    })
            ),
            @ApiResponse(responseCode = "405", content =
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class),
                    examples = @ExampleObject(name = "METHOD_NOT_ALLOWED", ref = "/swagger/components/examples/MethodNotAllowed" +
                            ".json"))
            ),
            @ApiResponse(responseCode = "406", content =
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class),
                    examples = @ExampleObject(name = "NOT_ACCEPTABLE", ref = "/swagger/components/examples/NotAcceptable.json"))
            ),
            @ApiResponse(responseCode = "415", content =
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class),
                    examples = @ExampleObject(name = "UNSUPPORTED_MEDIA_TYPE", ref = "/swagger/components/examples" +
                            "/UnsupportedMediaType.json"))
            )
    })
    @PutMapping(path = VERSION_INFO, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Flux<CheckVersionResponseDTO> checkVersion(@Valid @RequestBody CheckVersionRequestDTO checkVersionRequestDTO);

    @Operation(operationId = "DTS02")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content =
            @Content(mediaType = "application/json", schema = @Schema(implementation = CreateVersionResponseDTO.class))
            ),
            @ApiResponse(responseCode = "400", content =
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class),
                    examples = {
                            @ExampleObject(name = "APP_ID_REQUIRED", ref = "/swagger/components/examples/AppIdRequired.json")
                    })
            ),
            @ApiResponse(responseCode = "405", content =
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class),
                    examples = @ExampleObject(name = "METHOD_NOT_ALLOWED", ref = "/swagger/components/examples/MethodNotAllowed" +
                            ".json"))
            ),
            @ApiResponse(responseCode = "406", content =
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class),
                    examples = @ExampleObject(name = "NOT_ACCEPTABLE", ref = "/swagger/components/examples/NotAcceptable.json"))
            ),
            @ApiResponse(responseCode = "415", content =
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class),
                    examples = @ExampleObject(name = "UNSUPPORTED_MEDIA_TYPE", ref = "/swagger/components/examples" +
                            "/UnsupportedMediaType.json"))
            )
    })
    @PostMapping(path = VERSION_INFO, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Mono<CreateVersionResponseDTO> createVersion(@RequestBody CreateVersionRequestDTO createVersionRequestDTO);
}

