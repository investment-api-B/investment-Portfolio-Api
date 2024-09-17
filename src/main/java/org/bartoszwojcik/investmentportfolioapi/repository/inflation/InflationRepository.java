package org.bartoszwojcik.investmentportfolioapi.repository.inflation;

import java.util.Optional;
import org.bartoszwojcik.investmentportfolioapi.model.classes.Inflation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InflationRepository extends JpaRepository<Inflation, Long>,
        JpaSpecificationExecutor<Inflation> {
    @Query("SELECT y FROM Inflation y "
            + "WHERE y.countryName = :countryName")
    Optional<Inflation> findByCountryName(String countryName);
}
