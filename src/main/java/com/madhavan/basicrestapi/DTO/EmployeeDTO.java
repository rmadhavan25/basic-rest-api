package com.madhavan.basicrestapi.DTO;

import com.madhavan.basicrestapi.annotations.EmployeeRoleValidation;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    private Long id;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotNull(message = "Email cannot be null")
    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "Age cannot be null")
    @Max(value = 80, message = "Age of employee cannot be greater than 80")
    @Min(value = 20, message = "Age of employee cannot be less than 20")
    private Integer age;

    @PastOrPresent(message = "Date of joining cannot be in the future")
    private LocalDate dateOfJoining;

    @NotBlank(message = "Role cannot be blank")
    @EmployeeRoleValidation
    private String role;

    private Boolean isActive;

    @Positive(message = "Salary must be a positive number")
    @Digits(integer = 6, fraction = 2, message = "Salary must be a valid number with up to 6 digits and 2 decimal places")
    private Integer salary;
}
