package org.century.scp.spocr.counterparty.status.repositories;

import org.century.scp.spocr.base.repositories.BaseRepository;
import org.century.scp.spocr.counterparty.status.models.domain.CounterpartyStatus;
import org.springframework.stereotype.Repository;

@Repository
public interface CounterpartyStatusRepository
    extends BaseRepository<CounterpartyStatus> {

}
