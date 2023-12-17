package com.expression.evaluator.pojo;

public class CompareExpression extends Expression {
    String operator;

    Expression left;
    Expression right;

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public void setLeft(Expression left) {
        this.left = left;
    }

    public void setRight(Expression right) {
        this.right = right;
    }

    @Override
    public String getType() {
        return "comparison";
    }

    @Override
    public String toString() {
        return left + operator + right;
    }
}
