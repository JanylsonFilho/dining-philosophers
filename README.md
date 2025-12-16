# Dining Philosophers

This project is a Java implementation of the classic **Dining Philosophers** problem. It was developed to practice concurrency concepts, specifically thread synchronization, resource sharing, and deadlock prevention.

## Overview

The simulation consists of 5 philosophers sitting around a table, alternating between thinking and eating. To eat, a philosopher needs two forks (one on their left and one on their right). The challenge is to manage the competition for forks (shared resources) without causing deadlocks or starvation.

### Solution Implemented

To solve the concurrency issues, this project implements two main strategies:

1.  **Resource Hierarchy:** Philosophers always attempt to pick up the fork with the lower ID first, followed by the higher ID. This global ordering prevents circular wait conditions.
2.  **Semaphore Limitation:** A `Semaphore` is used to limit the number of philosophers attempting to pick up forks simultaneously to `N-1` (4 in this case). This ensures that at least one philosopher will always be able to acquire both forks.

## Technologies

* Java (JDK 24)
* `java.util.concurrent.Semaphore`
* `java.lang.Thread`
* `synchronized` blocks / `wait()` / `notifyAll()`

## How to Run

1.  Clone the repository.
2.  Compile the source code:
    ```bash
    javac -d bin src/*.java
    ```
3.  Run the application:
    ```bash
    java -cp bin JantarDosFilosofos
    ```

---

# Jantar dos Filósofos

Este projeto é uma implementação em Java do problema clássico do **Jantar dos Filósofos**. Foi desenvolvido para praticar conceitos de concorrência, especificamente sincronização de threads, compartilhamento de recursos e prevenção de deadlocks.

## Visão Geral

A simulação consiste em 5 filósofos sentados ao redor de uma mesa, alternando entre pensar e comer. Para comer, um filósofo precisa de dois garfos (um à sua esquerda e outro à sua direita). O desafio é gerenciar a disputa pelos garfos (recursos compartilhados) sem causar travamentos (deadlocks) ou inanição (starvation).

### Solução Implementada

Para resolver os problemas de concorrência, este projeto implementa duas estratégias principais:

1.  **Hierarquia de Recursos:** Os filósofos sempre tentam pegar primeiro o garfo com o ID menor, seguido pelo de ID maior. Essa ordenação global previne condições de espera circular.
2.  **Limitação via Semáforo:** Um `Semaphore` é utilizado para limitar o número de filósofos tentando pegar garfos simultaneamente a `N-1` (4 neste caso). Isso garante que pelo menos um filósofo sempre conseguirá adquirir ambos os garfos.

## Tecnologias

* Java (JDK 24)
* `java.util.concurrent.Semaphore`
* `java.lang.Thread`
* Blocos `synchronized` / `wait()` / `notifyAll()`

## Como Executar

1.  Clone o repositório.
2.  Compile o código fonte:
    ```bash
    javac -d bin src/*.java
    ```
3.  Execute a aplicação:
    ```bash
    java -cp bin JantarDosFilosofos
    ```
