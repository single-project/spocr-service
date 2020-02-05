package unit.org.century.scp.spocr.mappers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import org.century.scp.spocr.counterparty.models.domain.Counterparty;
import org.century.scp.spocr.counterparty.models.dto.LegalRekvView;
import org.century.scp.spocr.counterparty.models.dto.RequestForCreateCounterparty;
import org.century.scp.spocr.legaltype.models.domain.LegalType;
import org.century.scp.spocr.legaltype.models.dto.LegalTypeView;
import org.century.scp.spocr.paymentdetails.models.domain.PaymentDetails;
import org.century.scp.spocr.paymentdetails.models.dto.PaymentDetailsView;

public class SpringMappersService {

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

    return Counterparty.builder()
        .id(id)
        .name("name" + id)
        .active(true)
        .legalType(legalType)
        .paymentDetails(paymentDetails)
        .suggestion(null)
        .fullName("fullname" + id)
        .inn("11122233" + id)
        .kpp("222333444" + id)
        .ogrn("ogrn" + id)
        .ogrnDate("03.01.2014")
        .okonh("okonh" + id)
        .okpo("okpo" + id)
        .build();
  }

  public LegalType createLegalType(long counterpartyId) {
    long legalTypeId = counterpartyId * 100;

    return LegalType.builder()
        .id(legalTypeId)
        .name("ИП")
        .active(true)
        .okpfId("50101")
        .okpfType("2014")
        .okfpShortName("ИП")
        .okfpFullName("Индивидуальный предприниматель")
        .build();
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
