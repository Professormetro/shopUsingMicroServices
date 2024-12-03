package org.chernov.managerapp.dto;


import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductRequest {
    @NonNull
    @Size(min = 3, max = 50)
    private String title;
    @Size(min = 3, max = 1000)
    private String details;
}
