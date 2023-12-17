package com.expression.evaluator.model;

import com.expression.evaluator.pojo.Expression;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Map;

public class VariableExpression extends BaseExpression {
    private String name;

    public VariableExpression(String name) {
        super("variable");
        this.name = name;
    }

    public static VariableExpression getInstance(String name){
        return new VariableExpression(name);
    }

    @Override
    public Object evaluate(Map<String, Object> variables) {
        return variables.getOrDefault(name, null);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getType() {
        return "variable";
    }

    @Override
    public Expression fromJson(String json) throws JsonProcessingException {
        return super.fromJson(json);
    }
}
