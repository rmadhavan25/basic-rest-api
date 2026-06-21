package com.madhavan.basicrestapi.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class DepartmentDTO {

    private String title;
    private boolean isActive;
    private LocalDateTime createdAt;
}
