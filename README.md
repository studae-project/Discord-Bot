# Studae Discord Bot
Esse repositório armazena o código fonte do bot utilizado no grupo do Discord Studae.

# Contribuindo com o bot do discord da Studae
Ao contribuir com esse repositório, você concorda com:
1. O código é de livre acesso ao público;
2. O código poderá ser usado pela comunidade Studae;
3. O bot é público e você, apesar de contribuir, não pode reivindicar autoria por ele;
4. O nome das branchs, mensagens dos commits e comentários nos arquivos devem, **OBRIGATORIAMENTE**, estarem em portugês. Somente os nomes das classes, dos atributos e das variáveis e dos métodos devem estar, **OBRIGATORIAMENTE**, em inglês;
5. Você concorda em seguir o guia exposto abaixo de como contribuir com esse repositório.

## Enviando mudanças de código
Todos as solicitações de mudança ou implementação de novas funcionalidades no código deverão ser enviadas por **_pull request_ (PR)**, que será analisado pela equipe responsável por manter o repositório.

1. Para realizar um PR, você primeiro precisará clonar este repositório.
2. Em seguida, crie uma **_branch_** que explique o que foi feito [\(veja adiante\)](#criando-um-novo-branch) e edite o código nela mesma;
3. De preferência, faça **_commits_** de arquivos que executam funções semelhantes (por exemplo, se você alterou X arquivos que fazem ação Y, faça um só commit incluindo todos eles, no entanto, que exclua outros arquivos alterados que não fazem a mesma ação);
4. Faça o **_push_** para o seu repositório;
5. Crie o PR em nosso repositório.
### Clonando o repositório
1. Clique no botão **Fork**, disposto acima.
2. Rode o comando `git clone https://github.com/[seu nome de usuário no github]/Discord-Bot.git.git` no terminal

### Criando um novo branch
O nome do seu branch deve explicar o que foi feito, seguindo a seguinte regra:
* Para PR que farão correções de bugs, utilizar o prefixo `correcao/<o que está sendo corrigido>`;
* Para PR que adicionarão funcionalidades ao bot, utilizar o prefixo `funcionalidade/<o que está sendo adicionado>`.

**\* Exemplo¹:** se você está corrigindo um bug de mensagem quando um usuário entra, você pode utilizar uma branch com o nome `correcao/erro-mensagem-ao-entrar`;

**\* Exemplo²:** se você está adicionando um comando ao bot, você pode utilizar uma branch com o nome `funcionalidade/comando-<o que o comando faz>`;

### Commit
1. Rode o comando `git add [arquivos alterados]` para adicionar os arquivos à fila de commit;
2. Rode o comando `git commit -m "[uma descrição do commit]"`;
3. Envie o(s) commit(s) para o seu repositório utlizando `git push origin <branch que você criou>`;

### Pull Request
1. Vá até `https://github.com/[seu nome de usuário no github]/Discord-Bot.git` e clique em "Pull Requests" e então em "Novo Pull Request";
2. Crie o novo PR com o branch que você criou.
3. Aguarda a análise pela equipe 
