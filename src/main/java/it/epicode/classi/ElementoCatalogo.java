package it.epicode.classi;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@NamedQuery(name = "ElementoCatalogo.findByAnnoPubblicazione",query = "SELECT e FROM ElementoCatalogo e WHERE e.annoPubblicazione = :anno")
@NamedQuery(name = "ElementoCatalogo.findByTitoloParziale",query = "SELECT e FROM ElementoCatalogo e WHERE e.titolo LIKE :titolo")
@Entity
@Table(name = "catalogo")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class ElementoCatalogo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 50, nullable = false)
    private String isbn;

    @Column(length = 100, nullable = false)
    private String titolo;

    @Column(nullable = false)
    private int annoPubblicazione;

    @Column(nullable = false)
    private int numeroPagine;
}
