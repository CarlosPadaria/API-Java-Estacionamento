package com.estacionamento.estacionamentoveiculos.services;

import com.estacionamento.estacionamentoveiculos.model.Veiculo;
import com.estacionamento.estacionamentoveiculos.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;

@Service
public class VeiculoService {
    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private CalcularConta calcularConta;

    @Transactional
    public Veiculo[] getVeiculo(Integer id) {
        try {
            Veiculo arr[] = {veiculoRepository.findById(id).get()};
            return arr;
        } catch (NoSuchElementException e) {
            Veiculo arr[] = {};
            return arr;
        }

    }

    @Transactional
    public void updateVeiculo(Veiculo veiculoAtualizar, Integer id) {
        Veiculo veiculo = veiculoRepository.findById(id).get();
        veiculo.setModelo(veiculoAtualizar.getModelo());
        veiculo.setCor(veiculoAtualizar.getCor());
        veiculo.setPlaca(veiculoAtualizar.getPlaca());
        veiculoRepository.save(veiculo);
    }

    @Transactional
    public void updateContaSaida(Integer id) {
        Veiculo veiculo = veiculoRepository.findById(id).get();

        veiculo.setConta(CalcularConta.getConta1(veiculo.getDtEntrada() + "", veiculo.getDtSaida() + ""));
        veiculo.setDtSaida(LocalDateTime.now());
        veiculoRepository.save(veiculo);
    }

    @Transactional
    public HttpStatus createVeiculo(Veiculo veiculo) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formatado = now.format(formatter);
        LocalDateTime dateTime = LocalDateTime.parse(formatado, formatter);
        veiculo.setDtEntrada(dateTime);


        veiculoRepository.save(veiculo);
        return HttpStatus.CREATED;


    }

    @Transactional
    public void deleteVeiculo(Integer id) {

        veiculoRepository.deleteById(id);
    }
}
