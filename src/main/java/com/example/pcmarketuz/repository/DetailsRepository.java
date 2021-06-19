package com.example.pcmarketuz.repository;

import com.example.pcmarketuz.entity.Details;
import com.example.pcmarketuz.projection.CustomDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "details", collectionResourceRel = "detailsList",excerptProjection = CustomDetails.class)
public interface DetailsRepository extends JpaRepository<Details,Integer> {
}
