package com.expression.evaluator.constants;

import com.expression.evaluator.pojo.*;

import java.util.Stack;

public class ExpressionStack {

    private String currentOperator;
    private Stack<Expression> stack;

    public ExpressionStack() {
        this.stack = new Stack<>();
    }

    public void processToken(String token) {
        if ("if".equals(token)) {
            IfExpression ifExpression = new IfExpression();
            push(ifExpression);
        } else if ("else".equals(token)) {
//            pop();
            IfExpression parentIfExpression = findParentIfExpression();
            IfExpression newIfExpression = new IfExpression();
            parentIfExpression.setFalseBranch(newIfExpression);
            push(newIfExpression);
        } else if ("return".equals(token)) {
            // Do nothing for now
        } else if ("==".equals(token) || "&&".equals(token) || "||".equals(token) || "<".equals(token) || "*".equals(token) || "+".equals(token)) {
            currentOperator = token;
        } else if ("(".equals(token)) {
            // Ignore
        } else if (")".equals(token)) {
            pop(); // Pop the last expression within the parentheses
        } else {
            // Handle variable, comparison, or value
            if ("==".equals(currentOperator) || "&&".equals(currentOperator) || "||".equals(currentOperator) || "<".equals(currentOperator)) {
                CompareExpression compareExpression = new CompareExpression();
                compareExpression.setOperator(currentOperator);
                compareExpression.setLeft(pop());
                ValueExpression valueExpression = new ValueExpression();
                valueExpression.setValue(token);
                compareExpression.setRight(valueExpression);
                push(compareExpression);
                currentOperator = null;
            } else {
                // Handle variable or value
                if (stack.isEmpty() || stack.peek() instanceof IfExpression) {
                    // Create a ValueExpression
                    ValueExpression valueExpression = new ValueExpression();
                    valueExpression.setValue(token);
                    push(valueExpression);
                } else {
                    // Handle variable
                    VariableExpression variableExpression = new VariableExpression();
                    variableExpression.setName(token);
                    push(variableExpression);
                }
            }
        }
    }

    private IfExpression findParentIfExpression() {
        Stack<Expression> tempStack = new Stack<>();
        IfExpression parentIfExpression = null;

        while (!stack.isEmpty()) {
            Expression expression = stack.pop();
            if (expression instanceof IfExpression) {
                parentIfExpression = (IfExpression) expression;
                break;
            }
            tempStack.push(expression);
        }

        while (!tempStack.isEmpty()) {
            stack.push(tempStack.pop());
        }

        return parentIfExpression;
    }
    public void push(Expression expression) {
        stack.push(expression);
    }

    public Expression pop() {
        if (!stack.isEmpty()) {
            return stack.pop();
        }
        return null;
    }

    public Expression getResult() {
        if (stack.isEmpty()) {
            return null;
        }
        return stack.pop();
    }

}