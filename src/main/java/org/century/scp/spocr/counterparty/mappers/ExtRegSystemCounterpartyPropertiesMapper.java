package org.century.scp.spocr.counterparty.mappers;

import org.century.scp.spocr.counterparty.models.domain.ExtRegSystemCounterpartyProperties;
import org.century.scp.spocr.counterparty.models.dto.ExtRegSystemCounterpartyPropertiesView;
import org.century.scp.spocr.extregsystem.mappers.ExtRegSystemMapper;
import org.mapstruct.Mapper;

@Mapper(uses = ExtRegSystemMapper.class)
public interface ExtRegSystemCounterpartyPropertiesMapper {

  ExtRegSystemCounterpartyProperties map(ExtRegSystemCounterpartyPropertiesView view);

  ExtRegSystemCounterpartyPropertiesView map(ExtRegSystemCounterpartyProperties entity);
}
