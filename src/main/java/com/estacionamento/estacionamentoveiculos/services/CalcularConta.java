package com.estacionamento.estacionamentoveiculos.services;

import com.estacionamento.estacionamentoveiculos.model.Veiculo;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;


@Service
class CalcularConta {
    public static Double PRECO_DIAS = 30.0;
    public static Double PRECO_HORAS = 8.0;
    public static Double PRECO_MESES = 300.0;

    static Double getConta1(String dtEntrada, String dtSaida) {

        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        SimpleDateFormat sdf
                = new SimpleDateFormat(
                "dd-MM-yyyy HH:mm");


        try {


            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

            String temp1 = dtEntrada;

            LocalDateTime localDate = LocalDateTime.parse(temp1, formatter);

            String temp11 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(localDate);

            Date d1 = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(temp11);

            String temp2 = dtEntrada;

            LocalDateTime localDate2 = LocalDateTime.parse(temp2, formatter);

            String temp22 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(localDate2);

            Date d2 = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(temp22);

            long difference_In_Time
                    = d2.getTime() - d1.getTime();


            long difference_In_Seconds
                    = (difference_In_Time
                    / 1000)
                    % 60;

            long difference_In_Minutes
                    = (difference_In_Time
                    / (1000 * 60))
                    % 60;

            long difference_In_Hours
                    = (difference_In_Time
                    / (1000 * 60 * 60))
                    % 24;

            long difference_In_Years
                    = (difference_In_Time
                    / (1000l * 60 * 60 * 24 * 365));

            long difference_In_Days
                    = (difference_In_Time
                    / (1000 * 60 * 60 * 24))
                    % 365;


            if (difference_In_Days >= 1 && difference_In_Days < 30) {
                return (difference_In_Days * PRECO_DIAS);
            }
            if (difference_In_Hours >= 1 && difference_In_Days == 0) {
                return (difference_In_Hours * PRECO_HORAS);
            }
            if (difference_In_Days >= 30) {
                Double temp = (double) difference_In_Days;
                temp /= 30;
                temp = Math.ceil(temp);
                return (temp * PRECO_MESES);
            }
            if (difference_In_Minutes == 0 && difference_In_Hours == 0 && difference_In_Days == 0) {
                return PRECO_HORAS;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 0.0;
    }

    public Double getConta(Veiculo veiculo) {
        return getConta1(veiculo.getDtEntrada() + "", veiculo.getDtSaida() + "");
    }


    public static void main(String[] args) {
        System.out.println(getConta1("2022-11-19T17:17", "2022-11-19T17:18"));
    }


}