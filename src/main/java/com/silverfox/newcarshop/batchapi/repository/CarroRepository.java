package com.silverfox.newcarshop.batchapi.repository;

import com.silverfox.newcarshop.batchapi.model.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Long> {
}
