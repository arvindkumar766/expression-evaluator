package com.expression.evaluator.model;

import java.util.Stack;
import java.util.regex.Pattern;

public class ExpresssionHelper {
    Stack<BaseExpression> expressionStack = new Stack<>();

    private String currentOperator;
    private Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    private void push(BaseExpression expression) {
        expressionStack.push(expression);
    }

    private BaseExpression pop() {
        return expressionStack.pop();
    }

    private BaseExpression peek() {
        return expressionStack.peek();
    }

    public BaseExpression getExpression(String expression) {
        String[] tokens = expression.split(" ");

        // Use a simple stack to parse the expression
        for (String token : tokens) {
            System.out.println(token);
            processToken(token);
        }
        return getResult();
    }

    private void processToken(String token) {

        switch (token) {
            case "&&": {
                currentOperator = token;
                LogicalAndExpression logicalAndExpression = new LogicalAndExpression("&&");
                BaseExpression lastExpression = pop();
                logicalAndExpression.getOperands().add(lastExpression);
                push(logicalAndExpression);
            }
            break;
            case "||": {
                currentOperator = token;
                LogicalOrExpression logicalOrExpression = new LogicalOrExpression("||");
                BaseExpression lastExpression = pop();
                logicalOrExpression.getOperands().add(lastExpression);
                push(logicalOrExpression);
            }
            case "==":
            case ">=":
            case "<=":
            case ">":
            case "<":
            case "!=":
                if (currentOperator != null) {
                    if (currentOperator.equals("||")) {
                        LogicalOrExpression logicalOrExpression = (LogicalOrExpression) pop();

                    } else if (currentOperator.equals("&&")) {
                        LogicalAndExpression logicalAndExpression = (LogicalAndExpression) pop();

                    }
                } else {
                    ComparisonExpression compareExpression = new ComparisonExpression(token);
                    compareExpression.setLeft(expressionStack.pop());
                    push(compareExpression);
                }
                break;
            default:
                BaseExpression expression;
                if (ValueExpression.isValueToken(token)) {
                    expression = ValueExpression.getInstance(token);
                } else {
                    expression = VariableExpression.getInstance(token);
                }
                if (!expressionStack.isEmpty() && peek() instanceof ComparisonExpression) {
                    ComparisonExpression comparisonExpression = (ComparisonExpression) pop();
                    comparisonExpression.setRight(expression);
                    expression = comparisonExpression;
                }
                if (currentOperator != null && currentOperator.equals("||")) {
                    LogicalOrExpression logicalOrExpression = (LogicalOrExpression) pop();
                    logicalOrExpression.getOperands().add(expression);
                    expression = logicalOrExpression;
                } else if (currentOperator != null && currentOperator.equals("&&")
                        && !expressionStack.isEmpty() && peek() instanceof LogicalAndExpression) {
                    LogicalAndExpression logicalAndExpression = (LogicalAndExpression) pop();
                    logicalAndExpression.getOperands().add(expression);
                    expression = logicalAndExpression;
                }
                push(expression);
        }
    }


    public BaseExpression getResult() {
        if (expressionStack.isEmpty()) {
            return null;
        }
        return expressionStack.pop();
    }
}
