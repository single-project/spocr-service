package org.century.scp.spocr.accesslevel.repositories;

import org.century.scp.spocr.accesslevel.models.SystemRule;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemRuleRepository extends CrudRepository<SystemRule, Long> {

}
