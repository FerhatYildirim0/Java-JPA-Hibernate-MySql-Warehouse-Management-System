package entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer order_id;

    private int fis_no;
    private int status;
    private int order_size;
    private int total;

    @Temporal(TemporalType.DATE)
    private Date date;
    @OneToOne(cascade = CascadeType.DETACH)
    private Customer customer;
    @OneToOne(cascade = CascadeType.DETACH)
    private  Products products;
}
