package resolver;

import chromosome.Item;
import crossover.TwoChildrenSinglePointCrossover;
import selection.RouletteSelection;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

public class Main {

    private final static int TOURNEY_GROUP_SIZE = 5;
    private final static int POPULATION_SIZE = 10;
    private final static int MAX_GENERATION_SIZE = 100;
    private final static double MAX_CAPACITY = 165;
    private final static double MUTATION_PROBABILITY = 0.1;
    private final static double CROSSOVER_PROBABILITY = 0.75;

    private static Map.Entry<Integer, Item> createEntry(int id, double weight, double value){
        return new AbstractMap.SimpleEntry<>(id, new Item(id, weight, value));
    }

    public static void main(String[] args) {
        List<Item> items = List.of(
                new Item(1,23,92),
                new Item(2,31,57),
                new Item(3,29,49),
                new chromosome.Item(4,44,68),
                new chromosome.Item(5,53,60),
                new chromosome.Item(6,38,43),
                new chromosome.Item(7,63,67),
                new chromosome.Item(8,85,84),
                new chromosome.Item(9,89,87),
                new chromosome.Item(10,82,72)
        );

        KnapsackProblemResolver knapsackProblemResolver = new KnapsackProblemResolver(
                items,
                MAX_CAPACITY,
                MAX_GENERATION_SIZE,
                POPULATION_SIZE,
                MUTATION_PROBABILITY,
                new RouletteSelection(),
                new TwoChildrenSinglePointCrossover(CROSSOVER_PROBABILITY));

        System.out.println(knapsackProblemResolver.solve());
        System.out.println("1111010000");
    }


}
