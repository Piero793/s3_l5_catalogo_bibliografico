package it.epicode.main;

import it.epicode.DAO.ArchivioDAO;
import it.epicode.classi.*;
import it.epicode.enums.Periodicita;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("epicode");
        EntityManager em = emf.createEntityManager();

        ArchivioDAO archivioDAO = new ArchivioDAO(em);

        // Creazione di libri
        Libro libro = Libro.builder()
                .isbn("1234567890")
                .titolo("Il Signore degli Agnelli")
                .autore("J.R.R. Tolkien")
                .genere("Fantasy")
                .annoPubblicazione(1954)
                .numeroPagine(423)
                .build();

        Libro libro2 = Libro.builder()
                .isbn("7854123456")
                .titolo("Il linguaggio dei signori")
                .autore("Uno dei signori")
                .genere("Moderno")
                .annoPubblicazione(1993)
                .numeroPagine(256)
                .build();

        // Creazione di riviste
        Rivista rivista = Rivista.builder()
                .isbn("9876543210")
                .titolo("National Geographic")
                .annoPubblicazione(1888)
                .numeroPagine(100)
                .periodicita(Periodicita.SETTIMANALE)
                .build();

        Rivista rivista2 = Rivista.builder()
                .isbn("255599955")
                .titolo("Scientific American")
                .annoPubblicazione(2025)
                .numeroPagine(50)
                .periodicita(Periodicita.MENSILE)
                .build();

        // Creazione di utenti
        Utente utente = Utente.builder()
                .nome("Mario")
                .cognome("Rossi")
                .dataDiNascita(LocalDate.of(1990, 10, 7))
                .prestiti(new ArrayList<>())
                .build();

        Utente utente2 = Utente.builder()
                .nome("Luigi")
                .cognome("Verdi")
                .dataDiNascita(LocalDate.of(2000, 5, 10))
                .prestiti(new ArrayList<>())
                .build();

        // Creazione di prestiti
        Prestito prestito1 = Prestito.builder()
                .utente(utente)
                .elementoPrestato(libro)
                .dataInizioPrestito(LocalDate.now())
                .dataRestituzioneEffettiva(null)
                .build();
        prestito1.calcolaDataRestituzionePrevista(); // uso il metodo creato nella entity prestito

        Prestito prestito2 = Prestito.builder()
                .utente(utente2)
                .elementoPrestato(rivista)
                .dataInizioPrestito(LocalDate.of(2025, 3, 20))
                .dataRestituzioneEffettiva(null)
                .build();
        prestito2.calcolaDataRestituzionePrevista();

    }
}




