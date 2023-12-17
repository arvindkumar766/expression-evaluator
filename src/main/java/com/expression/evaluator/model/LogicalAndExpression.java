package com.expression.evaluator.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LogicalAndExpression extends BaseExpression {
    List<BaseExpression> operands;
    String operator;

    public LogicalAndExpression(String operator) {
        super("logical_and");
        this.operator = operator;
        operands=new ArrayList<>();
    }

    @Override
    public Object evaluate(Map<String, Object> variables) {
        for (BaseExpression expression : operands) {
            if (!(boolean) expression.evaluate(variables)) {
                return false;
            }
        }
        return true;
    }

    public List<BaseExpression> getOperands() {
        return operands;
    }
}
