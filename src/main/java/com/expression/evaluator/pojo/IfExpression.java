package com.expression.evaluator.pojo;

import com.expression.evaluator.constants.ExpressionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class IfExpression extends Expression {
    Expression condition;
    Expression trueBranch;

    Expression falseBranch;

    public IfExpression() {

    }
    @Override
    public String getType() {
        return "if";
    }

    public Expression getCondition() {
        return condition;
    }

    public void setCondition(Expression condition) {
        this.condition = condition;
    }

    public Expression getTrueBranch() {
        return trueBranch;
    }

    public void setTrueBranch(Expression trueBranch) {
        this.trueBranch = trueBranch;
    }

    public Expression getFalseBranch() {
        return falseBranch;
    }

    public void setFalseBranch(Expression falseBranch) {
        this.falseBranch = falseBranch;
    }


}
