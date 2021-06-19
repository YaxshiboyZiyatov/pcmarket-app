package com.example.pcmarketuz.repository;

import com.example.pcmarketuz.entity.Supplier;
import com.example.pcmarketuz.projection.CustomSupplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "supplier", collectionResourceRel = "supplierList",excerptProjection = CustomSupplier.class)
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
}
