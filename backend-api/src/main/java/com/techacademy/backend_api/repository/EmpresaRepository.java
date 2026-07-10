package com.techacademy.backend_api.repository;

import com.techacademy.backend_api.entity.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {
}