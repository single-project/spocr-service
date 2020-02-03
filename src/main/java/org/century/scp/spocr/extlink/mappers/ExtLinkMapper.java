package org.century.scp.spocr.extlink.mappers;

import java.util.List;
import org.century.scp.spocr.extlink.models.domain.ExtLink;
import org.century.scp.spocr.extlink.models.dto.ExtLinkView;
import org.century.scp.spocr.extlink.models.dto.RequestForCreateExtLink;
import org.mapstruct.Mapper;

@Mapper
public interface ExtLinkMapper {

  ExtLinkView map(ExtLink entity);

  ExtLink map(RequestForCreateExtLink view);

  List<ExtLinkView> map(List<ExtLink> list);

}
