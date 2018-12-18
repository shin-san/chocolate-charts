package au.com.advent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ChocolateCharts {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChocolateCharts.class);

    private static List<Integer> recipeScore = new ArrayList<Integer>();
    private static List<Integer> lastTenRecipes = new ArrayList<>();
    private static int elf1Position =  1;
    private static int elf2Position = 2;

    private static int score1 = 3;
    private static int score2 = 7;

    private static int counter = 1;

    private static String recipeList = "";

    private static String currentRecipeValue = "360781";
    public static void main(String[] args) {

        phase1();
//        phase2();

    }

    private static void phase2() {

//        int iteration = 360780;
//        iteration = 2017;

        recipeScore.add(score1);
        recipeScore.add(score2);
        recipeList = String.valueOf(score1) + String.valueOf(score2);

        int index = -1;

        while(index < 0) {
            LOGGER.info("Iteration #: {}", counter);
            counter++;
            recipePuzzle();
        }

        LOGGER.info("Total recipes to the left: {}", recipeList.length()-5);
    }

    private static void phase1() {

        int iteration = 2017;

        int nextTenRecipes = 9;

        recipeScore.add(score1);
        recipeScore.add(score2);

        do {
            LOGGER.info("Iteration #: {}", counter);
            counter++;
            recipePuzzle();
        } while(recipeScore.size() <= iteration);

        for (int i = 0; i < nextTenRecipes; i++) {
            recipePuzzle();
        }

        int recipeCounter = 0;

        // Phase 1
        for (int i = 0; i < recipeScore.size(); i++) {
            if (i == iteration) {
                recipeCounter = i;
            }
            if (i > iteration && lastTenRecipes.size() <= 9) {
                lastTenRecipes.add(recipeScore.get(i));
            }
        }

        LOGGER.info("The last 10 recipes are: {}", lastTenRecipes);
    }

    private static void recipePuzzle() {

        int totalScore = score1 + score2;

        if (String.valueOf(totalScore).length() > 1) {
            char[] currentScores = String.valueOf(totalScore).toCharArray();
            recipeScore.add(Character.getNumericValue(currentScores[0]));
            recipeScore.add(Character.getNumericValue(currentScores[1]));
            recipeList += Character.getNumericValue(currentScores[0]);
            recipeList += Character.getNumericValue(currentScores[1]);
        } else {
            recipeScore.add(totalScore);
            recipeList += String.valueOf(totalScore);
        }

        //LOGGER.info("Current recipes: {}", recipeScore);

        int steps1 = score1 + 1;
        int steps2 = score2 + 1;

        int newElf1Position = newPosition(steps1, elf1Position, recipeScore);
        int newElf2Position = newPosition(steps2, elf2Position, recipeScore);

        elf1Position = newElf1Position;
        elf2Position = newElf2Position;

        score1 = recipeScore.get(newElf1Position - 1);
        score2 = recipeScore.get(newElf2Position - 1);

        //LOGGER.info("New recipe score of Elf 1 is: {}", score1);
        //LOGGER.info("New recipe score of Elf 2 is: {}", score2);

    }

    private static int newPosition(int steps, int currentPosition, List<Integer> recipeScore) {

        int newPos = steps;

        while (newPos > recipeScore.size()) {
            newPos -= recipeScore.size();
        }

        int totalSteps = newPos + currentPosition;

        if (totalSteps > recipeScore.size()) {
            newPos = totalSteps - recipeScore.size();
        } else {
            newPos = totalSteps;
        }

        return newPos;
    }
}
