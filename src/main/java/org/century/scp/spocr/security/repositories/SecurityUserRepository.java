package org.century.scp.spocr.security.repositories;

import java.util.Optional;
import org.century.scp.spocr.security.models.domain.SecurityUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecurityUserRepository extends JpaRepository<SecurityUser, Integer> {
  Optional<SecurityUser> findByLogin(String login);
}
