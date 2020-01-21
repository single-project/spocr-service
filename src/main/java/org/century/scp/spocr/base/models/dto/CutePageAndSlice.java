package org.century.scp.spocr.base.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = {"empty", "first", "last", "pageable", "sort"})
public class CutePageAndSlice<T>  {


}
