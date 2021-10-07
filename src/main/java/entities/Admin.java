package entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id ;
    private String name;
    private String surname;
    private String email;
    @Column(length = 32)
    private String password;
}
