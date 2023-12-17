package com.expression.evaluator.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class BaseExpression implements IExpression {
    private static AtomicInteger counter = new AtomicInteger(0);
    private final int id;
    private final String type;

    public String getType() {
        return type;
    }

    public BaseExpression(String type) {
        this.type = type;
        id = counter.incrementAndGet();
    }

    public int getId() {
        return id;
    }


    public abstract Object evaluate(Map<String, Object> variables);


    @Override
    public String toJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(this);
    }

    public static void main(String[] args) {
        String exp = "age < 10 && age > 6";

//        String exp = "if ( name == 'arvind' && age >= 10) return 'hello'";
        ExpresssionHelper helper = new ExpresssionHelper();
        BaseExpression result = helper.getExpression(exp);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("age", 9);
        System.out.println(result.evaluate(hashMap));
    }
}
