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
}
