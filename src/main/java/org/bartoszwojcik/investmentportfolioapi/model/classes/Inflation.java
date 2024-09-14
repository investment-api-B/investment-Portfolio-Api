package org.bartoszwojcik.investmentportfolioapi.model.classes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SQLDelete(sql = "UPDATE inflation SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
@Table(name = "inflation's")
public class Inflation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private double yearToYear;
    @Column(nullable = false)
    private String countryName;
    @Column(name = "deleted",nullable = false)
    private boolean isDeleted = false;
}
