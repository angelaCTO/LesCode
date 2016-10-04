%{
(* See this for a tutorial on ocamlyacc
 * http://plus.kaist.ac.kr/~shoh/ocaml/ocamllex-ocamlyacc/ocamlyacc-tutorial/ *)
open Nano
%}

%token <int> Num
%token EOF
%token TRUE 
%token FALSE
%token <string> Id
%token LET 
%token REC 
%token EQ 
%token IN 
%token FUN 
%token ARROW 
%token IF 
%token THEN 
%token ELSE
%token PLUS 
%token MINUS 
%token MUL 
%token DIV 
%token LT 
%token LE 
%token NE 
%token AND
%token OR
%token LPAREN 
%token RPAREN
%token LBRAC 
%token RBRAC 
%token SEMI 
%token COLONCOLON

%nonassoc LET 
%nonassoc FUN 
%nonassoc IF
%left EQ 
%left LT 
%left LE 
%left NE 
%left OR 
%left AND 
%left MINUS 
%left PLUS 
%left MUL 
%left DIV 
%left APPLY
%right COLONCOLON


%start exp
%type <Nano.expr> exp

%%

list:
  | LBRAC RBRAC               { NilExpr }
  | LBRAC exp1 RBRAC          { $2 }
  | exp COLONCOLON exp        { Bin ($1, Cons, $3) }

exp:
  | exp exp2 %prec APPLY      { App ($1, $2) }
  | exp2                      { $1 }

exp1:
  | exp SEMI exp1             { Bin ($1, Cons, $3) }
  | exp                       { Bin ($1, Cons, NilExpr) }

exp2:
  | IF exp THEN exp ELSE exp  { If ($2, $4, $6) }
  | FUN Id ARROW exp          { Fun ($2, $4) }
  | LPAREN exp RPAREN         { $2 }
  | list                      { $1 }
  | exp PLUS  exp             { Bin ($1, Plus, $3) }
  | exp MINUS exp             { Bin ($1, Minus, $3) }
  | exp MUL   exp             { Bin ($1, Mul, $3) }
  | exp DIV   exp             { Bin ($1, Div, $3) }
  | exp LT    exp             { Bin ($1, Lt, $3) }
  | exp LE    exp             { Bin ($1, Le, $3) }
  | exp NE    exp             { Bin ($1, Ne, $3) }
  | exp AND   exp             { Bin ($1, And, $3) }
  | exp OR    exp             { Bin ($1, Or, $3) }
  | exp EQ    exp             { Bin ($1, Eq, $3) }
  | Num                       { Const $1 }
  | TRUE                      { True }
  | FALSE                     { False }
  | Id                        { Var $1 }
  | LET Id EQ exp IN exp      { Let ($2, $4, $6) }
  | LET REC Id EQ exp IN exp  { Letrec ($3, $5, $7) }
