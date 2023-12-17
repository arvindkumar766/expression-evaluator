package com.expression.evaluator.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




public class VariableExpression extends Expression{

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String name;

    @Override
    public String getType() {
        return "variable";
    }

    @Override
    public String toString(){
        return name;
    }
}
