# Rede de Computadores - Grafo Circuito Euleriano.
Aplicando Teoria de Grafos em uma Rede de Computadores. Exercício desenvolvido para disciplina de Teoria dos Grafos - UFCG.
Aplicado os conceitos de grafo conectado, grafo par e desenvolvimento do algoritmo de Fleury para adquirir a rota.

## Requisitos do Projeto:

Um gerente de uma rede local de computadores necessita medir o tempo necessário para uma mensagem ser transmitida de uma máquina para outra da rede através de uma conexão direta se existir. Para tal, o gerente enviará um programa (agente móvel) a partir da máquina gerente. Este agente é um programa ativo que deverá percorrer todas as conexões diretas entre duas máquina e registrar o tempo gasto para percorrer cada conexão. A fim de minimizar o tráfego na rede, cada conexão deve ser percorrida uma única vez e o agente só retorna à máquina gerente após percorrer todas as conexões.

Usando a API JGraphT (jgrapht.org), implemente um programa que recebe o grafo que descreve uma rede e a indicação da máquina gerente como entrada e utiliza conceitos vistos em sala para:
1 - Determinar se é possível encontrar a rota desejada com as restrições acima descritas.
2 - Se sim, retorne a rota.

![Grafo da Rede de Computadores](rede.png)


## Saída do Programa:
```
::: Estrutura do Grafo GML Importado :::
Ordem do Grafo G: 20
Tamanho do Grafo G: 41
------
V(G): [M20, M19, M2, M10, M16, M13, M17, M11, C, M4, M16, M14, M7, M1, M5, M6, M9, M3, M12, M8]
E(G): [(M3 : M7), (M17 : M3), (M7 : M19), (M4 : M6), (M17 : M5), (M11 : C), (M14 : M5), (M13 : M16), (M8 : C), (M16 : M12), (M2 : M13), (M13 : M6), (M7 : M11), (M6 : M14), (M6 : M2), (M14 : M10), (M8 : M1), (M14 : M16), (M14 : M4), (M17 : M7), (M2 : M3), (M9 : M1), (M3 : M11), (M3 : M1), (M16 : M19), (M11 : M8), (C : M14), (M12 : M17), (C : M5), (M6 : M10), (M6 : M20), (M17 : M19), (M13 : M12), (M11 : M20), (M12 : M3), (M17 : M16), (M8 : M9), (M12 : M5), (M19 : M11), (M16 : M1), (M2 : M12)]
------
Rota da Máquina Gerente(C) - Circuito Euleriano: 
[(C : M14), (M6 : M14), (M13 : M6), (M13 : M12), (M16 : M12), (M17 : M16), (M17 : M19), (M7 : M19), (M3 : M7), (M17 : M3), (M12 : M17), (M12 : M3), (M3 : M11), (M11 : M20), (M6 : M20), (M4 : M6), (M14 : M4), (M14 : M5), (M12 : M5), (M2 : M12), (M2 : M13), (M13 : M16), (M16 : M19), (M19 : M11), (M7 : M11), (M17 : M7), (M17 : M5), (C : M5), (M11 : C), (M11 : M8), (M8 : M9), (M9 : M1), (M3 : M1), (M2 : M3), (M6 : M2), (M6 : M10), (M14 : M10), (M14 : M16), (M16 : M1), (M8 : M1), (M8 : C)]
```
