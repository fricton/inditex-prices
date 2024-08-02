package com.fricton.inditex.prices.infrastructure.db.h2.repositories;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fricton.inditex.prices.infrastructure.db.h2.entities.PriceEntity;

@Repository
public interface JpaPriceRepository extends JpaRepository<PriceEntity, Integer> {

	@Query("SELECT p FROM PriceEntity p  WHERE p.brandId = :brandId  AND p.productId = :productId AND p.curr = :curr "
			+ "AND :applicationDate BETWEEN p.startDate AND p.endDate " + "ORDER BY p.priority DESC")
	List<PriceEntity> findPrices(@Param("brandId") Integer brandId, @Param("productId") Integer productId,
			@Param("curr") String curr, @Param("applicationDate") Instant applicationDate);

	
	@Query("SELECT p FROM PriceEntity p WHERE p.productId = :productId AND p.brandId = :brandId ORDER BY p.priority DESC")
	List<PriceEntity> findPrices1(@Param("brandId") Integer brandId, @Param("productId") Integer productId);
}
