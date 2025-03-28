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