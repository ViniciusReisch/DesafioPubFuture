## Repositório para o desafio Pub Future realizado pela Pública Tecnologia!

### Funcionalidades do programa:

- Criar, deletar, selecionar e atualizar contas;
- Transferir saldo entre contas;
- Listar saldo total de contas;
- Criar, deletar, selecionar e atualizar receitas e despesas;


### Como baixar e executar o projeto

- Primeiramente, baixe o projeto pelo Github: ``git clone https://github.com/Flyinng/DesafioPubFuture``
- Após a instação do projeto, execute-o conforme sua IDE.

## Rotas do projeto

### CRUD para contas

#### Criar uma nova conta
- URL: ``http://localhost:8080/accounts/create``   
- Body: ``{ "saldo": 1.564, "tipoConta": "Conta poupança", "instituicaoFinanceira": "Insituição financeira de Blumenau" }``
#### Deletar uma conta
- URL: ``http://localhost:8080/accounts/delete/{id}``   
#### Atualizar uma conta
- URL: ``http://localhost:8080/accounts/edit/{id}``
- Body: ``{ "saldo": 1.564, "tipoConta": "Conta poupança atualizada", "instituicaoFinanceira": "Insituição financeira de Blumenau" }``
#### Obter todas as contas
- URL: ``http://localhost:8080/accounts/all``
#### Obter saldo total de uma conta
- URL: ``http://localhost:8080/accounts/total-balance/1``
#### Fazer uma transação de saldo entre duas contas
- URL: ``http://localhost:8080/accounts/transaction/{idDaContaEmissora}/{idDaContaReceptora}``
- Body: ``{ "saldo": 10 }``

As rotas e body de despesas e receitas podem ser encontradas nos Controllers e seguem o mesmo padrão das de Conta.
