package com.expression.evaluator.pojo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public interface IExpression {

    Object evaluate(Map<String, Object> variables);

    String toJson() throws JsonProcessingException;

    default Expression fromJson(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, Expression.class);
    }

}
