package entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pro_id;

    @Column(length = 500)
    private String pro_title;

    private int pro_buying_price;
    private int pro_sale_price;
    private long pro_code;
    private int pro_tax_status;
    private int pro_unit_status;
    private int pro_amount;

    @Column(length = 500)
    private String pro_detail;


}
