package crossover;

import chromosome.KnapsackChromosome;
import chromosome.KnapsackGene;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class SinglePointCrossover implements Crossover {

    @Override
    public abstract List<KnapsackChromosome> crossover(List<KnapsackChromosome> parents);

    protected KnapsackChromosome crossover(KnapsackChromosome first, KnapsackChromosome second, int locus){
        checkLocusRange(locus, first.size());
        List<KnapsackGene> genesToLocus = first.getGenes().subList(0,locus);
        List<KnapsackGene> genesFromLocus = second.getGenes().subList(locus,second.getGenes().size());
        List<KnapsackGene> crossedGenes = Stream
                .concat(genesToLocus.stream(), genesFromLocus.stream())
                .collect(Collectors.toList());
        return new KnapsackChromosome(crossedGenes);
    }

    private void checkLocusRange(int locus, int size){
        if(locus < 0)
            throw new IllegalArgumentException("Locus cannot be lower than 0.");
        if(locus >= size)
            throw new IllegalArgumentException("Locus has to be lower than the number of genes.");

    }


}
