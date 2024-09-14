package org.bartoszwojcik.investmentportfolioapi.repository.stock.user;

import org.bartoszwojcik.investmentportfolioapi.model.classes.UserStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStockRepository extends JpaRepository<UserStock, Long>,
        JpaSpecificationExecutor<UserStock> {
}
