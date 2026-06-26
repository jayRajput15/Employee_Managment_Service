package com.example.EmployeeOrg.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {

    private Long id;

    @NotBlank(message = "Department title cannot be empty")
    @Size(min = 2,max = 30,message = "Department title cannot be more than 30 and less than 2")
    private String title;

    @AssertTrue(message = "Department should be active")
    @JsonProperty("isActive")
    private boolean isActive;

    @PastOrPresent(message = "Created date cannot be of future")
    private LocalDateTime createdAt;
}
