package io.tsuderia.km124_api.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "document")
public class DocumentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "document_type_id")
    private DocumentTypeEntity documentType;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private EmployeeEntity employee;

    @ManyToOne
    @JoinColumn(name = "gold_purity_id")
    private GoldPurityEntity goldPurity;

    @ManyToOne
    @JoinColumn(name = "interest_rate_id")
    private InterestRateEntity interestRate;

    private List<String> items = new ArrayList<>();

    private float weight;
    private float weightOfGems;
    private float evaluation;
    private float amount;

    @Column(name = "commission_fee")
    private float commissionFee;

    @Column(name = "date_of_termination")
    private LocalDate dateOfTermination = LocalDate.now().plusDays(60);

    private Boolean status;
    private Boolean isBuyOut;

    @Column(name = "created_at")
    private LocalDate createdAt = LocalDate.now();

    @Column(name = "updated_at")
    private LocalDate updatedAt = LocalDate.now();

}
