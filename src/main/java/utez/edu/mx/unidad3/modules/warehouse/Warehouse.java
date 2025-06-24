package utez.edu.mx.unidad3.modules.warehouse;

import jakarta.persistence.*;
import utez.edu.mx.unidad3.modules.cede.Cede;
import utez.edu.mx.unidad3.modules.clients.Client;

//atributos propios de la entidad
//Atributos de relacion
//Constructores
//Getters y Setters

@Entity
@Table(name = "warehouse")
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "clave", nullable = false, unique = true)
    private String clave;

    @Column(name = "sell_price", nullable = false)
    private Double sell_Price;

    @Column(name = "rent_preice", nullable = false)
    private Double rent_Price;

    @ManyToOne
    @JoinColumn(name = "id_cede", nullable = false)
    private Cede cede;

    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;

    public Warehouse() {
    }

    public Warehouse(Long id, String clave, Double sell_Price, Double rent_Price, Cede cede, Client client) {
        this.id = id;
        this.clave = clave;
        this.sell_Price = sell_Price;
        this.rent_Price = rent_Price;
        this.cede = cede;
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Double getSell_Price() {
        return sell_Price;
    }

    public void setSell_Price(Double sell_Price) {
        this.sell_Price = sell_Price;
    }

    public Double getRent_Price() {
        return rent_Price;
    }

    public void setRent_Price(Double rent_Price) {
        this.rent_Price = rent_Price;
    }

    public Cede getCede() {
        return cede;
    }

    public void setCede(Cede cede) {
        this.cede = cede;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
