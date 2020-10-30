package resolver;

import chromosome.Item;
import chromosome.KnapsackChromosome;
import crossover.Crossover;
import selection.Selection;

import java.util.List;

public class KnapsackProblemResolver {

    private final double mutationProbability;
    private final int maxGenerationSize;
    private final List<Item> items;
    private final int populationSize;
    private final double maxCapacity;
    private final Selection selection;
    private final Crossover crossover;

    public KnapsackProblemResolver(
            List <Item> items,
            double maxCapacity,
            int maxGenerationSize,
            int populationSize,
            double mutationProbability,
            Selection selection,
            Crossover crossover) {
        this.items = items;
        this.maxCapacity = maxCapacity;
        this.maxGenerationSize = maxGenerationSize;
        this.populationSize = populationSize;
        this.mutationProbability = mutationProbability;
        this.selection = selection;
        this.crossover = crossover;
    }

    public KnapsackChromosome solve(){
        int generationSize = 0;
        List<KnapsackChromosome> population = PopulationGenerator.generatePopulation(items, populationSize);
        evaluate(population);
        while(generationSize < maxGenerationSize) {
            print(population, generationSize);
            List<KnapsackChromosome> parentsPopulation = selection.select(population);
            List<KnapsackChromosome> children = crossover.crossover(parentsPopulation);
            mutate(children);
            population = children;
            evaluate(population);
            generationSize++;
        }
        return KnapsackChromosomeUtils.findBestFitted(population);
    }

    private void evaluate(List<KnapsackChromosome> population){
        population.forEach(
                chromosome -> chromosome.evaluate(maxCapacity));
    }

    private void print(List<KnapsackChromosome> population, int generationSize){
        System.out.println("ITERATION " + generationSize);
        population.forEach(ind -> System.out.println(ind.toString()));
        System.out.println("---------------------");
    }

    private void mutate(List<KnapsackChromosome> population){
        population.forEach(
                chromosome -> chromosome.mutate(()->KnapsackChromosomeUtils.lottery(mutationProbability)));
    }

}