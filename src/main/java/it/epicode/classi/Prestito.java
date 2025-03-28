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
@NamedQuery(name = "Prestito.findByNumeroTessera",query = "SELECT p FROM Prestito p WHERE p.utente.numeroTessera = :numeroTessera")
@NamedQuery(name = "Prestito.findPrestitiAttivi",query = "SELECT p FROM Prestito p WHERE p.dataRestituzioneEffettiva IS NULL")
@NamedQuery(name = "Prestito.findPrestitiScaduti",query = "SELECT p FROM Prestito p WHERE p.dataRestituzionePrevista < CURRENT_DATE AND p.dataRestituzioneEffettiva IS NULL")
@Entity
@Table(name = "prestiti")
public class Prestito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "utente_id", nullable = false)
    private Utente utente;

    @ManyToOne
    @JoinColumn(name = "elemento_prestato_id", nullable = false)
    private ElementoCatalogo elementoPrestato;

    @Column(nullable = false)
    private LocalDate dataInizioPrestito;

    @Column(nullable = false)
    private LocalDate dataRestituzionePrevista;

    @Column
    private LocalDate dataRestituzioneEffettiva;

    // Metodo per calcolare la data di restituzione prevista
    public void calcolaDataRestituzionePrevista() {
        this.dataRestituzionePrevista = this.dataInizioPrestito.plusDays(30);
    }
}