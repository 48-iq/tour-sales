package dev.ivanov.tour_sales.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "contracts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contract {
    @Id
    private String id;
    private String tourId;
    private String userId;
    private Double price;
}
