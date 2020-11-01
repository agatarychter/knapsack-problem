package crossover;

import chromosome.KnapsackChromosome;
import resolver.KnapsackChromosomeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;;

public class OneChildSinglePointCrossover extends SinglePointCrossover {

    private final double crossoverProbability;
    private final Random random = new Random();

    public OneChildSinglePointCrossover(double probability, double maxCapacity) {
        super(maxCapacity);
        this.crossoverProbability = probability;
    }

    @Override
    public List<KnapsackChromosome> crossover(List<KnapsackChromosome> parents) {
        List<KnapsackChromosome> children = new ArrayList<>(parents.size());
        parents.forEach(
                parent -> {
                    if(KnapsackChromosomeUtils.lottery(crossoverProbability)){
                        children.add(crossover(parent,
                                parents.get(random.nextInt(parents.size())),
                                random.nextInt(parent.size())));

                    }
                    else{
                        children.add(parent);
                    }
                }
        );
        return children;
    }

}
