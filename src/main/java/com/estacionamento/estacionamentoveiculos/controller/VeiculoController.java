package com.estacionamento.estacionamentoveiculos.controller;


import com.estacionamento.estacionamentoveiculos.model.Veiculo;
import com.estacionamento.estacionamentoveiculos.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.estacionamento.estacionamentoveiculos.services.VeiculoService;

import java.util.List;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private VeiculoService veiculoService;
    @GetMapping
    public List<Veiculo> list() {
        return veiculoRepository.findAll();
    }

    @PostMapping
    public void save(@RequestBody Veiculo veiculo){

        veiculoService.createVeiculo(veiculo);
    }
    @GetMapping("/{id}")
    public Object getVeiculo(@PathVariable Integer id){
            return veiculoService.getVeiculo(id);
    }

    @PutMapping("/{id}")
    public void updateVeiculo(@PathVariable Integer id , @RequestBody Veiculo veiculo){

        veiculoService.updateVeiculo(veiculo, id);
    }

    @PutMapping("/{id}/calcularconta")
    public void updateCalcularConta(@PathVariable Integer id){

        veiculoService.updateContaSaida(id);
    }

    @DeleteMapping("/{id}")
    public void deleteVeiculo(@PathVariable Integer id){
        veiculoService.deleteVeiculo(id);
    }

}
