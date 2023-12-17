package com.expression.evaluator.pojo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;



public class ConcatenateExpression extends Expression{
    private List<Expression> values;

    public List<Expression> getValues() {
        return values;
    }

    public void setValues(List<Expression> values) {
        this.values = values;
    }

    @Override
    public String getType() {
        return "concatenate";
    }

//    @Override
//    public String toString() {
//        return values.stream();
//    }
// getters, setters, and constructors
}
