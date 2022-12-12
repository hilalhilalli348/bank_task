package com.example.bpmn_task.dto.currency;


import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.math.BigDecimal;

@XmlRootElement(name = "Valute")
@XmlAccessorType(XmlAccessType.FIELD)
public class Valute   {
    @XmlElement(name = "Nominal")
    String nominal;
    @XmlElement(name = "Name")
    String name;
    @XmlElement(name = "Value")
    BigDecimal Value;

    @XmlAttribute(name = "Code")
    String code;

    public Valute() {
    }

    public Valute(String nominal, String name, BigDecimal value, String code) {
        this.nominal = nominal;
        this.name = name;
        Value = value;
        this.code = code;
    }

    public String getNominal() {
        return nominal;
    }

    public void setNominal(String nominal) {
        this.nominal = nominal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getValue() {
        return Value;
    }

    public void setValue(BigDecimal value) {
        Value = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Valute{" +
                "nominal='" + nominal + '\'' +
                ", name='" + name + '\'' +
                ", Value=" + Value +
                ", code='" + code + '\'' +
                '}';
    }
}
