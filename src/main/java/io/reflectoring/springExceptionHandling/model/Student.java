package io.reflectoring.springExceptionHandling.model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "student")
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
@Data

public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int age;
    private String address;
    private String email;

}
