package org.century.scp.spocr.base.repositories;

import org.century.scp.spocr.base.models.domain.DomainEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<T extends DomainEntity>
    extends JpaRepository<T, Long>, JpaSpecificationExecutor<T> {}
