# desafio-ciee-back_end
 
 dificuldades: -Conectar banco, 
 configurações gerais do projeto, 
 Problemas com serialização dos obj(s) no momento de retonar resposta.
 primeria vez que crio uma relação "muitos para muitos"

projeto está separado em pacotes services, repository, rest e entity para desacoplar codigo;
todos os métodos contam com tratamento de exeções (try catch) retornando um erro decente para o front.

para consultas ao banco foi usado a orm Hibernate, as consultas são feitas por querys ou usando abstrações do JPA.
