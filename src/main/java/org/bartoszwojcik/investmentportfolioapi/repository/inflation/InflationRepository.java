package org.bartoszwojcik.investmentportfolioapi.repository.inflation;

import org.bartoszwojcik.investmentportfolioapi.model.classes.Inflation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface InflationRepository extends JpaRepository<Inflation, Long>,
        JpaSpecificationExecutor<Inflation> {
}
