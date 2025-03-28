package it.epicode.classi;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@NamedQuery(name = "Libro.findByAutore",query = "SELECT l FROM Libro l WHERE l.autore = :autore")
@Entity
@Table(name = "libri")
public class Libro extends ElementoCatalogo {

    @Column(length = 50, nullable = false)
    private String autore;

    @Column(length = 50)
    private String genere;
}
