package com.example.dispatch.Repository;

import com.example.dispatch.Entity.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VehicleRepository extends JpaRepository<VehicleEntity, Long> {

    List<VehicleEntity> findByStatus(String status);
}
