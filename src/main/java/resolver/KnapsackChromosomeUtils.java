package resolver;

import chromosome.KnapsackChromosome;

import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class KnapsackChromosomeUtils {

    private static final Random random = new Random();

    private KnapsackChromosomeUtils(){
        // private constructor - utility class
    }

    public static KnapsackChromosome findBestFitted(List<KnapsackChromosome> tourneyGroup){
        return tourneyGroup
                .stream()
                .max(Comparator.comparing(KnapsackChromosome::getFitness))
                .orElseThrow();
    }

    public static boolean lottery(double probability){
        final int BOUND = 100;
        return random.nextInt(BOUND) <= probability*BOUND;
    }
}
