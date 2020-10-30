package chromosome;

public class KnapsackGene {

    private final Item item;
    private boolean active;

    public KnapsackGene(Item item, boolean active) {
        this.item = item;
        this.active = active;
    }

    public void mutate(){
        this.active = !this.active;
    }

    public double getWeight() {
        return active ? item.getWeight() : 0;
    }

    public double getValue(){
        return active ? item.getValue() : 0;
    }

    @Override
    public String toString() {
        return "chromosome.KnapsackGene{" +
                "item=" + item +
                ", active=" + (active ? 1 : 0 )+
                '}';
    }
}
