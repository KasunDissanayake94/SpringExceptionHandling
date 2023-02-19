package io.reflectoring.springExceptionHandling.model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "student")
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String name;

    @NotNull
    private int age;

    @NotNull
    private String address;

    @NotNull
    @Column(unique = true)
    private String email;

}
