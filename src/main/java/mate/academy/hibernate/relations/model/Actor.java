package mate.academy.hibernate.relations.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "actors")
public class Actor implements Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "country_id")
    private Country country;

    public Actor() {
    }

    public Actor(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public Actor clone() {
        try {
            Actor clonedActor = (Actor) super.clone();
            if (country != null) {
                clonedActor.setCountry(country.clone());
            }
            return clonedActor;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Can't clone actor: " + this, e);
        }
    }

    @Override
    public String toString() {
        String countryName = (country != null) ? country.getName() : "N/A";
        return "Actor{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", country='" + countryName + '\''
                + '}';
    }
}
