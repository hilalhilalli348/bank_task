package com.example.bpmn_task.utils;


import com.example.bpmn_task.dto.currency.ValCurs;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.atomic.AtomicReference;

// merkezi bankin cari tarixdeki valyuta mezenneleri
public final class CurrencyScanner {

    public static BigDecimal getCurrency(String currencyCode)  {

        RestTemplate restTemplate = new RestTemplate();

        try {
            SSLUtils.turnOffSslChecking();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (KeyManagementException e) {
            throw new RuntimeException(e);
        }

        // https://cbar.az/currencies/31.12.2023.xml -  cari tarix ucun neticeni verir.
        ValCurs valCurs = restTemplate.getForObject("https://cbar.az/currencies/31.12.2023.xml", ValCurs.class);


        AtomicReference<BigDecimal> USDCurrency = new AtomicReference<>(new BigDecimal(0));

        valCurs.getValTypes().forEach(
                valType -> {
                    valType.getValTypeList()
                            .forEach(
                                    valute -> {
                                        if (valute.getCode().equals(currencyCode)) {
                                            USDCurrency.set(new BigDecimal(valute.getValue().toString()));
                                        }

                                    }
                            );
                }
        );


        return  USDCurrency.get();
    }


}
