![image](https://github.com/user-attachments/assets/51ec19cf-f20b-4833-8858-124529059c97)

# TOS API - Terminal Operations System
Este projeto está sendo desenvolvido como parte da avaliação para a disciplina de Desenvolvimento para Servidores 2 (Projeto P2). A aplicação consiste em uma API RESTful para a gestão de operações em um terminal portuário, com foco em contêineres, embarques e clientes. A API permite realizar o controle de clientes, navios, contêineres e embarques de maneira eficiente, garantindo a maturidade e o tratamento de erros adequado.

## Funcionalidades Específicas:
#### Controle de Operações Portuárias: 
Gerenciamento da movimentação de contêineres, incluindo carga e descarga de navios.
#### Rastreamento de Contêineres: 
Verificar a localização e o status de contêineres em tempo real.
#### Gestão de Navios: 
Manter um registro de navios que utilizam o terminal, incluindo sua capacidade e status.

### Exemplo de Cenário:
Cliente faz uma solicitação de embarque para enviar dois contêineres para outro porto.
Contêineres são alocados ao embarque e marcados como "Em trânsito".
O navio associado ao embarque é atualizado com o status "Em trânsito" durante a viagem.
Operações portuárias registram o carregamento e descarregamento dos contêineres.
Ao final do processo, os contêineres e o embarque são marcados como "Concluídos".
