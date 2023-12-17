package com.expression.evaluator.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public interface IExpression {

    Object evaluate(Map<String, Object> variables);

    String toJson() throws JsonProcessingException;

    default BaseExpression fromJson(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, BaseExpression.class);
    }

}
