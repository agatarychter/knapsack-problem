package chromosome;

import lombok.Data;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;

@Data
public class KnapsackChromosome {

    private final static Random random = new Random();
    private final List<KnapsackGene> genes;
    private double fitness;

    public KnapsackChromosome(List<KnapsackGene> genes) {
        this.genes = genes;
        this.fitness = 0;
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

    public boolean exceedsWeight(double maxCapacity){
        return weight() > maxCapacity;
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
        genes.get(random.nextInt(size())).mutate();
    }

    public void correct(double maxCapacity) {
        while(exceedsWeight(maxCapacity)){
            KnapsackGene gene =  genes.get(random.nextInt(size()));
            if(gene.isActive())
                gene.mutate();
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        genes.forEach(gene -> stringBuilder.append(gene.isActive()? 1 : 0));
        stringBuilder.append( "Fitness: "+ fitness);
        return stringBuilder.toString();
    }
}
