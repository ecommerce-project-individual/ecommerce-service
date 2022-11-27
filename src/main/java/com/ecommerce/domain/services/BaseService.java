package com.ecommerce.domain.services;


import com.ecommerce.app.dtos.DTO;
import com.ecommerce.app.dtos.FilterDto;
import com.ecommerce.app.responses.CustomPage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public interface BaseService<T, S> {
  CustomPage<T> findAll(Pageable pageable);

  T findById(HttpServletRequest request, S id);

  @Transactional(rollbackFor = Exception.class)
  T create(HttpServletRequest request, DTO dto);

  @Transactional(rollbackFor = Exception.class)
  T update(HttpServletRequest request, S id, DTO dto);

  T update(HttpServletRequest request, Long id, DTO dto);

  @Transactional(rollbackFor = Exception.class)
  boolean delete(HttpServletRequest request, S id);

  Page<T> findAllByFilter(FilterDto<T> dto, Pageable pageable);

  List<T> findAllByFilter(HttpServletRequest request);
}
