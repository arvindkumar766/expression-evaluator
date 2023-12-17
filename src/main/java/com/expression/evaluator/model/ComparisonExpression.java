package com.expression.evaluator.model;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class ComparisonExpression extends BaseExpression {
    public ComparisonExpression(String operator) {
        super("compare");
        this.operator = operator;

    }

    private String operator;
    private BaseExpression left;
    private BaseExpression right;

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public BaseExpression getLeft() {
        return left;
    }

    public void setLeft(BaseExpression left) {
        this.left = left;
    }

    public BaseExpression getRight() {
        return right;
    }

    public void setRight(BaseExpression right) {
        this.right = right;
    }


    @Override
    public Object evaluate(Map<String, Object> variables) {
        // Evaluate the comparison expression
        Object leftValue = left.evaluate(variables);
        Object rightValue = right.evaluate(variables);
        return getResultByOperator(leftValue, rightValue);
    }

    private boolean getResultByOperator(Object left, Object right) {
        return switch (operator) {
            case "==" -> left.equals(right);
            case "!=" -> !left.equals(right);
            case "<=" -> getComparisonResult(left, right, true);
            case ">=" -> getComparisonResult(right, left, true);
            case "<" -> getComparisonResult(left, right, false);
            case ">" -> getComparisonResult(right, left, false);
            default -> false;
        };
    }

    private boolean getComparisonResult(Object left, Object right, boolean inclusive) {
        if (left instanceof Double && right instanceof Double) {
            return inclusive ? (Double) left <= (Double) right :
                    (Double) left < (Double) right;
        } else if (left instanceof Long && right instanceof Long) {
            return inclusive ? (Long) left <= (Long) right :
                    (Long) left < (Long) right;
        } else {
            if (inclusive) {
                assert left instanceof Integer;
                return (Integer) left <= (Integer) right;
            } else {
                assert left instanceof Integer;
                return (Integer) left < (Integer) right;
            }
        }
    }
}

class FoodRatings {


    class Food  {

        int rating;
        String food;


        public Food(String food, int rating) {
            this.food = food;
            this.rating = rating;
        }


    }

    Map<String, PriorityQueue<Food>> topRatedCuisines;
    Map<String, String> foodCuisineMap;
    Map<String, Food> foodDetailMap;

    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        this.topRatedCuisines = new HashMap<>();
        this.foodDetailMap = new HashMap<>();
        foodCuisineMap = new HashMap<>();
        int n = foods.length;
        for (int i = 0; i < n; i++) {
            foodCuisineMap.put(foods[i], cuisines[i]);
            Food food = new Food(foods[i], ratings[i]);
            foodDetailMap.put(foods[i], food);
            if (!topRatedCuisines.containsKey(cuisines[i])) {
                topRatedCuisines.put(cuisines[i], new PriorityQueue<>((a, b) -> {
                    int result=b.rating-a.rating;
                    if(result==0){
                        return (a.food).compareTo(b.food);
                    }
                    return result;
                }));

            }
            topRatedCuisines.get(cuisines[i]).add(food);
        }
    }

    public void changeRating(String food, int newRating) {
        String cuisine = foodCuisineMap.get(food);
        Food previousFood = foodDetailMap.get(food);
        topRatedCuisines.get(cuisine).remove(previousFood);

        Food newFood = new Food(food, newRating);
        foodDetailMap.put(food, newFood);
        topRatedCuisines.get(cuisine).add(newFood);
    }

    public String highestRated(String cuisine) {
        return topRatedCuisines.get(cuisine).peek().food;
    }

    public static void main(String[] args) {
        FoodRatings foodRatings = new FoodRatings(new String[]{"cpctxzh", "bryvgjqmj", "wedqhqrmyc", "ee", "lafzximxh", "lojzxfel", "flhs"},
                new String[]{"wbhdgqphq", "wbhdgqphq", "mxxajogm", "wbhdgqphq", "wbhdgqphq", "mxxajogm", "mxxajogm"},
                new int[]{5, 5, 7, 16, 16, 10, 13});
        //["FoodRatings","changeRating","highestRated","highestRated","highestRated"]
        //[[["cpctxzh","bryvgjqmj","wedqhqrmyc","ee","lafzximxh","lojzxfel","flhs"],
        // ["wbhdgqphq","wbhdgqphq","mxxajogm","wbhdgqphq","wbhdgqphq","mxxajogm","mxxajogm"],
        // [15,5,7,16,16,10,13]],["lojzxfel",1],["mxxajogm"],["wbhdgqphq"],["mxxajogm"]]
        foodRatings.changeRating("lojzxfel", 1);
        System.out.println(foodRatings.highestRated("mxxajogm"));
        System.out.println(foodRatings.highestRated("wbhdgqphq"));
        System.out.println(foodRatings.highestRated("mxxajogm"));
    }
}