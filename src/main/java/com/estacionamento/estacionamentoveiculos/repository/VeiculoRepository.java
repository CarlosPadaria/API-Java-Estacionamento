package com.estacionamento.estacionamentoveiculos.repository;

import com.estacionamento.estacionamentoveiculos.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Integer> {

}
