package com.estacionamento.estacionamentoveiculos.model;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "tab_veiculo")
public class Veiculo {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE

           // , generator = "sequence_id_veiculo"
    )
    Integer id;

    @Column(length = 50, nullable = false)
    String placa;

    @Column(length = 50, nullable = false)
    String modelo;

    @Column(length = 50, nullable = false)
    String cor;
    @Column(nullable = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    LocalDateTime dtSaida;
    @Column()
    // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
   // @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    LocalDateTime dtEntrada;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Veiculo veiculo = (Veiculo) o;
        return Objects.equals(id, veiculo.id) && Objects.equals(placa, veiculo.placa) && Objects.equals(modelo, veiculo.modelo) && Objects.equals(cor, veiculo.cor) && Objects.equals(dtSaida, veiculo.dtSaida) && Objects.equals(dtEntrada, veiculo.dtEntrada) && Objects.equals(conta, veiculo.conta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, placa, modelo, cor, dtSaida, dtEntrada, conta);
    }

    @Column()
    Double conta;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public LocalDateTime getDtEntrada() {
        return dtEntrada;
    }

    public void setDtEntrada(LocalDateTime dtEntrada) {
        this.dtEntrada = dtEntrada;
    }

    public LocalDateTime getDtSaida() {
        return dtSaida;
    }

    public void setDtSaida(LocalDateTime dtSaida) {
        this.dtSaida = dtSaida;
    }

    public Double getConta() {
        return conta;
    }

    public void setConta(Double conta) {
        this.conta = conta;
    }

    public Veiculo(Integer id, String placa, String modelo, String cor, LocalDateTime dtEntrada, LocalDateTime dtSaida, Double conta) {
        this.id = id;
        this.placa = placa;
        this.modelo = modelo;
        this.cor = cor;
        this.dtEntrada = dtEntrada;
        this.dtSaida = dtSaida;
        this.conta = conta;
    }

    @Override
    public String toString() {
        return "Veiculo{" +
                "id=" + id +
                ", placa='" + placa + '\'' +
                ", modelo='" + modelo + '\'' +
                ", cor='" + cor + '\'' +
                ", dtSaida=" + dtSaida +
                ", dtEntrada=" + dtEntrada +
                ", conta=" + conta +
                '}';
    }

    public Veiculo(){

    }
}
