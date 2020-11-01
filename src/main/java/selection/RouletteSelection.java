package selection;

import chromosome.KnapsackChromosome;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RouletteSelection implements Selection{

    private final Random random = new Random();

    @Override
    public List<KnapsackChromosome> select(List<KnapsackChromosome> population)
    {
        List<KnapsackChromosome> selected = new ArrayList<>(population.size());
        double fitnessSum = population.stream()
                .mapToDouble(KnapsackChromosome::getFitness)
                .sum();
        if(fitnessSum == 0)
            return population;
        int chosenIndividuals = 0;
        double [] wheel = buildRouletteWheel(population, fitnessSum);
        double rand = random.nextDouble();
        while(chosenIndividuals<population.size())
        {
            int index = 0;
            while(rand>wheel[index])
            {
                index++;
            }
            selected.add(population.get(index));
            chosenIndividuals++;
            rand = random.nextDouble();
        }
        return selected;
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
//        System.out.println(Arrays.toString(wheel));
        return wheel;
    }
}
