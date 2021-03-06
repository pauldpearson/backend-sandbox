package info.mywinecellar.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "grape_component")
public class GrapeComponent extends BaseEntity implements Comparable<GrapeComponent> {

    public GrapeComponent() {
        super();
    }

    public GrapeComponent(Byte percentage, Date harvestBegin, Date harvestEnd, Grape grape, Wine wine) {
        super();
        this.percentage = percentage;
        this.harvestBegin = harvestBegin;
        this.harvestEnd = harvestEnd;
        this.grape = grape;
        this.wine = wine;
    }

    @NotNull(message = "Please provide the %")
    @Column(name = "percentage")
    public Byte percentage;

    @Column(name = "harvest_begin")
    public Date harvestBegin;

    @Column(name = "harvest_end")
    public Date harvestEnd;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "grape_id", referencedColumnName = "id")
    public Grape grape;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "wine_id", referencedColumnName = "id")
    public Wine wine;

    @ManyToOne
    @JoinColumn(name = "maceration_id", referencedColumnName = "id")
    public Maceration maceration;

    @ManyToOne
    @JoinColumn(name = "fermentation_id", referencedColumnName = "id")
    public Fermentation fermentation;

    @OneToMany(mappedBy = "grapeComponent", cascade = CascadeType.REMOVE)
    public List<BarrelComponent> barrelComponents;

    @Override
    public int compareTo(GrapeComponent gc) {
        int result = percentage.compareTo(gc.percentage);

        if (result == 0)
            return grape.name.compareTo(gc.grape.name);

        return result;
    }

    @Override
    public String toString() {
        return "GrapeComponent(" + id + ")";
    }

}
