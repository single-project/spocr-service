package unit.org.century.scp.spocr.mappers.factories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import org.century.scp.spocr.counterparty.models.domain.Counterparty;
import org.century.scp.spocr.counterparty.models.dto.LegalRekvView;
import org.century.scp.spocr.counterparty.models.dto.RequestForCreateCounterparty;
import org.century.scp.spocr.counterparty.models.dto.RequestForUpdateCounterparty;
import org.century.scp.spocr.legaltype.models.domain.LegalType;
import org.century.scp.spocr.legaltype.models.dto.LegalTypeView;
import org.century.scp.spocr.paymentdetails.models.domain.PaymentDetails;
import org.century.scp.spocr.paymentdetails.models.dto.PaymentDetailsView;

public class CounterpartyFactoryService {

  public List<Counterparty> createCounterpartyList(int size) {
    List<Counterparty> counterparties = new ArrayList<>();
    for (int i = 1; i <= size; i++) {
      counterparties.add(createCounterparty(i));
    }
    return counterparties;
  }

  public Counterparty createCounterparty(long id) {

    LegalType legalType = createLegalType(id);
    PaymentDetails paymentDetails = createPaymentDetails(id);

    Counterparty counterparty = new Counterparty();
    // TODO : fix it
    counterparty.setId(id);
    counterparty.setName("name" + id);
    counterparty.setActive(true);
    counterparty.setLegalType(legalType);
    counterparty.setPaymentDetails(paymentDetails);
    counterparty.setSuggestion(null);
    counterparty.setFullName("fullname" + id);
    counterparty.setInn("11122233" + id);
    counterparty.setKpp("222333444" + id);
    counterparty.setOgrn("ogrn" + id);
    counterparty.setOgrnDate("03.01.2014");
    counterparty.setOkonh("okonh" + id);
    counterparty.setOkpo("okpo" + id);
    return counterparty;
  }

  public LegalType createLegalType(long counterpartyId) {
    long legalTypeId = counterpartyId * 100;

    LegalType lt = new LegalType();
    lt.setId(legalTypeId);
    lt.setName("ИП");
    lt.setActive(true);
    lt.setOkpfId("50101");
    lt.setOkpfType("2014");
    lt.setOkfpShortName("ИП");
    lt.setOkfpFullName("Индивидуальный предприниматель");
    return lt;
  }

  public PaymentDetails createPaymentDetails(long counterpartyId) {
    long paymentDetailsId = counterpartyId * 10;
    PaymentDetails paymentDetails = new PaymentDetails();
    paymentDetails.setId(paymentDetailsId);
    paymentDetails.setBic("bic");
    paymentDetails.setBank("bank");
    paymentDetails.setActive(true);
    paymentDetails.setVersion((long) 0);
    paymentDetails.setPaymentAccount("paymentAccount");
    paymentDetails.setCorrespondingAccount("correspondingAccount");
    return paymentDetails;
  }

  public RequestForCreateCounterparty createCounterpartyRequestForCreate() {
    int randomInt = new Random().nextInt(9);
    RequestForCreateCounterparty createCounterparty = new RequestForCreateCounterparty();
    createCounterparty.setName("name" + randomInt);
    createCounterparty.setLegalType(createLegalTypeView());
    createCounterparty.setLegalRekv(createLegalRekvView());
    createCounterparty.setPaymentDetails(createPaymentDetailsView());
    createCounterparty.setSuggestion(createSuggestion());
    return createCounterparty;
  }

  public RequestForUpdateCounterparty createCounterpartyRequestForUpdate(long counterpartyId) {
    List<String> updatedFields = Arrays.asList("name", "suggestion");
    RequestForUpdateCounterparty requestForUpdateCounterparty = new RequestForUpdateCounterparty();
    requestForUpdateCounterparty.setId(counterpartyId);
    requestForUpdateCounterparty.setName("name" + counterpartyId);
    requestForUpdateCounterparty.setVersion((long) 0);
    requestForUpdateCounterparty.setActive(true);
    requestForUpdateCounterparty.setLegalType(createLegalTypeView());
    requestForUpdateCounterparty.setLegalRekv(createLegalRekvView());
    requestForUpdateCounterparty.setPaymentDetails(createPaymentDetailsView());
    requestForUpdateCounterparty.setSuggestion(createSuggestion());
    requestForUpdateCounterparty.setUpdatedFields(updatedFields);
    return requestForUpdateCounterparty;
  }

  public LegalTypeView createLegalTypeView() {
    return new LegalTypeView("ИП");
  }

  public LegalRekvView createLegalRekvView() {
    int randomInt = new Random().nextInt(9);
    LegalRekvView legalRekvView = new LegalRekvView();
    legalRekvView.setShortName("shortname" + randomInt);
    legalRekvView.setFullName("fullname" + randomInt);
    legalRekvView.setInn("11122233" + randomInt);
    legalRekvView.setKpp("222333444" + randomInt);
    legalRekvView.setOgrn("ogrn" + randomInt);
    legalRekvView.setOgrnDate("0" + randomInt + ".01.2014");
    legalRekvView.setOkonh("okonh" + randomInt);
    legalRekvView.setOkpo("okpo" + randomInt);
    return legalRekvView;
  }

  public PaymentDetailsView createPaymentDetailsView() {
    int randomInt = new Random().nextInt(9);
    PaymentDetailsView view = new PaymentDetailsView();
    view.setBic("bic" + randomInt);
    view.setBank("bank" + randomInt);
    view.setPaymentAccount("paymentAccount" + randomInt);
    view.setCorrespondingAccount("correspondingAccount" + randomInt);
    return view;
  }

  public HashMap createSuggestion() {
    int randomInt = new Random().nextInt(9);
    HashMap suggestion = new HashMap();
    HashMap opf = new HashMap();
    opf.put("type", "2014");
    opf.put("code", "50102");
    opf.put("full", "Индивидуальный предприниматель");
    opf.put("short", "ИП");
    suggestion.put("value", "value" + randomInt);
    suggestion.put("opf", opf);
    return suggestion;
  }
}
