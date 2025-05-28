package io.tsuderia.km124_api.data.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "cash_disbursement_order")
public class CashDisbursementOrderEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private float amount;

    @Nullable
    private String description;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = true)
    private EmployeeEntity employee;

    @Column(name = "created_at")
    private LocalDate createdAt = LocalDate.now();

    @Column(name = "updated_at")
    private LocalDate updatedAt = LocalDate.now();
}
