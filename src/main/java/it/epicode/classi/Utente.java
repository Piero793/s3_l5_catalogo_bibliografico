package it.epicode.classi;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "utenti")
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int numeroTessera;

    @Column(length = 50)
    private String nome;

    @Column(length = 50)
    private String cognome;

    @Column
    private LocalDate dataDiNascita;

    @OneToMany(mappedBy = "utente",cascade = CascadeType.ALL)
    private List<Prestito> prestiti = new ArrayList<>();
}