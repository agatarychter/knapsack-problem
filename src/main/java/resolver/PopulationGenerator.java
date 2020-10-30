package resolver;

import chromosome.Item;
import chromosome.KnapsackChromosome;
import chromosome.KnapsackGene;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class PopulationGenerator {

    private static final Random random = new Random();

    public static List<KnapsackChromosome> generatePopulation(List<Item> items, int populationSize){
        List<KnapsackChromosome> population = new ArrayList<>(populationSize);
        for(int i =0;i<populationSize ;i++)
            population.add(generateRandomChromosome(items));
        return population;
    }

    private static KnapsackChromosome generateRandomChromosome(List<Item> items){
        List<KnapsackGene> genes =
                items.stream()
                        .map(item -> new KnapsackGene(item, random.nextBoolean()))
                        .collect(Collectors.toList());
        return new KnapsackChromosome(genes);
    }
}
