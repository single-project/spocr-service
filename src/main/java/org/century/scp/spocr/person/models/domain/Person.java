package org.century.scp.spocr.person.models.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.century.scp.spocr.base.models.domain.DomainEntity;
import org.century.scp.spocr.enumeration.models.domain.Enumeration;

@EqualsAndHashCode
@Data
@Entity
@Table(name = "persons")
@NoArgsConstructor
public class Person implements DomainEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "patronymic")
  private String patronymic;

  @Column(name = "birth_date")
  private Date birthDate;

  @Column(name = "birth_place")
  private String birthPlace;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "doc_type_id")
  private Enumeration docType;

  @Column(name = "doc_series_number")
  private String docSeriesNumber;

  @Column(name = "inn")
  private String inn;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "citizenship_id")
  private Enumeration citizenship;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "gender_id")
  private Enumeration gender;

  @Column(name = "email")
  private String email;

  @Column(name = "phones")
  private String phones;

  public Person(String lastName, String firstName, String patronymic) {
    this.lastName = lastName;
    this.firstName = firstName;
    this.patronymic = patronymic;
  }
}
