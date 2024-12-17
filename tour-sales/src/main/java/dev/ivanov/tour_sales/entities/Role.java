package dev.ivanov.tour_sales.entities;

import jakarta.persistence.Id;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {
    @Id
    private String name;
}
