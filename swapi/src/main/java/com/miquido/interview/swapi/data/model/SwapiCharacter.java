package com.miquido.interview.swapi.data.model;
import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.lang.Nullable;
import javax.persistence.*;

@Data
@Entity
public class SwapiCharacter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    @Column(unique = true)
    private String name;

    @Nullable
    private Integer height;
    @Nullable
    private Integer mass;

    public SwapiCharacter(String name, Integer height, Integer mass){
        this.name = name;
        this.height = height;
        this.mass = mass;
    }
    public SwapiCharacter(){}
}
