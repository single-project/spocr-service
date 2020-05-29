package org.century.scp.spocr.draft.mappers;

import static org.springframework.security.core.context.SecurityContextHolder.getContext;

import java.time.Instant;
import org.century.scp.spocr.draft.models.domain.Draft;
import org.century.scp.spocr.draft.models.dto.DraftView;
import org.century.scp.spocr.security.models.domain.SecurityUser;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;

@Mapper
public abstract class DraftMapper {

  @Mapping(target = "ts", ignore = true)
  @Mapping(target = "username", ignore = true)
  public abstract Draft map(DraftView view);

  public abstract DraftView map(Draft entity);

  public Page<DraftView> map(Page<Draft> page) {
    return page.map(this::map);
  }

  @AfterMapping
  void fillAdditionFields(DraftView view, @MappingTarget Draft entity) {
    if (entity.getTs() == null) {
      entity.setTs(Instant.now());
    }
    entity.setUsername(getCurrentUserLogin());
  }

  private String getCurrentUserLogin() {
    return ((SecurityUser) getContext().getAuthentication().getPrincipal()).getLogin();
  }
}
