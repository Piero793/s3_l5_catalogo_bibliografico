package it.epicode.classi;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "libri")
public class Libro extends ElementoCatalogo {

    @Column(length = 50, nullable = false)
    private String autore;

    @Column(length = 50)
    private String genere;
}
