package crossover;

import chromosome.KnapsackChromosome;

import java.util.List;

public interface Crossover {

    List<KnapsackChromosome> crossover(List<KnapsackChromosome> parents);
}
