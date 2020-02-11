package org.century.scp.spocr.base.configs;

import java.util.List;
import net.kaczmarzyk.spring.data.jpa.web.SpecificationArgumentResolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

  @Value("${paging.default.pageSize}")
  private int size;

  @Value("${paging.default.sortField}")
  private String sortField;

  @Value("${paging.default.sortDirection}")
  private String sortDirection;

  private int page = 0;

  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
    PageableHandlerMethodArgumentResolver pageableHandlerMethodArgumentResolver =
        new PageableHandlerMethodArgumentResolver();
    pageableHandlerMethodArgumentResolver.setFallbackPageable(
        PageRequest.of(page, size, Sort.by(Direction.fromString(sortDirection), sortField)));
    argumentResolvers.add(pageableHandlerMethodArgumentResolver);
    argumentResolvers.add(new SpecificationArgumentResolver());
  }
}
