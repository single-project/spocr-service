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
    return PaymentDetails.builder()
        .id(paymentDetailsId)
        .bic("bic")
        .bank("bank")
        .active(true)
        .version((long) 0)
        .paymentAccount("paymentAccount")
        .correspondingAccount("correspondingAccount")
        .build();
  }

  public RequestForCreateCounterparty createCounterpartyRequestForCreate() {
    int randomInt = new Random().nextInt(9);
    return RequestForCreateCounterparty.builder()
        .name("name" + randomInt)
        .legalType(createLegalTypeView())
        .legalRekv(createLegalRekvView())
        .paymentDetails(createPaymentDetailsView())
        .suggestion(createSuggestion())
        .build();
  }

  public RequestForUpdateCounterparty createCounterpartyRequestForUpdate(long counterpartyId) {
    List<String> updatedFields = Arrays.asList("name", "suggestion");
    return RequestForUpdateCounterparty.builder()
        .id(counterpartyId)
        .name("name" + counterpartyId)
        .version((long) 0)
        .active(true)
        .legalType(createLegalTypeView())
        .legalRekv(createLegalRekvView())
        .paymentDetails(createPaymentDetailsView())
        .suggestion(createSuggestion())
        .updatedFields(updatedFields)
        .build();
  }

  public LegalTypeView createLegalTypeView() {
    return LegalTypeView.builder().name("ИП").active(true).build();
  }

  public LegalRekvView createLegalRekvView() {
    int randomInt = new Random().nextInt(9);
    return LegalRekvView.builder()
        .shortName("shortname" + randomInt)
        .fullName("fullname" + randomInt)
        .inn("11122233" + randomInt)
        .kpp("222333444" + randomInt)
        .ogrn("ogrn" + randomInt)
        .ogrnDate("0" + randomInt + ".01.2014")
        .okonh("okonh" + randomInt)
        .okpo("okpo" + randomInt)
        .build();
  }

  public PaymentDetailsView createPaymentDetailsView() {
    int randomInt = new Random().nextInt(9);
    return PaymentDetailsView.builder()
        .bic("bic" + randomInt)
        .bank("bank" + randomInt)
        .paymentAccount("paymentAccount" + randomInt)
        .correspondingAccount("correspondingAccount" + randomInt)
        .build();
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
