public class Garfo {
    private int id;
    private boolean isAvailable;

    public Garfo(int id) {
        this.id = id;
        this.isAvailable = true;
    }

    public int getId() {
        return id;
    }

    public synchronized void pickUp(int filosofoId) throws InterruptedException {
        System.out.println(System.currentTimeMillis() + " - Filósofo " + filosofoId + " tenta pegar o garfo " + id + ".");
        while (!isAvailable) {
            System.out.println(System.currentTimeMillis() + " - Filósofo " + filosofoId + " está esperando pelo garfo " + id + ".");
            wait(); // Espera até que o garfo seja liberado
        }
        isAvailable = false;
        System.out.println(System.currentTimeMillis() + " - Garfo " + id + " foi pego pelo Filósofo " + filosofoId + ".");
    }

    public synchronized void putDown(int filosofoId) {
        isAvailable = true;
        System.out.println(System.currentTimeMillis() + " - Garfo " + id + " foi devolvido pelo Filósofo " + filosofoId + ".");
        notifyAll(); // Notifica todos os filósofos esperando por este garfo
    }
}

