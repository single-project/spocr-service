package org.century.scp.spocr.shop.models.domain;

import java.util.Map;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.century.scp.spocr.base.converters.MapConverter;
import org.century.scp.spocr.base.models.domain.IdentifiedEntity;
import org.century.scp.spocr.extregsystem.models.domain.ExtRegSystem;

@EqualsAndHashCode
@Data
@Entity
@Table(name = "shop_to_ext_reg_system_props")
@NoArgsConstructor
@AllArgsConstructor
public class ExtRegSystemShopProperties implements IdentifiedEntity {

  @Column(name = "properties")
  @Convert(converter = MapConverter.class)
  Map<String, Object> properties;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "ext_reg_system_id")
  ExtRegSystem extRegSystem;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
}
