package dev.ivanov.tour_sales.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

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
    private Double price;
    private LocalDate startAt;
    private LocalDate finishAt;
    private Integer availableCount;
    private String companyId;
}
