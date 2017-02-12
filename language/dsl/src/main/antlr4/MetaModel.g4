grammar MetaModel;

fragment ESC :   '\\' (["\\/bfnrt] | UNICODE) ;
fragment UNICODE : 'u' HEX HEX HEX HEX ;
fragment HEX : [0-9a-fA-F] ;

STRING : '"' (ESC | ~["\\])* '"' | '\'' (ESC | ~["\\])* '\'' ;
IDENT : [a-zA-Z_][a-zA-Z_0-9]*;
WS : ([ \t\r\n]+ | SL_COMMENT) -> skip ; // skip spaces, tabs, newlines
SL_COMMENT :  '//' ~('\r' | '\n')* ;
NUMBER : [\-]?[0-9]+'.'?[0-9]*;

metamodel: ruleDef*;

ruleDef: 'rule' STRING condition action 'end';
condition: 'when' ('!')? (term boolOperator term);
term: (type '.' attribute) | NUMBER | STRING;
boolOperator: ( '==' | '>' | '>=' | '<' | '<=' | '!=');
type: IDENT ('.' IDENT)*;
attribute: IDENT;
action: 'then' task;
task: operation ('.'operation)*;
operation: IDENT '(' (value(',' value)*)? ')';
value: STRING | '{' task '}';
