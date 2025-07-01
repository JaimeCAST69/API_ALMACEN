package utez.edu.mx.unidad3.modules.cede;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.unidad3.utils.APIResponse;

@RestController
@RequestMapping("/api/cedes")
@Tag(name = "CedeController", description = "Operaciones relacionadas con las Cedes")
public class CedeController {

    @Autowired
    private CedeService cedeService;

    @Operation(summary = "Obtener todas las cedes", description = "Retorna una lista de todas las cedes registradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping
    public ResponseEntity<APIResponse> getAllCedes() {
        APIResponse response = cedeService.findAll();
        return new ResponseEntity<>(response, response.getStatus());
    }

    @Operation(summary = "Obtener una cede por ID", description = "Busca una cede específica por su identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cede encontrada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Cede no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<APIResponse> getCedeById(@PathVariable long id) {
        APIResponse response = cedeService.findById(id);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @Operation(summary = "Registrar nueva cede", description = "Crea una nueva cede y genera su clave automáticamente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cede creada exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error al guardar la cede")
    })
    @PostMapping
    public ResponseEntity<APIResponse> createCede(@RequestBody Cede payload) {
        APIResponse response = cedeService.saveCede(payload);
        return new ResponseEntity<>(response, response.getStatus());
    }
}
