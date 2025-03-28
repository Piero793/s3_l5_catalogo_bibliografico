package it.epicode.exception;

public class PrestitoDAOException extends RuntimeException {

    // Costruttore che accetta solo il messaggio di errore
    public PrestitoDAOException(String message) {
        super(message);
    }

    // Eccezione per errori durante l'aggiunta di un prestito
    public static class AggiuntaPrestitoException extends PrestitoDAOException {
        public AggiuntaPrestitoException(String message) {
            super(message);
        }
    }

    // Eccezione per errori durante la ricerca di un prestito per ID
    public static class RicercaPrestitoPerIdException extends PrestitoDAOException {
        public RicercaPrestitoPerIdException(String message) {
            super(message);
        }
    }
}
