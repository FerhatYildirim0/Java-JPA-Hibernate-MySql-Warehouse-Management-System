package entities;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class SaverObject {

    @Id
    private int fis_obj_no;

    private int cu_obj_id;
    private int or_obj_id;
    private int price;

}
