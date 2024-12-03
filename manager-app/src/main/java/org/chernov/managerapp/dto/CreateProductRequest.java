package org.chernov.managerapp.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductRequest {
    @NotNull
    @Size(min = 3, max = 50, message = "Title should be from 3 to 50")
    private String title;
    @Size(min = 3, max = 1000, message = "Title should be from 3 to 1000")
    private String details;
}
