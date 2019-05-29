package com.dell.tsp.subscriber.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dell.tsp.subscriber.entity.ServiceGroupEntity;

@Repository
public interface ServiceGroupRepository extends JpaRepository<ServiceGroupEntity, Long>{

}

