package com.ecommerce.domain.services.impl.business;

import com.ecommerce.app.dtos.DTO;
import com.ecommerce.app.dtos.FilterDto;
import com.ecommerce.app.dtos.impl.ProductRatingDto;
import com.ecommerce.app.responses.CustomPage;
import com.ecommerce.app.responses.ProductRatingResponseList;
import com.ecommerce.domain.entities.business.Product;
import com.ecommerce.domain.entities.business.ProductRating;
import com.ecommerce.domain.services.BaseService;
import com.ecommerce.domain.services.impl.BaseAbtractService;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class ProductRatingService extends BaseAbtractService implements BaseService<ProductRating, Long> {
    @Override
    public CustomPage<ProductRating> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public ProductRating findById(HttpServletRequest request, Long id) {
        return getProductRatingById(id);
    }

    @Override
    public ProductRating create(HttpServletRequest request, DTO dto) {
        ProductRatingDto productRatingDto = modelMapper.map(dto, ProductRatingDto.class);
        ProductRating productRating = ProductRating.builder()
                .product(getProductById(productRatingDto.getProductId()))
                .user(getUser())
                .content(productRatingDto.getContent())
                .numberStar(productRatingDto.getNumberStar())
                .build();

        return productRatingRepository.save(productRating);
    }

    @Override
    public ProductRating update(HttpServletRequest request, Long id, DTO dto) {
        ProductRatingDto productRatingDto = modelMapper.map(dto, ProductRatingDto.class);
        ProductRating productRating = getProductRatingById(id);
        productRating.setContent(productRatingDto.getContent());
        productRating.setNumberStar(productRatingDto.getNumberStar());

        return productRatingRepository.save(productRating);
    }

    @Override
    public boolean delete(HttpServletRequest request, Long id) {
        ProductRating productRating = getProductRatingById(id);
        productRatingRepository.delete(productRating);

        return true;
    }

    @Override
    public Page<ProductRating> findAllByFilter(FilterDto<ProductRating> dto, Pageable pageable) {
        return null;
    }

    @Override
    public List<ProductRating> findAllByFilter(HttpServletRequest request) {
        return null;
    }

    public List<ProductRatingResponseList> getListCmtByProductId(Long id){
        List<ProductRatingResponseList> responseLists = new ArrayList<>();
        Product product = productRepository.findById(id).orElse(null);
        if (product != null) {
            List<ProductRating> productRatings = productRatingRepository.findAllByProduct(product);
            for (ProductRating p: productRatings
                 ) {
                ProductRatingResponseList productRatingResponseList = new ProductRatingResponseList();
                productRatingResponseList.setName(p.getUser().getFullName());
                productRatingResponseList.setTime(p.getCreated_at());
                productRatingResponseList.setContent(p.getContent());
                productRatingResponseList.setNumber_star(p.getNumberStar());
                responseLists.add(productRatingResponseList);
            }
            return responseLists;
        }
        return null;
    }
}
