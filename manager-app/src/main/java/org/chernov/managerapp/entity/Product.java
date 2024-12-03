package org.chernov.managerapp.entity;


import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    private Integer id;

    private String title;

    private String details;
}
