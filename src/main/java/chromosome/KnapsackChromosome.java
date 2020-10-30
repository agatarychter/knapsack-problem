package chromosome;

import lombok.Data;
import java.util.List;
import java.util.concurrent.Callable;

@Data
public class KnapsackChromosome {

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

    public void mutate(Callable<Boolean> shouldMutate) {
        genes.forEach(
                gene -> {
                    try {
                        if(shouldMutate.call())
                            gene.mutate();
                    } catch (Exception e) {
                        throw new UnsupportedOperationException("Mutation call failed");
                    }
                }
        );
    }

    @Override
    public String toString() {
        return "Fitness: "+ fitness;
//        StringBuilder stringBuilder = new StringBuilder();
//        genes.forEach(gene -> stringBuilder.append( gene.toString()+ "\n"));
//        return stringBuilder.toString();
    }

}
