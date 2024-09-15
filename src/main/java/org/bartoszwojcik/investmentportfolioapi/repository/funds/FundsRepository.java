package org.bartoszwojcik.investmentportfolioapi.repository.funds;

import java.util.Optional;
import org.bartoszwojcik.investmentportfolioapi.model.classes.Funds;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FundsRepository extends JpaRepository<Funds, Long>,
        JpaSpecificationExecutor<Funds> {
    Optional<Funds> findBySessionId(String sessionId);
}
