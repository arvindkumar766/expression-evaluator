package com.expression.evaluator.model;

import org.apache.commons.lang3.math.NumberUtils;

import java.util.Map;

public class ValueExpression extends BaseExpression {

    private Object value;

    public ValueExpression(Object value) {
        super("value");
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public Object evaluate(Map<String, Object> variables) {
        // Return the constant value
        return value;
    }

    public static boolean isValueToken(String token) {
        return token.contains("'") || isDigit(token) || isBoolean(token);
    }

    private static boolean isBoolean(String str) {
        return str.equals("true") || str.equals("false");
    }

    private static boolean isDigit(String token) {
        return NumberUtils.isCreatable(token);
    }


    public static ValueExpression getInstance(String token) {
        Object value;
        if (isDigit(token)) {
            value = getNumber(token);
        } else if (isBoolean(token)) {
            value = Boolean.parseBoolean(token);
        }else{
            value= token.replace("'","");
        }

        return new ValueExpression(value);
    }

    private static Number getNumber(String token) {
        return NumberUtils.createNumber(token);
    }
}
