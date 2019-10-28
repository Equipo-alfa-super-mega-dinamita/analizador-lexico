package com.superdinamita.parser;


import com.superdinamita.lexer.Token;
import com.superdinamita.lexer.TokenType;

import java.util.List;
import java.util.Set;



public abstract class Symbol {

     String value;
     boolean hasEmpty;
     abstract void eval(SyntaxAnalizer g) throws Exception;

    abstract void eval(SyntaxAnalizer g) throws Exception;
    /*public Symbol(String s)  {

        if(s.matches("[A-Za-z][A-Za-z0-9]*")){ //Formato propio de nuestra sintaxis de gramáticas.
            value = s;
        }
        else if ( s.matches(  "\\{(.*)}")  ){
            value = s.substring(1, s.length() -1).trim();
        }
        else if (s.equals("?")) {
            value = ;
        }
    }*/
     public abstract Set<TokenType> firsts();

    public void syntaxError(List<Rule> reglasEsperadas, SyntaxAnalizer syntaxAnalizer) throws Exception {
        //<{linea},{col}> Error sintactico:
        // se encontro: {lexema del token encontrado};
        // se esperaba: {lista de símbolos/tokens esperados separados por comas.}
        String expectedTokens = "";
        Token received = syntaxAnalizer.token();
        for (Rule rule : reglasEsperadas) {
            //TODO Concatenar conjuntos de prediccion a expectedTokens convertidos
        }
        throw new Exception("<" + received.row + "," + received.column + "> Error sintactico: se encontro: " + received.lexeme
                + "; Se esperaba: " + expectedTokens);

    }

