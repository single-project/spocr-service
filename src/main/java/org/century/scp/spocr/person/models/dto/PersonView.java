package org.century.scp.spocr.person.models.dto;

import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.century.scp.spocr.base.models.dto.BaseEntityView;
import org.century.scp.spocr.enumeration.models.dto.EnumerationView;

@Getter
@Setter
@NoArgsConstructor
public class PersonView extends BaseEntityView {

  private String name;
  private String lastName;
  private String firstName;
  private String patronymic;
  private Date birthDate;
  private String birthPlace;
  private EnumerationView docType;
  private String docSeriesNumber;
  private String inn;
  private EnumerationView citizenship;
  private EnumerationView gender;
  private String email;
  private String phones;

}
