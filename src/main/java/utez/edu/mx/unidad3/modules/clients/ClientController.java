package utez.edu.mx.unidad3.modules.clients;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.unidad3.utils.APIResponse;

@RestController
@RequestMapping("/api/client")
@Tag(name = "Controlador de cllien")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping("")
    @Operation(summary = "Traer todo los clientes", description = "Trae el listado de los clientes")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Respuestas de operacion exitosa",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = APIResponse.class)),
                            @Content(mediaType = "application/xml", schema = @Schema(implementation = APIResponse.class))
                    }
            )
    })
    public ResponseEntity<APIResponse> findAll() {
        APIResponse response = clientService.findAll();
        return new ResponseEntity<>(response, response.getStatus());
    }

    @PostMapping("")
    @Operation(summary = "Registrar cliente", description = "Registra un cliente en el sistema")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Respuestas de operacion exitosa",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = APIResponse.class)),

                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Respuestas de operacion erronea",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = APIResponse.class)),

                    }
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Respuestas de error interno en el servidor",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = APIResponse.class)),

                    }
            )
    })
    public ResponseEntity<APIResponse> saveClient(@RequestBody Client payload) {
        APIResponse response = clientService.saveClient(payload);
        return new ResponseEntity<>(response, response.getStatus());
    }
}
