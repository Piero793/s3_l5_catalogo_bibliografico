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
@NamedQuery(name = "ElementoCatalogo.trovaPerAnnoPubblicazione", query = "SELECT e FROM ElementoCatalogo e WHERE e.annoPubblicazione = :anno")
@NamedQuery(name = "ElementoCatalogo.trovaPerAutore", query = "SELECT l FROM Libro l WHERE l.autore = :autore")
@NamedQuery(name = "ElementoCatalogo.trovaPerTitoloParziale", query = "SELECT e FROM ElementoCatalogo e WHERE e.titolo LIKE :titolo")
@Entity
@Table(name = "catalogo")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class ElementoCatalogo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 50)
    private String isbn;

    @Column(length = 100)
    private String titolo;

    @Column
    private int annoPubblicazione;

    @Column
    private int numeroPagine;
}
