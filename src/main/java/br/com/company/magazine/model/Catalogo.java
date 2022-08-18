package br.com.company.magazine.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.ui.Model;

import java.io.Serializable;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Catalogo extends RepresentationModel<Catalogo> implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final String SEQUENCE_CATALOGO = "catalogo_id_seq";

    @Id
    @SequenceGenerator(name = SEQUENCE_CATALOGO, sequenceName = SEQUENCE_CATALOGO, schema = "public", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_CATALOGO)
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String namePerson;
    private String title;
    private Long code;
    private String month;
    private Long year;
}
