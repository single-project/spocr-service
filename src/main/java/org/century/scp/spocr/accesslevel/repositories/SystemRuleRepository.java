package org.century.scp.spocr.accesslevel.repositories;

import java.util.Optional;
import org.century.scp.spocr.accesslevel.models.SystemRule;
import org.century.scp.spocr.base.repositories.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemRuleRepository extends BaseRepository<SystemRule> {

  Optional<SystemRule> findSystemRuleByName(String name);

}
