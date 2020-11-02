package selection;

import chromosome.KnapsackChromosome;
import resolver.KnapsackChromosomeUtils;

import javax.inject.Singleton;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.*;
import java.util.stream.Collectors;

@Singleton
@Valid
public class TourneySelection implements Selection {

    private final int groupSize;

    public TourneySelection(@Min(0) int groupSize) {
        this.groupSize = groupSize;
    }

    @Override
    public List<KnapsackChromosome> select(List<KnapsackChromosome> population){
        if(groupSize == 0)
            return population;
        if(population.isEmpty())
            return population;
        List<KnapsackChromosome> selected = new ArrayList<>();
        int selectedSize = 0;
        while(selectedSize < population.size()){
            List<KnapsackChromosome> tourneyGroup = chooseTourneyGroup(population, groupSize);
            selected.add(KnapsackChromosomeUtils.findBestFitted(tourneyGroup).deepCopy());
            selectedSize++;
        }
        return selected;
    }

    private List<KnapsackChromosome> chooseTourneyGroup(List<KnapsackChromosome> population, int groupSize){
        Random random = new Random();
        Set<Integer> tourneyGroupIndices = new HashSet<>(groupSize);
        while(tourneyGroupIndices.size()<groupSize){
            tourneyGroupIndices.add(random.nextInt(population.size()));
        }
        return tourneyGroupIndices.stream().map(population::get).collect(Collectors.toList());
    }

}
