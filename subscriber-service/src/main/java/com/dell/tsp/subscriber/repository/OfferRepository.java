package com.dell.tsp.subscriber.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dell.tsp.subscriber.entity.OfferEntity;

@Repository
public interface OfferRepository extends JpaRepository<OfferEntity, Long>{

}

