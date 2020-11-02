package chromosome;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Data
public class KnapsackChromosome{

    private final static Random RANDOM = new Random();
    private final double maxCapacity;
    private final List<KnapsackGene> genes;
    private double fitness;

    public KnapsackChromosome(List<KnapsackGene> genes, double maxCapacity) {
        this.genes = genes;
        this.fitness = 0;
        this.maxCapacity = maxCapacity;
        correctIfNecessary();
    }

    private void correctIfNecessary() {
        while(exceedsWeight(maxCapacity)){
            KnapsackGene gene =  genes.get(RANDOM.nextInt(size()));
            if(gene.isActive())
                gene.mutate();
        }
    }

    public KnapsackChromosome deepCopy(){
        return new KnapsackChromosome(
                new ArrayList<>(genes.stream()
                        .map(KnapsackGene::deepCopy)
                        .collect(Collectors.toList())),
                maxCapacity);
    }

    public int size(){
        return genes.size();
    }

    public void evaluate(double maxCapacity){
        if(weight() > maxCapacity)
            this.fitness = 0;
        else
            this.fitness = value();
    }

    private double weight(){
        return genes
                .stream()
                .mapToDouble(KnapsackGene::getWeight)
                .sum();
    }

    private double value(){
        return genes
                .stream()
                .mapToDouble(KnapsackGene::getValue)
                .sum();
    }

    public void mutate(boolean shouldMutate) {
        if (shouldMutate) {
            genes.get(RANDOM.nextInt(size())).mutate();
            correctIfNecessary();
        }
    }

    private boolean exceedsWeight(double maxCapacity){
        return weight() > maxCapacity;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        genes.forEach(gene -> stringBuilder.append(gene.isActive()? 1 : 0));
        stringBuilder.append("| Fitness: ").append(fitness);
        return stringBuilder.toString();
    }
}
