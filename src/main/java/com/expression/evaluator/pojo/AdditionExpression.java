package com.expression.evaluator.pojo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;


public class AdditionExpression extends Expression {
    private List<Expression> operands;

    public List<Expression> getOperands() {
        return operands;
    }

    public void setOperands(List<Expression> operands) {
        this.operands = operands;
    }

    @Override
    public String getType() {
        return "addition";
    }
}