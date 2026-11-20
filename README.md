# Otimização  de Redes de Computadores

## Como rodar o projeto

1. Clone o repositório 
2. No intellij selecione o sdk Java 25
3. Rode o projeto normalmente

> Atenção
> 
>   A exibição do grafo só irá mostrar as arestas que foram utilizadas

## Exemplos de entrada

### Exemplo 1 todos conectados:
```text
Quantidade de computadores: 4
Computador 1: PC1
Computador 2: PC2
Computador 3: PC3
Computador 4: PC4

Quantidade de conexões possíveis: 5
Conexão 1 - computador de origem: PC1
Conexão 1 - computador de destino: PC2
Custo da conexão: 4

Conexão 2 - computador de origem: PC1
Conexão 2 - computador de destino: PC3
Custo da conexão: 2

Conexão 3 - computador de origem: PC2
Conexão 3 - computador de destino: PC3
Custo da conexão: 5

Conexão 4 - computador de origem: PC2
Conexão 4 - computador de destino: PC4
Custo da conexão: 3

Conexão 5 - computador de origem: PC3
Conexão 5 - computador de destino: PC4
Custo da conexão: 6
```

### Exemplo 2 - mais de uma subarvore:
```text
Quantidade de computadores: 5
Computador 1: A
Computador 2: B
Computador 3: C
Computador 4: D
Computador 5: E

Quantidade de conexões possíveis: 4
Conexão 1 - computador de origem: A
Conexão 1 - computador de destino: B
Custo da conexão: 2

Conexão 2 - computador de origem: B
Conexão 2 - computador de destino: C
Custo da conexão: 3

Conexão 3 - computador de origem: A
Conexão 3 - computador de destino: C
Custo da conexão: 5

Conexão 4 - computador de origem: D
Conexão 4 - computador de destino: E
Custo da conexão: 4
```