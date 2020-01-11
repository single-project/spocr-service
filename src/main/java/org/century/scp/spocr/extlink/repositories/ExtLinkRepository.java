package org.century.scp.spocr.extlink.repositories;

import org.century.scp.spocr.extlink.models.domain.ExtLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ExtLinkRepository
    extends JpaRepository<ExtLink, Long>, JpaSpecificationExecutor<ExtLink> {}
