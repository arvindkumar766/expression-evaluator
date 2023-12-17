package com.expression.evaluator.pojo;

import com.expression.evaluator.constants.ExpressionStack;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = IfExpression.class, name = "if"),
        @JsonSubTypes.Type(value = CompareExpression.class, name = "comparison"),
        @JsonSubTypes.Type(value = LogicalAndExpression.class, name = "logical_and"),
        @JsonSubTypes.Type(value = LogicalOrExpression.class, name = "logical_or"),
        @JsonSubTypes.Type(value = VariableExpression.class, name = "variable"),
        @JsonSubTypes.Type(value = ValueExpression.class, name = "value"),
        @JsonSubTypes.Type(value = ConcatenateExpression.class, name = "concatenate"),
        @JsonSubTypes.Type(value = MultiplicationExpression.class, name = "multiplication"),
        @JsonSubTypes.Type(value = AdditionExpression.class, name = "addition")
})
public abstract class Expression {
    private static int idCounter = 0;
    private final int id;

    public abstract String getType();

    protected Expression expression;

    public Expression() {
        id = ++idCounter;

    }

    public static Expression valueOf(String expression) throws JsonProcessingException {
//        ObjectMapper objectMapper = new ObjectMapper();
        // Tokenize the input expression
        String[] tokens = expression.split("\\s+");

        // Use a simple stack to parse the expression
        ExpressionStack stack = new ExpressionStack();

        for (String token : tokens) {
            stack.processToken(token);
        }

        return stack.getResult();
//        return objectMapper.readValue(expression, Expression.class);
    }

    public static void parseExpression(String expression) {
        String[] tokens = expression.split("\\s+");
        System.out.println(Arrays.toString(tokens));
    }

    private String toJson() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(this);
    }

    public int getId() {
        return id;
    }
}
