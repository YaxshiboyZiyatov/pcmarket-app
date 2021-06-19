package com.example.pcmarketuz.repository;

import com.example.pcmarketuz.entity.Position;
import com.example.pcmarketuz.projection.CustomPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "position", collectionResourceRel = "positionList",excerptProjection = CustomPosition.class)
public interface PositionRepository extends JpaRepository<Position, Integer> {
}
