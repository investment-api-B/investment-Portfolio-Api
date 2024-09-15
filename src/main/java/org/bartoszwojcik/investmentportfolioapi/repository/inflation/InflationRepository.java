package org.bartoszwojcik.investmentportfolioapi.repository.inflation;

import java.util.Optional;
import org.bartoszwojcik.investmentportfolioapi.model.classes.Inflation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface InflationRepository extends JpaRepository<Inflation, Long>,
        JpaSpecificationExecutor<Inflation> {
    Optional<Inflation> findByCountryName(String countryName);
}
