# dl-short-4
Este projeto é uma versão resumida da linguagem DL.  
Esta versao faz as análises léxica, sintática e semântica e a geração de código intermediário.  
Ela está de acordo com a gramática abaixo.  

## Gramática
PROGRAM				::= programa ID BLOCK  
BLOCK				::= inicio STMTS fim  
STMTS				::= STMT; STMTS | ε  
STMT				::= BLOCK | DECL | ASSIGN | WRITE | IF  
DECL     			::= TYPE ID  
ASSIGN   			::= ID = EXPR  
WRITE				::= escreva(ID)  
IF					::= se (EXPR) STMT  
EXPR				::= EXPR "|" REL | REL  
REL					::= REL < ARITH | REL <= ARITH | REL > ARITH | ARITH  
ARITH  				::= ARITH + TERM | ARITH - TERM | TERM  
TERM				::= TERM * FACTOR | FACTOR  
FACTOR				::= (EXPR) | ID | LIT_INT | LIT_REAL | LIT_BOOL | LIT_ROMANO

## Definições Regulares
LETTER		::= a | b | ... | z | A | B | ... Z | _  
DIGIT		::= 0 | 1 | ... | 9  
ID			::= LETTER (LETTER | DIGIT)*  
LIT_INT		::= DIGIT+  
LIT_REAL	::= DIGIT+ . DIGIT+   
LIT_BOOL	::= verdadeiro | falso  
LIT_ROMANO  ::= 0r LETTER+
TYPE     	::= inteiro | real | booleano | romano

## Comentários
Não conseguimos fazer a B e, por consequencia, não conseguimos fazer a C.
Nós lemos o capítulo do livro e entendemos o funcionamento da tabela de símbolos, porém não conseguimos implementar no compilador.
Pedimos ajuda de alguns colegas mas ninguém conseguiu ajudar.