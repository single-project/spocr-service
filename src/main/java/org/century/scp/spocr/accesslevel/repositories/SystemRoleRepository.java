package org.century.scp.spocr.accesslevel.repositories;

import org.century.scp.spocr.accesslevel.models.SystemRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemRoleRepository extends JpaRepository<SystemRole, Long> {

}
