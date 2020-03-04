package org.century.scp.spocr.counterparty.services;

import java.util.Set;
import javax.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.century.scp.spocr.base.repositories.BaseRepository;
import org.century.scp.spocr.base.services.BaseService;
import org.century.scp.spocr.contact.models.domain.Contact;
import org.century.scp.spocr.counterparty.models.domain.Counterparty;
import org.century.scp.spocr.enumeration.models.domain.Enumeration;
import org.century.scp.spocr.owner.models.domain.Owner;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CounterpartyService extends BaseService<Counterparty> {

  public CounterpartyService(EntityManager entityManager, BaseRepository<Counterparty> repository) {
    super(Counterparty.class, entityManager, repository);
  }

  @Override
  public void refresh(Counterparty counterparty) {
    if (counterparty.getParent() != null) {
      counterparty.setParent(getReference(counterparty.getParent(), Counterparty.class));
    }

    if (counterparty.getLegalType() != null) {
      counterparty.setLegalType(getReference(counterparty.getLegalType(), Enumeration.class));
    }

    if (counterparty.linkedWithStatuses()) {
      Set<Enumeration> statuses = getReferences(counterparty.getStatuses(), Enumeration.class);
      counterparty.setStatuses(statuses);
    }

    if (counterparty.linkedWithPaymentTypes()) {
      Set<Enumeration> paymentTypes = getReferences(counterparty.getPaymentTypes(),
          Enumeration.class);
      counterparty.setPaymentTypes(paymentTypes);
    }

    if (counterparty.getOwner() != null) {
      counterparty.setOwner(getReference(counterparty.getOwner(), Owner.class));
    }

    if (counterparty.linkedWithContacts()) {
      counterparty.setContacts(getReferences(counterparty.getContacts(), Contact.class));
    }
  }
}
