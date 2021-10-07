package entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class CashObject {

    @Id
    private int cu_id;
    private int fis_no;
    private int payInTotal;
    private String payInDetail;
}
