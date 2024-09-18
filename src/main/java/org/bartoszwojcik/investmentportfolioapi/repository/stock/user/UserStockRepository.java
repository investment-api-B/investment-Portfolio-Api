package org.bartoszwojcik.investmentportfolioapi.repository.stock.user;

import java.util.List;
import java.util.Optional;
import org.bartoszwojcik.investmentportfolioapi.model.classes.UserStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStockRepository extends JpaRepository<UserStock, Long>,
        JpaSpecificationExecutor<UserStock> {
    @Query("SELECT us FROM UserStock us "
            + "WHERE us.user.id = :userId")
    List<UserStock> findByUserId(Long userId);

    @Query("SELECT us FROM UserStock us "
            + "WHERE us.stock.id = :stockId")
    Optional<UserStock> findByStockId(Long stockId);
}
