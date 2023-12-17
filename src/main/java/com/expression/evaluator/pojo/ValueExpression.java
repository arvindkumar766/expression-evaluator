package com.expression.evaluator.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class ValueExpression extends Expression {

    Object value;

    public Object getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String getType() {
        return "value";
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
