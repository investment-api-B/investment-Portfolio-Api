package org.bartoszwojcik.investmentportfolioapi.repository.role;

import java.util.Optional;
import org.bartoszwojcik.investmentportfolioapi.model.classes.Role;
import org.bartoszwojcik.investmentportfolioapi.model.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RoleRepository extends JpaRepository<Role, Integer>,
        JpaSpecificationExecutor<Role> {
    Optional<Role> findByName(RoleName name);
}
