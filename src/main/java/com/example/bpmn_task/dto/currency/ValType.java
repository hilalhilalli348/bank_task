package com.example.bpmn_task.dto.currency;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.List;


@XmlRootElement(name = "ValType")
@XmlAccessorType(XmlAccessType.FIELD)
public class ValType  {

    @XmlElement(name = "Valute")
    List<Valute> valTypeList;

    public ValType() {
    }

    public ValType(List<Valute> valTypeList) {
        this.valTypeList = valTypeList;
    }


    public List<Valute> getValTypeList() {
        return valTypeList;
    }

    public void setValTypeList(List<Valute> valTypeList) {
        this.valTypeList = valTypeList;
    }

    @Override
    public String toString() {
        return "ValType{" +
                "valTypeList=" + valTypeList +
                '}';
    }
}
