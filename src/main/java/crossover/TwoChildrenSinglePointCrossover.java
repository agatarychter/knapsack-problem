package crossover;

import chromosome.KnapsackChromosome;
import resolver.KnapsackChromosomeUtils;

import java.util.*;

public class TwoChildrenSinglePointCrossover extends SinglePointCrossover {

    private final double crossoverProbability;
    private final Random random = new Random();

    public TwoChildrenSinglePointCrossover(double probability) {
        this.crossoverProbability = probability;
    }

    @Override
    public List<KnapsackChromosome> crossover(List<KnapsackChromosome> parents){
        Collections.shuffle(parents);
        List<KnapsackChromosome> children = new ArrayList<>(parents.size());
        for(int i=0 ;i< parents.size()/2; i++){
            KnapsackChromosome first = parents.get(2*i);
            KnapsackChromosome second = parents.get(2*i+1);
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

        if(parents.size()%2 != 0){
            children.add(parents.get(parents.size()-1));
        }
        return children;
    }

//    @Override
//    public List<KnapsackChromosome> crossover(List<KnapsackChromosome> parents) {
//        List<KnapsackChromosome> children = new ArrayList<>(parents.size());
//        HashSet<Integer> processedParentsIndices = new HashSet<>(parents.size());
//        for(int i=0; processedParentsIndices.size()<=parents.size(); i++){
//            if(!processedParentsIndices.contains(i)){
//                KnapsackChromosome first = parents.get(i);
//                int secondIndex = chooseNotProcessedParentIndex(processedParentsIndices, parents, i+1);
//                KnapsackChromosome second = parents.get(secondIndex);
//                if(KnapsackChromosomeUtils.lottery(crossoverProbability)) {
//                    int locus = random.nextInt(first.size());
//                    children.add(crossover(
//                            first,
//                            second,
//                            locus));
//                    children.add(crossover(
//                            second,
//                            first,
//                            locus
//                    ));
//                }
//                else {
//                    children.add(first);
//                    children.add(second);
//                }
//                processedParentsIndices.add(i);
//                processedParentsIndices.add(secondIndex);
//            }
//        }
//        chooseSingleParentIfPopulationNotEven(parents, processedParentsIndices);
//        throwIfNotParentsProcessed(processedParentsIndices, parents);
//        return children;
//    }
//
//    private void chooseSingleParentIfPopulationNotEven(List<KnapsackChromosome> parents, HashSet<Integer> processedParentsIndices) {
//        if(parents.size()%2 == 0)
//            return;
//        processedParentsIndices.add(chooseNotProcessedParentIndex(
//                processedParentsIndices,
//                parents,
//                0
//        ));
//    }
//
//    private int chooseNotProcessedParentIndex(
//            HashSet<Integer> processedParentsIndices,
//            List<KnapsackChromosome> parents,
//            int startIndex) {
//        int i = getRandomFromRange(startIndex, parents.size());
//        while(processedParentsIndices.contains(i)){
//            i = getRandomFromRange(startIndex, parents.size());
//        }
//        return i;
//    }
//
//    private int getRandomFromRange(int startIndex, int bound){
//        return random.nextInt(bound-startIndex) + startIndex;
//    }
//
//
//    private void throwIfNotParentsProcessed(HashSet<Integer> processedParentsIndices, List<KnapsackChromosome> parents) {
//        if(processedParentsIndices.size() != parents.size())
//            throw new RuntimeException("Not all parents were processed in the crossover phase");
//    }


}
