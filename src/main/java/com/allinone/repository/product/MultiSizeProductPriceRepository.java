package com.allinone.repository.product;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.allinone.model.product.MultiSizeProductPrice;
@Repository
public interface MultiSizeProductPriceRepository extends CrudRepository<MultiSizeProductPrice, Long> {

}
