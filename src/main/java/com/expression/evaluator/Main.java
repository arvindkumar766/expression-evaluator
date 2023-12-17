package com.expression.evaluator;

import com.expression.evaluator.pojo.Expression;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
//    static String  inputExpression = "if ( ( name == 'Arvind' && gender == 'male' ) || gender == 'female' ) return name + gender else if( name == 'Pawan' || gender == 'Male' || age == 9) { return age * 2; } else if( gender == 'none' ) return 'None' else { return age + 2; }";

    public static void main(String[] args) throws JsonProcessingException {
//        String inputExpression = "if (( name=='Arvind' && gender =='male') || gender=='female' ) return name + gender else if(name=='Pawan' || gender == 'Male' || age == 9) { return age * 2; } else if(gender=='none') return 'None' else { return age + 2; }";
        String inputExpression = "name == 'Arvind'";
        Expression expression = Expression.valueOf(inputExpression);
        System.out.println(new ObjectMapper().writeValueAsString(expression));
    }
}