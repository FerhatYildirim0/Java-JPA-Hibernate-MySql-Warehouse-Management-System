package entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class CPOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cu_id;

    private String cu_name;
    private String cu_surname;
    private int fis_no;
    private String pro_title;
    private int order_size;
    private int pro_sale_price;
    private int order_id;
}
