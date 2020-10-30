package selection;

import chromosome.KnapsackChromosome;

import java.util.List;

public interface Selection {

    List<KnapsackChromosome> select(List<KnapsackChromosome> population);

}
