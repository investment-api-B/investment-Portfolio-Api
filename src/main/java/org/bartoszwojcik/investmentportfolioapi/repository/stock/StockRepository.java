package org.bartoszwojcik.investmentportfolioapi.repository.stock;

import org.bartoszwojcik.investmentportfolioapi.model.classes.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long>,
        JpaSpecificationExecutor<Stock> {
}
