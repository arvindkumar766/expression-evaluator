package com.expression.evaluator.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LogicalOrExpression extends BaseExpression {
    private final String operator;
    public List<BaseExpression> operands;

    public LogicalOrExpression(String operator) {
        super("logical_or");
        this.operator = operator;
        operands = new ArrayList<>();
    }

    @Override
    public Object evaluate(Map<String, Object> variables) {
        for (BaseExpression expression : operands) {
            if ((boolean) expression.evaluate(variables)) {
                return true;
            }
        }
        return false;
    }

    public String getOperator() {
        return operator;
    }

    public List<BaseExpression> getOperands() {
        return operands;
    }
}
