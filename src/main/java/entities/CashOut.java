package entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class CashOut {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer cashOut_id;

    private String payOutDetail;
    private int payOutTotal;
    private String payOutTitle;
    private int payOutSection;
    @Temporal(TemporalType.DATE)
    private Date payOut_date;

}
