package com.example.bpmn_task.dto.currency;

import javax.xml.bind.annotation.*;

import java.util.List;


@XmlRootElement(name = "ValCurs")
@XmlAccessorType(XmlAccessType.FIELD)
public class ValCurs   {

    @XmlElement(name = "ValType")
    List<ValType> valTypes;

    public ValCurs() {
    }


    public ValCurs(List<ValType> valTypes) {
        this.valTypes = valTypes;
    }


    public List<ValType> getValTypes() {
        return valTypes;
    }



    public void setValTypes(List<ValType> valTypes) {
        this.valTypes = valTypes;
    }

    @Override
    public String toString() {
        return "ValCurs{" +
                "valTypes=" + valTypes +
                '}';
    }
}
