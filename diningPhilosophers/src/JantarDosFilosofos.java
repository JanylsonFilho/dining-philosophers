import java.util.concurrent.Semaphore;

public class JantarDosFilosofos {
    private static final int NUM_FILOSOFOS = 5;

    public static void main(String[] args) {
        Garfo[] garfos = new Garfo[NUM_FILOSOFOS];
        for (int i = 0; i < NUM_FILOSOFOS; i++) {
            garfos[i] = new Garfo(i);
        }

        // Solução para evitar deadlock: limitar o número de filósofos que podem
        // tentar pegar garfos simultaneamente a N-1. Assim, sempre haverá
        // pelo menos um garfo disponível para o último filósofo que conseguir
        // adquirir a permissão do semáforo.
        Semaphore semaphore = new Semaphore(NUM_FILOSOFOS - 1);

        Filosofo[] filosofos = new Filosofo[NUM_FILOSOFOS];
        Thread[] threads = new Thread[NUM_FILOSOFOS];

        for (int i = 0; i < NUM_FILOSOFOS; i++) {
            Garfo leftFork = garfos[i];
            Garfo rightFork = garfos[(i + 1) % NUM_FILOSOFOS];
            filosofos[i] = new Filosofo(i, leftFork, rightFork, semaphore);
            threads[i] = new Thread(filosofos[i]);
            threads[i].start();
        }

        // Opcional: Adicionar um shutdown hook para parar as threads graciosamente
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            for (Thread thread : threads) {
                thread.interrupt();
            }
            System.out.println("Jantar dos Filósofos encerrado.");
        }));
    }
}

