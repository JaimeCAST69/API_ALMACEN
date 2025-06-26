package utez.edu.mx.unidad3.modules.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utez.edu.mx.unidad3.utils.APIResponse;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Transactional(readOnly = true)
    public APIResponse findAll() {
        List<Client> list = new ArrayList<>();
        list = clientRepository.findAll();

        return new APIResponse("Operacion Exitosa", list, false, HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public APIResponse findById(Long id) {
        try {
            Client found = clientRepository.findById(id).orElse(null);
            if(found == null) {
                return new APIResponse("El cliente no existe", true, HttpStatus.NOT_FOUND);
            }
            return new APIResponse("Operación exitosa",found, false, HttpStatus.OK);
        }catch (Exception ex) {
            ex.printStackTrace();
            return new APIResponse("No se pudo consultar cliente", true, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public APIResponse saveClient(Client payload) {
        try{
            if(clientRepository.findByEmail(payload.getEmail()).isPresent()){
                return new APIResponse("Cliente ya existente", true, HttpStatus.BAD_REQUEST);
            }

            clientRepository.save(payload);
            return new APIResponse("Cliente guardado correctamente", false, HttpStatus.CREATED);
        }catch(Exception e){
            e.printStackTrace();
            return new APIResponse("Error al guardar", true, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public APIResponse updateClient(Client payload){
        try {
            Client found = clientRepository.findById(payload.getId()).orElse(null);
            if(found == null) {
                return new APIResponse("El cliente no existe", true, HttpStatus.NOT_FOUND);
            }
            clientRepository.save(payload);
            return new APIResponse("Cliente actualizado correctamente", false, HttpStatus.OK);
        }catch (NullPointerException nullex){
            nullex.printStackTrace();
            return new APIResponse("No se aceptan valors nulos", true, HttpStatus.BAD_REQUEST);
        }catch (Exception ex) {
            ex.printStackTrace();
            return new APIResponse("No se pudo actualizar cliente", true, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public APIResponse deleteClient(Client payload) {
        try {
            Client found = clientRepository.findById(payload.getId()).orElse(null);
            if(found == null) {
                return new APIResponse("El cliente no existe", true, HttpStatus.NOT_FOUND);
            }
            clientRepository.deleteById(found.getId());
            return new APIResponse("Cliente eliminado correctamente", false, HttpStatus.OK);
        }catch (Exception ex) {
            ex.printStackTrace();
            return new APIResponse("No se pudo eliinar cliente", true, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
