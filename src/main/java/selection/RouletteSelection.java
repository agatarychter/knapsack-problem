package selection;

import chromosome.KnapsackChromosome;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RouletteSelection implements Selection{

    private final Random random = new Random();

    @Override
    public List<KnapsackChromosome> select(List<KnapsackChromosome> population)
    {
        double fitnessSum = population.stream()
                .mapToDouble(KnapsackChromosome::getFitness)
                .sum();
        if(fitnessSum == 0)
            return population;
        double [] wheel = buildRouletteWheel(population, fitnessSum);
        return selectByTurningRoulette(population, wheel);
    }

    private double[] buildRouletteWheel(List<KnapsackChromosome> population, double fitnessSum){
        double[] wheel = new double[population.size()];
        for(int i=0;i<wheel.length;i++)
        {
            if(i==0)
                wheel[i]=(population.get(i).getFitness())/fitnessSum;
            else
                wheel[i] = wheel[i-1] + (population.get(i).getFitness())/fitnessSum;
        }
        return wheel;
    }

    private List<KnapsackChromosome> selectByTurningRoulette(List<KnapsackChromosome> population, double[] wheel){
        List<KnapsackChromosome> selected = new ArrayList<>(population.size());
        int chosenIndividuals = 0;
        double rand = random.nextDouble();
        while(chosenIndividuals<population.size())
        {
            int index = 0;
            while(rand>wheel[index])
            {
                index++;
            }
            selected.add(population.get(index).deepCopy());
            chosenIndividuals++;
            rand = random.nextDouble();
        }
        return selected;
    }
}
