package org.century.scp.spocr.counterparty.models.dto;

import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.century.scp.spocr.base.models.dto.BaseEntityListView;
import org.century.scp.spocr.base.models.dto.BaseEntityView;
import org.century.scp.spocr.contact.models.dto.ContactView;
import org.century.scp.spocr.enumeration.models.dto.EnumerationView;
import org.century.scp.spocr.owner.models.dto.OwnerView;
import org.century.scp.spocr.paymentdetails.models.dto.PaymentDetailsView;
import org.century.scp.spocr.person.models.dto.PersonView;

@Getter
@Setter
@NoArgsConstructor
public class CounterpartyView extends BaseEntityView {

  private String name;
  private EnumerationView legalType;
  private LegalRekvView legalRekv;
  private Set<PaymentDetailsView> paymentDetails;
  private BaseEntityListView parent;
  private Set<EnumerationView> statuses;
  private Set<EnumerationView> paymentTypes;
  private PersonView personRekv;
  private OwnerView owner;
  private String comment;
  private Boolean noVat;
  private Set<ContactView> contacts;
  private ExtRegSystemCounterpartyPropertiesView extRegSystemProperties;

}
