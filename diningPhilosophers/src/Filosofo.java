import java.util.Random;
import java.util.concurrent.Semaphore;

public class Filosofo implements Runnable {
    private int id;
    private Garfo leftFork;
    private Garfo rightFork;
    private Random random;
    private Semaphore semaphore; // Para controlar o número de filósofos tentando pegar garfos

    public Filosofo(int id, Garfo leftFork, Garfo rightFork, Semaphore semaphore) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.random = new Random();
        this.semaphore = semaphore;
    }

    private void pensar() throws InterruptedException {
        System.out.println(System.currentTimeMillis() + " - Filósofo " + id + " está pensando.");
        Thread.sleep(random.nextInt(1000) + 500); // Pensa por 0.5 a 1.5 segundos
    }

    private void comer() throws InterruptedException {
        System.out.println(System.currentTimeMillis() + " - Filósofo " + id + " está comendo.");
        Thread.sleep(random.nextInt(1000) + 500); // Come por 0.5 a 1.5 segundos
    }

    private void pegarGarfos() throws InterruptedException {
        System.out.println(System.currentTimeMillis() + " - Filósofo " + id + " tenta pegar garfos.");
        // Adquire permissão para tentar pegar garfos. Isso limita o número de filósofos
        // que podem tentar pegar garfos simultaneamente a N-1, prevenindo deadlock.
        semaphore.acquire(); 

        // Para evitar deadlock, o filósofo pega o garfo de menor ID primeiro.
        // Isso garante uma ordenação global na aquisição de recursos.
        Garfo firstFork = (leftFork.getId() < rightFork.getId()) ? leftFork : rightFork;
        Garfo secondFork = (leftFork.getId() < rightFork.getId()) ? rightFork : leftFork;

        // Tenta pegar o primeiro garfo
        firstFork.pickUp(id); 
        System.out.println(System.currentTimeMillis() + " - Filósofo " + id + " pegou o garfo " + firstFork.getId() + ".");

        // Tenta pegar o segundo garfo
        secondFork.pickUp(id); 
        System.out.println(System.currentTimeMillis() + " - Filósofo " + id + " pegou o garfo " + secondFork.getId() + ".");
    }

    private void devolverGarfos() {
        System.out.println(System.currentTimeMillis() + " - Filósofo " + id + " devolve os garfos.");
        // Devolve os garfos
        leftFork.putDown(id);
        System.out.println(System.currentTimeMillis() + " - Filósofo " + id + " devolveu o garfo " + leftFork.getId() + ".");
        rightFork.putDown(id);
        System.out.println(System.currentTimeMillis() + " - Filósofo " + id + " devolveu o garfo " + rightFork.getId() + ".");
        
        semaphore.release(); // Libera a permissão do semáforo
    }

    @Override
    public void run() {
        try {
            while (true) {
                pensar();
                pegarGarfos();
                comer();
                devolverGarfos();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }
    }
}

