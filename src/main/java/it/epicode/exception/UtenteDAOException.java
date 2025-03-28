package it.epicode.exception;

public class UtenteDAOException extends RuntimeException {

    // Costruttore che accetta solo il messaggio di errore
    public UtenteDAOException(String message) {
        super(message);
    }

    // Eccezione per errori durante l'aggiunta di un utente
    public static class AggiuntaUtenteException extends UtenteDAOException {
        public AggiuntaUtenteException(String message) {
            super(message);
        }
    }

    // Eccezione per errori durante la ricerca di un utente per numero di tessera
    public static class RicercaUtentePerTesseraException extends UtenteDAOException {
        public RicercaUtentePerTesseraException(String message) {
            super(message);
        }
    }

    // Eccezione per errori durante la rimozione di un utente
    public static class RimozioneUtenteException extends UtenteDAOException {
        public RimozioneUtenteException(String message) {
            super(message);
        }
    }
}
