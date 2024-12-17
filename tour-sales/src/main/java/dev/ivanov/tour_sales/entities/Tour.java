package dev.ivanov.tour_sales.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "tours")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tour {
    @Id
    private String id;
    private String title;
    private String description;
    private Instant createdAt;
    private Double price;
    private Instant startAt;
    private Instant finishAt;
    private Integer availableCount;
    private String companyId;
}
