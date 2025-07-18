package utez.edu.mx.unidad3.modules.clients;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import utez.edu.mx.unidad3.modules.warehouse.Warehouse;

import java.util.List;

//atributos propios de la entidad
//Atributos de relacion
//Constructores
//Getters y Setters

@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    //@NotBlank
    //@NotNull
    //@Email
    //@Size(min = 1, max = 255)
    @Pattern(regexp = "^[a-z0-9][a-z0-9_.]{3,}@[a-z]{2,}(\\.[a-z]{2,}){1,2}$", message = "corre no valido")
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Pattern(regexp = "^[0-9]{10}$", message = "numero de telefono no valido")
    @Column(name = "phone", nullable = false)
    private String phone;

    @OneToMany(mappedBy = "client")
    @JsonIgnore
    private List<Warehouse> warehouses;

    public Client() {
    }

    public Client(Long id, String name, String email, String phone, List<Warehouse> warehouses) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.warehouses = warehouses;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id= id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Warehouse> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(List<Warehouse> warehouses) {
        this.warehouses = warehouses;
    }
}