    static String mapTypeToExpected(TokenType type) {
        switch (type) {
            case none:
                return "this shouldn't happen D:";
            case tk_id:
                return "identifier";
            case tk_eof:
                return "end of file";
            case tk_cadena:
                return "string";
            case tk_caracter:
                return "character";
            case tk_P:
                return "P token";
            case tk_V:
                return "V token";
            case tk_af:
                return "af";
            case tk_and:
                return "and or &&";
            case tk_any:
                return "any";
            case tk_begin:
                return "begin";
            case tk_body:
                return "body";
            case tk_bool:
                return "bool";
            case tk_by:
                return "by";
            case tk_call:
                return "call";
            case tk_cap:
                return "cap";
            case tk_char:
                return "char";
            case tk_co:
                return "co";
            case tk_const:
                return "const";
            case tk_create:
                return "create";
            case tk_destroy:
                return "destroy";
            case tk_do:
                return "do";
            case tk_downto:
                return "downto";
            case tk_else:
                return "else";
            case tk_end:
                return "end";
            case tk_enum:
                return "enum";
            case tk_exit:
                return "exit";
            case tk_extend:
                return "extend";
            case tk_external:
                return "external";
            case tk_fa:
                return "fa";
            case tk_false:
                return "false";
            case tk_fi:
                return "fi";
            case tk_file:
                return "file";
            case tk_final:
                return "final";
            case tk_forward:
                return "forward";
            case tk_global:
                return "global";
            case tk_getarg:
                return "getarg";
            case tk_high:
                return "high";
            case tk_if:
                return "if";
            case tk_import:
                return "import";
            case tk_in:
                return "in";
            case tk_initial:
                return "initial";
            case tk_int:
                return "int";
            case tk_low:
                return "low";
            case tk_mod:
                return "mod";
            case tk_new:
                return "new";
            case tk_next:
                return "next";
            case tk_ni:
                return "ni";
            case tk_noop:
                return "noop";
            case tk_not:
                return "not";
            case tk_null:
                return "null";
            case tk_oc:
                return "oc";
            case tk_od:
                return "od";
            case tk_on:
                return "on";
            case tk_op:
                return "op";
            case tk_optype:
                return "optype";
            case tk_or:
                return "or";
            case tk_proc:
                return "proc";
            case tk_procedure:
                return "procedure";
            case tk_process:
                return "process";
            case tk_ptr:
                return "ptr";
            case tk_read:
                return "read";
            case tk_real:
                return "real";
            case tk_rec:
                return "rec";
            case tk_receive:
                return "receive";
            case tk_ref:
                return "ref";
            case tk_reply:
                return "reply";
            case tk_res:
                return "res";
            case tk_resource:
                return "resource";
            case tk_return:
                return "return";
            case tk_returns:
                return "returns";
            case tk_sem:
                return "sem";
            case tk_send:
                return "send";
            case tk_separate:
                return "separate";
            case tk_skip:
                return "skip";
            case tk_st:
                return "st";
            case tk_stderr:
                return "stderr";
            case tk_stdin:
                return "stdin";
            case tk_stdout:
                return "stdout";
            case tk_stop:
                return "stop";
            case tk_string:
                return "string";
            case tk_to:
                return "to";
            case tk_true:
                return "true";
            case tk_type:
                return "type";
            case tk_union:
                return "union";
            case tk_val:
                return "val";
            case tk_var:
                return "var";
            case tk_vm:
                return "vm";
            case tk_write:
                return "write";
            case tk_xor:
                return "xor";
            case tk_semicolon:
                return ";";
            case tk_comma:
                return ",";
            case tk_colon:
                return ":";
            case tk_eq:
                return "=";
            case tk_incr:
                return "++";
            case tk_plus:
                return "+";
            case tk_decr:
                return "--";
            case tk_minus:
                return "-";
            case tk_aster:
                return "*";
            case tk_expon:
                return "**";
            case tk_div:
                return "/";
            case tk_remdr:
                return "%";
            case tk_lparen:
                return "(";
            case tk_rparen:
                return ")";
            case tk_arrow:
                return "->";
            case tk_square:
                return "[]";
            case tk_assign:
                return ":=";
            case tk_swap:
                return ":=:";
            case tk_lbracket:
                return "[";
            case tk_rbracket:
                return "]";
            case tk_ge:
                return ">=";
            case tk_le:
                return "<=";
            case tk_gt:
                return ">";
            case tk_lt:
                return "<";
            case tk_ne:
                return "!= or ~=";
            case tk_period:
                return ".";
            case tk_addr:
                return "@";
            case tk_hat:
                return "^";
            case tk_concat:
                return "||";
            case tk_lbrace:
                return "{";
            case tk_rbrace:
                return "}";
            case tk_qmark:
                return "?";
            case tk_parallel:
                return "//";
            case tk_rshift:
                return ">>";
            case tk_lshift:
                return "<<";
            case tk_aug_plus:
                return "+:=";
            case tk_aug_minus:
                return "%:=";
            case tk_aug_aster:
                return ">>:=";
            case tk_aug_expon:
                return "**:=";
            case tk_aug_div:
                return "/:=";
            case tk_aug_remdr:
                return "%:=";
            case tk_aug_or:
                return "|:=";
            case tk_aug_and:
                return "&:=";
            case tk_aug_concat:
                return "||:=";
            case tk_aug_rshift:
                return ">>:=";
            case tk_aug_lshift:
                return "<<:=";
            case tk_num_real:
                return "numero real";
            case tk_num_int_oct:
                return "numero entero(octal)";
            case tk_num_int_dec:
                return "numero entero(decimal)";
            case tk_num_int_hex:
                return "numero entero(hexadecimal)";
            default:
                return "this shouldn't happen either";
        }
    }


}










    /*public Symbol(String s)  {

        if(s.matches("[A-Za-z][A-Za-z0-9]*")){ //Formato propio de nuestra sintaxis de gramáticas.
            value = s;
        }
        else if ( s.matches(  "\\{(.*)}")  ){
            value = s.substring(1, s.length() -1).trim();
        }
        else if (s.equals("?")) {
            value = ;
        }
    }*/


/*

Pseudo codigo
        if( this.type == terminal ) {
            //////// emparejar(token);
            //if(!this.soyvacio) nuevotoken;
        }
        else if( this.type == variable){

            //evaluar error si nunguna regla tiene.
        }
*/

/*
    void emparejar(TokenType t){

    }*/