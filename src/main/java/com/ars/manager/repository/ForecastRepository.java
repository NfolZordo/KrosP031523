package com.ars.manager.repository;

import com.ars.manager.domain.Forecast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface ForecastRepository extends JpaRepository<Forecast, Long> {

    @Query("SELECT DISTINCT f FROM Forecast f JOIN Document d JOIN Company c WHERE c.uuid = :companyId")
    List<Forecast> findByAllByCompanyId(@Param("companyId") String companyId);

    Optional<Forecast> findByUuid(String uuid);
}
