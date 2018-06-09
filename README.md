# Rede de Computadores - Grafo Circuito Euleriano.
Aplicando Teoria de Grafos em uma Rede de Computadores. Exercício desenvolvido para disciplina de Teoria dos Grafos - UFCG.

## Requisitos do Projeto:

Um gerente de uma rede local de computadores necessita medir o tempo necessário para uma mensagem ser transmitida de uma máquina para outra da rede através de uma conexão direta se existir. Para tal, o gerente enviará um programa (agente móvel) a partir da máquina gerente. Este agente é um programa ativo que deverá percorrer todas as conexões diretas entre duas máquina e registrar o tempo gasto para percorrer cada conexão. A fim de minimizar o tráfego na rede, cada conexão deve ser percorrida uma única vez e o agente só retorna à máquina gerente após percorrer todas as conexões.

Usando a API JGraphT (jgrapht.org), implemente um programa que recebe o grafo que descreve uma rede e a indicação da máquina gerente como entrada e utiliza conceitos vistos em sala para:
1 - Determinar se é possível encontrar a rota desejada com as restrições acima descritas.
2 - Se sim, retorne a rota.

![Grafo da Rede de Computadores](rede.png)
