package io.tsuderia.km124_api.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "buy_out")
public class BuyOutEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "referenced_document_id", nullable = false)
    private DocumentEntity documentReference;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private EmployeeEntity employee;

    private float amount;

    @Column(name = "created_at")
    private LocalDate createdAt = LocalDate.now();
    @Column(name = "updated_at")
    private LocalDate updatedAt = LocalDate.now();
}
