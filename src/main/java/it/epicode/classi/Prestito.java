package it.epicode.classi;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@NamedQuery(name = "Prestito.trovaElementiPrestatiDaUtente", query = "SELECT p.elementoPrestato FROM Prestito p WHERE p.utente.numeroTessera = :numeroTessera AND p.dataRestituzioneEffettiva IS NULL")
@NamedQuery(name = "Prestito.trovaPrestitiScadutiENonRestituiti", query = "SELECT p FROM Prestito p WHERE p.dataRestituzionePrevista < CURRENT_DATE AND p.dataRestituzioneEffettiva IS NULL")
@Entity
@Table(name = "prestiti")
public class Prestito {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    private Utente utente;

    @ManyToOne(optional = false)
    private ElementoCatalogo elementoPrestato;

    @Column(nullable = false)
    private LocalDate dataInizioPrestito;

    @Column(nullable = false)
    private LocalDate dataRestituzionePrevista;

    @Column
    private LocalDate dataRestituzioneEffettiva;


    public void calcolaDataRestituzionePrevista() {
        this.dataRestituzionePrevista = this.dataInizioPrestito.plusDays(30);
    }
}