package chromosome;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@Valid
@Data
public class Item {
    private final int id;
    private final double weight;
    private final double value;

    public Item(int id, @Min(0) double weight, @Min(0)double value) {
        this.id = id;
        this.weight = weight;
        this.value = value;
    }
}
