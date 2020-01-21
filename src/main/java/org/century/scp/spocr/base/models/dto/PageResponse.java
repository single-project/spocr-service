package org.century.scp.spocr.base.models.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.century.scp.spocr.base.models.domain.BaseEntity;
import org.springframework.data.domain.Page;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PageResponse<T extends BaseEntity, K extends BaseEntityView> {
  private List<K> content;
  private int totalPages;
  private int totalElements;
  private int numberOfElements;
  private int size;
  private int page;
  private String sort;

  public PageResponse(Page<T> page) {
    this.content = map(page.getContent());
    this.totalPages = page.getTotalPages();
    this.totalElements = page.getNumberOfElements();
    this.numberOfElements = page.getNumberOfElements();
    this.size = page.getSize();
    this.page = page.getNumber();
    this.sort = page.getSort().toString();
  }

  private List<K> map(List<T> content) {
    List<K> result = new ArrayList<>();
    content.forEach(obj->result.add((K) obj.map()));
    return result;
  }

  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  public class Sort {
    private String field;
    private String direction;
  }
}
