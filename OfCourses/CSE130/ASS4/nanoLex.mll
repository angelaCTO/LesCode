{
  open Nano        (* nano.ml *)
  open NanoParse   (* nanoParse.ml from nanoParse.mly *)
}

(* DEFINITIONS *)                       (* TOKENS *)
rule token = parse
  | eof                                 { EOF   }
  | "true"                              { TRUE  }
  | "false"                             { FALSE }
  | [' ' '\t' '\n' '\r']                { token lexbuf }
  | "let"                               { LET }
  | "rec"                               { REC }
  | "fun"                               { FUN }
  | "="                                 { EQ }
  | "in"                                { IN }
  | "->"                                { ARROW }
  | "if"                                { IF }
  | "then"                              { THEN }
  | "else"                              { ELSE }
  | "+"                                 { PLUS }
  | "-"                                 { MINUS }
  | "*"                                 { MUL }
  | "/"                                 { DIV }
  | "<"                                 { LT }
  | "<="                                { LE }
  | "!="                                { NE }
  | "&&"                                { AND }
  | "||"                                { OR }
  | "("                                 { LPAREN }
  | ")"                                 { RPAREN }
  | "]"                                 { RBRAC }
  | "["                                 { LBRAC }
  | ";"                                 { SEMI }
  | "::"                                { COLONCOLON }
  | ['A'-'Z''a'-'z']+ 
    ['A'-'Z''a'-'z''0'-'9']* 
    as x                                { Id(x) }
  | ['0'-'9']+ 
    as x                                { Num(int_of_string x) }
  | _                                   { raise (MLFailure ("Error: Illegal Character")) }
