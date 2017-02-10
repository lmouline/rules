grammar MetaModel;

fragment ESC :   '\\' (["\\/bfnrt] | UNICODE) ;
fragment UNICODE : 'u' HEX HEX HEX HEX ;
fragment HEX : [0-9a-fA-F] ;

STRING : '"' (ESC | ~["\\])* '"' | '\'' (ESC | ~["\\])* '\'' ;
IDENT : [a-zA-Z_][a-zA-Z_0-9]*;
WS : ([ \t\r\n]+ | SL_COMMENT) -> skip ; // skip spaces, tabs, newlines
SL_COMMENT :  '//' ~('\r' | '\n')* ;

metamodel: ruleDef*;

ruleDef: 'rule' STRING condition action;
condition: 'when' type'.'attribute;
type: IDENT ('.' IDENT)*;
attribute: IDENT;
action: 'then' task;
task: taskAction ('.'taskAction)*;
taskAction: IDENT '(' (value(',' value)*)? ')';
value: STRING | '{' task '}';
