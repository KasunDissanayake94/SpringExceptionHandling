package io.reflectoring.springExceptionHandling.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor

public class StudentRequest {
    @NotBlank (message = "name should not be null")
    private String name;
    @Range(min= 18, max= 60)
    private int age;
    @NotBlank (message = "address should not be null")
    private String address;
    @Email (message = "invalid email address")
    private String email;
}
