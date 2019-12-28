package tech.bestwebshop.api.categoryservice.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name="category")
@NoArgsConstructor
@RequiredArgsConstructor
public class Category implements Serializable {

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @NonNull
    private int id;

    @Column(name = "name")
    @NonNull
    private String name;
}
