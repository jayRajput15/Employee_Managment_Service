package com.example.EmployeeOrg.dto;

import com.example.EmployeeOrg.annotations.EmployeeRoleValidation;
import com.example.EmployeeOrg.annotations.PasswordValidation;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private Long id;

    @NotBlank(message = "Name should not be blank")
    @Size(min = 1, max = 10, message = "Number of character in name should be in range: [2,10]")
    private String name;

    @NotBlank(message = "Email of an employee cannot be blank")
    @Email(message = "Email should be a valid email")
    private String email;

  //  @NotBlank(message = "Age of the employee cannot be blank")
    @NotNull(message = "Age of the employee cannot be blank")
    @Max(value = 80, message = "Age should be not greater than 80")
    @Min(value = 15, message = "Age should not be less than 15")
    private Integer age;

    @NotBlank(message = "Role of the Employee cannot  be blank")
   // @Pattern(regexp = "^(ADMIN|USER)$", message = "Role of Employee can be either USER or ADMIN")
    @EmployeeRoleValidation
    private String role;

   // @NotBlank(message = "Salary of an employee cannot be blank")
    @NotNull(message = "Salary of an employee cannot be blank")
    @Positive(message = "Salary of an employee should always be positive")
    @Digits(fraction = 3, integer = 10, message = "The salary can be in the form of XXXXXXXXXX.YYY")
    @DecimalMax(value = "10000000.999")
    @DecimalMin(value = "100.999")
    private Double salary;

    @PasswordValidation
    private String password;

    
    @PastOrPresent(message = "Date of joining of an employee cannot be of future")
    private LocalDate dateOfJoining;

    @AssertTrue(message = "Employee should be Active")
    @JsonProperty("isActive")
    private Boolean isActive;
}

