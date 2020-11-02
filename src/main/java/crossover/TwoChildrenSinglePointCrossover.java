package crossover;

import chromosome.KnapsackChromosome;
import resolver.KnapsackChromosomeUtils;

import java.util.*;

public class TwoChildrenSinglePointCrossover extends SinglePointCrossover {

    private final double crossoverProbability;
    private final Random random = new Random();

    public TwoChildrenSinglePointCrossover(double probability, double maxCapacity) {
        super(maxCapacity);
        this.crossoverProbability = probability;
    }

    @Override
    public List<KnapsackChromosome> crossover(List<KnapsackChromosome> parents){
        Collections.shuffle(parents);
        return createChildren(parents);
    }

    private List<KnapsackChromosome> createChildren(List<KnapsackChromosome> parents){
        List<KnapsackChromosome> children = matchInPairs(parents);
        if(singleParentLeft(parents))
            children.add(parents.get(parents.size()-1));
        return children;
    }

    private List<KnapsackChromosome> matchInPairs(List<KnapsackChromosome> parents){
        List<KnapsackChromosome> children = new ArrayList<>(parents.size());
        for(int i=0 ;i< parents.size()/2; i++){
            KnapsackChromosome first = parents.get(2*i);
            KnapsackChromosome second = parents.get(2*i+1);
            addChildrenOrParentsToNewPopulation(first, second, children);
        }
        return children;
    }

    private void addChildrenOrParentsToNewPopulation(
            KnapsackChromosome first,
            KnapsackChromosome second,
            List<KnapsackChromosome> children){
        if(KnapsackChromosomeUtils.lottery(crossoverProbability)) {
            int locus = random.nextInt(first.size());
            children.add(crossover(
                    first,
                    second,
                    locus));
            children.add(crossover(
                    second,
                    first,
                    locus
            ));
        }
        else {
            children.add(first);
            children.add(second);
        }
    }

    private boolean singleParentLeft(List<KnapsackChromosome> parents) {
        return parents.size()%2 != 0;
    }

}
