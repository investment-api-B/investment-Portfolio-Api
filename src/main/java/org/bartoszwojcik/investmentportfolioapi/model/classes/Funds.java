package org.bartoszwojcik.investmentportfolioapi.model.classes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bartoszwojcik.investmentportfolioapi.model.enums.PaymentType;
import org.bartoszwojcik.investmentportfolioapi.model.enums.Status;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "funds")
@Getter
@Setter
@NoArgsConstructor
@SQLDelete(sql = "UPDATE funds SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Funds {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentType type;

    @Column(nullable = false)
    private String sessionUrl;

    @Column(nullable = false)
    private String sessionId;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amountToPay;

    @Column(name = "deleted", nullable = false)
    private boolean isDeleted = false;
}
