package utez.edu.mx.unidad3.modules.clients;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
    private Long id0;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "phone", nullable = false)
    private String phone;

    @OneToMany(mappedBy = "client")
    @JsonIgnore
    private List<Warehouse> warehouses;

    public Client() {
    }

    public Client(Long id0, String name, String email, String phone, List<Warehouse> warehouses) {
        this.id0 = id0;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.warehouses = warehouses;
    }

    public Long getId0() {
        return id0;
    }

    public void setId0(Long id0) {
        this.id0 = id0;
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
