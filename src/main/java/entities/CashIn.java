package entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class CashIn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cash_id;
    private int cu_id;
    private int order_id;
    private int cash_status;
    private String payInDetail;
    private int payInTotal;

    @Temporal(TemporalType.DATE)
    private Date cashIn_date;
    @OneToOne
    private Orders order;


}
