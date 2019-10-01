package lexicalAnalyzer;

import symboltable.SymbolTable;

import javax.swing.*;
import java.util.regex.Pattern;

public class LexicalAnalyzer {
    int tokenRow;
    int tokenColumn;
    ScriptReader reader;
    SymbolTable symbolTable;

    Token currentToken;
    char lastChar;

    public LexicalAnalyzer(String fileName) {
        this.reader = new ScriptReader(fileName);
        lastChar = reader.getNextChar();
        this.symbolTable = new SymbolTable();
    }

    private void ignoreEmptySpacesAndLineComments() {
        //ignorar espacios
        while (lastChar == ' ' || lastChar == '\n' || lastChar == '\t') {
            lastChar = reader.getNextChar();
        }
        //Comentario de linea
        while (lastChar == '#') {
            reader.nextRow();
            lastChar = reader.getNextChar();
        }
    }

    private void ignoreMultiLineComments() {
        //comentario multiple linea =======> por revisar
        while (lastChar == '/') {
            int i = reader.getRow();
            int j = reader.getColumn();
            char aux = reader.getNextChar();
            if (aux == '*') {
                boolean flash = true;
                while (flash) {
                    lastChar = reader.getNextChar();
                    if (lastChar == '*') {
                        lastChar = reader.getNextChar();
                        if (lastChar == '/') {
                            flash = false;
                            lastChar = reader.getNextChar();
                            ignoreEmptySpacesAndLineComments();
                        }
                    } else if (lastChar == '¶') {
                        return;
                    }
                }
            } else {
                reader.setRowColumn(i, j);
                return;
            }
        }
        return;
    }


    Token nextToken() {
        ignoreEmptySpacesAndLineComments();
        ignoreMultiLineComments();
        ignoreEmptySpacesAndLineComments();
        tokenRow = reader.getRow();
        tokenColumn = reader.getColumn();
        //caracter es EOF?
        if (lastChar == '¶') {
            return new Token(tokenRow, tokenColumn, "EOF", TokenType.tk_eof);
        }
        switch (lastChar) {
            case ',':
                lastChar = reader.getNextChar();
                return new Token(tokenRow, tokenColumn, ",", TokenType.tk_comma);
            case ';':
                lastChar = reader.getNextChar();
                return new Token(tokenRow, tokenColumn, ";", TokenType.tk_semicolon);

            case ':':
                lastChar = reader.getNextChar();
                if (lastChar == '=') {
                    lastChar = reader.getNextChar();
                    if (lastChar == ':') {
                        lastChar = reader.getNextChar();
                        return new Token(tokenRow, tokenColumn, ":=:", TokenType.tk_swap);
                    } else {
                        return new Token(tokenRow, tokenColumn, ":=", TokenType.tk_assign);
                    }
                } else {
                    return new Token(tokenRow, tokenColumn, ":", TokenType.tk_colon);
                }

            case '-':
                lastChar = reader.getNextChar();
                if (lastChar == '-') {
                    lastChar = reader.getNextChar();
                    return new Token(tokenRow, tokenColumn, "--", TokenType.tk_decr);
                } else if (lastChar == ':') {
                    lastChar = reader.getNextChar();
                    if (lastChar == '=') {
                        lastChar = reader.getNextChar();
                        return new Token(tokenRow, tokenColumn, "=", TokenType.tk_aug_minus);
                    } else {
                        return new Token(tokenRow, tokenColumn, "", TokenType.ERROR);
                    }
                } else if (lastChar == '>') {
                    lastChar = reader.getNextChar();
                    return new Token(tokenRow, tokenColumn, "->", TokenType.tk_arrow);
                } else {
                    return new Token(tokenRow, tokenColumn, "-", TokenType.tk_minus);
                }

            case '?':
                lastChar = reader.getNextChar();
                return new Token(tokenRow, tokenColumn, "?", TokenType.tk_qmark);

            //case '.':  TO-DO  El punto es problemático por los números reales.
            case '(':
                lastChar = reader.getNextChar();
                return new Token(tokenRow, tokenColumn, "(", TokenType.tk_lparen);
            case ')':
                lastChar = reader.getNextChar();
                return new Token(tokenRow, tokenColumn, ")", TokenType.tk_rparen);
            case '[':
                lastChar = reader.getNextChar();
                if (lastChar == ']') {
                    lastChar = reader.getNextChar();
                    return new Token(tokenRow, tokenColumn, "[]", TokenType.tk_square);
                } else {
                    return new Token(tokenRow, tokenColumn, "[", TokenType.tk_lbracket);
                }
            case ']':
                lastChar = reader.getNextChar();
                return new Token(tokenRow, tokenColumn, "]", TokenType.tk_rbracket);
            case '{':
                lastChar = reader.getNextChar();
                return new Token(tokenRow, tokenColumn, "{", TokenType.tk_lbrace);
            case '}':
                lastChar = reader.getNextChar();
                return new Token(tokenRow, tokenColumn, "}", TokenType.tk_rbrace);
            case '@':
                lastChar = reader.getNextChar();
                return new Token(tokenRow, tokenColumn, "@", TokenType.tk_addr);

            case '*':
                lastChar = reader.getNextChar();
                if (lastChar == '*') {
                    lastChar = reader.getNextChar();
                    if (lastChar == ':') {
                        lastChar = reader.getNextChar();
                        if (lastChar == '=') {
                            lastChar = reader.getNextChar();
                            new Token(tokenRow, tokenColumn, "**:=", TokenType.tk_aug_expon);
                        } else {
                            new Token(tokenRow, tokenColumn, "", TokenType.ERROR);
                        }
                    } else {
                        return new Token(tokenRow, tokenColumn, "**", TokenType.tk_expon);
                    }
                } else if (lastChar == ':') {
                    lastChar = reader.getNextChar();
                    if (lastChar == '=') {
                        lastChar = reader.getNextChar();
                        return new Token(tokenRow, tokenColumn, "*:=", TokenType.tk_aug_aster);
                    } else {
                        return new Token(tokenRow, tokenColumn, "", TokenType.ERROR);
                    }
                } else {
                    lastChar = reader.getNextChar();
                    return new Token(tokenRow, tokenColumn, "*", TokenType.tk_aster);
                }
            case '/':
                lastChar = reader.getNextChar();
                if (lastChar == ':') {
                    lastChar = reader.getNextChar();
                    if (lastChar == '=') {
                        lastChar = reader.getNextChar();
                        return new Token(tokenRow, tokenColumn, "/:=", TokenType.tk_aug_div);
                    } else {
                        return new Token(tokenRow, tokenColumn, "", TokenType.ERROR);
                    }
                } else if (lastChar == '/') {
                    lastChar = reader.getNextChar();
                    return new Token(tokenRow, tokenColumn, "//", TokenType.tk_parallel);
                } else {
                    return new Token(tokenRow, tokenColumn, "/", TokenType.tk_div);
                }
            case '&':
                lastChar = reader.getNextChar();
                if (lastChar == ':') {
                    lastChar = reader.getNextChar();
                    if (lastChar == '=') {
                        lastChar = reader.getNextChar();
                        return new Token(tokenRow, tokenColumn, "&:=", TokenType.tk_aug_and);
                    } else {
                        return new Token(tokenRow, tokenColumn, "", TokenType.ERROR);
                    }
                } else {
                    return new Token(tokenRow, tokenColumn, "&", TokenType.tk_and);
                }
            case '%':
                lastChar = reader.getNextChar();
                if (lastChar == ':') {
                    lastChar = reader.getNextChar();
                    if (lastChar == '=') {
                        lastChar = reader.getNextChar();
                        return new Token(tokenRow, tokenColumn, "%:=", TokenType.tk_aug_remdr);
                    } else {
                        return new Token(tokenRow, tokenColumn, "", TokenType.ERROR);
                    }
                } else {
                    return new Token(tokenRow, tokenColumn, "%", TokenType.tk_remdr);
                }

            case '^':
                lastChar = reader.getNextChar();
                return new Token(tokenRow, tokenColumn, "^", TokenType.tk_hat);

            case '+':
                lastChar = reader.getNextChar();
                if (lastChar == ':') {
                    lastChar = reader.getNextChar();
                    if (lastChar == '=') {
                        lastChar = reader.getNextChar();
                        return new Token(tokenRow, tokenColumn, "+:=", TokenType.tk_aug_plus);
                    } else {
                        return new Token(tokenRow, tokenColumn, "", TokenType.ERROR);
                    }
                } else if (lastChar == '+') {
                    lastChar = reader.getNextChar();
                    return new Token(tokenRow, tokenColumn, "++", TokenType.tk_incr);
                } else {
                    return new Token(tokenRow, tokenColumn, "+", TokenType.tk_plus);
                }
            case '<':
                lastChar = reader.getNextChar();
                if (lastChar == '<') {
                    lastChar = reader.getNextChar();
                    if (lastChar == ':') {
                        lastChar = reader.getNextChar();
                        if (lastChar == '=') {
                            lastChar = reader.getNextChar();
                            return new Token(tokenRow, tokenColumn, "<<:=", TokenType.tk_aug_lshift);
                        } else {
                            return new Token(tokenRow, tokenColumn, "", TokenType.ERROR);
                        }
                    } else {
                        return new Token(tokenRow, tokenColumn, "<<", TokenType.tk_lshift);
                    }
                } else if (lastChar == '=') {
                    lastChar = reader.getNextChar();
                    return new Token(tokenRow, tokenColumn, "<=", TokenType.tk_le);
                } else {
                    return new Token(tokenRow, tokenColumn, "<", TokenType.tk_lt);
                }
            case '=':
                lastChar = reader.getNextChar();
                return new Token(tokenRow, tokenColumn, "=", TokenType.tk_eq);
            case '>':
                lastChar = reader.getNextChar();
                if (lastChar == '>') {
                    lastChar = reader.getNextChar();
                    if (lastChar == ':') {
                        lastChar = reader.getNextChar();
                        if (lastChar == '=') {
                            lastChar = reader.getNextChar();
                            return new Token(tokenRow, tokenColumn, ">>:=", TokenType.tk_aug_rshift);
                        } else {
                            return new Token(tokenRow, tokenColumn, "", TokenType.ERROR);
                        }
                    } else {
                        return new Token(tokenRow, tokenColumn, ">>", TokenType.tk_rshift);
                    }
                } else if (lastChar == '=') {
                    lastChar = reader.getNextChar();
                    return new Token(tokenRow, tokenColumn, ">=", TokenType.tk_ge);
                } else {
                    return new Token(tokenRow, tokenColumn, ">", TokenType.tk_gt);
                }

            case '|':
                lastChar = reader.getNextChar();
                if (lastChar == '|') {
                    lastChar = reader.getNextChar();
                    if (lastChar == ':') {
                        lastChar = reader.getNextChar();
                        if (lastChar == '=') {
                            lastChar = reader.getNextChar();
                            return new Token(tokenRow, tokenColumn, "||:=", TokenType.tk_aug_concat);
                        } else {
                            return new Token(tokenRow, tokenColumn, "ERROR", TokenType.ERROR);
                        }
                    } else {
                        return new Token(tokenRow, tokenColumn, "||", TokenType.tk_concat);
                    }
                } else if (lastChar == ':') {
                    lastChar = reader.getNextChar();
                    if (lastChar == '=') {
                        lastChar = reader.getNextChar();
                        return new Token(tokenRow, tokenColumn, "|:=", TokenType.tk_aug_or);
                    } else {
                        return new Token(tokenRow, tokenColumn, "ERROR", TokenType.ERROR);
                    }
                } else {
                    return new Token(tokenRow, tokenColumn, "|", TokenType.tk_or);
                }

            case '~':
                lastChar = reader.getNextChar();
                if (lastChar == '=') {
                    lastChar = reader.getNextChar();
                    return new Token(tokenRow, tokenColumn, "~=", TokenType.tk_ne);
                } else {
                    return new Token(tokenRow, tokenColumn, "~", TokenType.tk_not);
                }
            default:
                if (Pattern.matches("[a-zA-Z]", Character.toString(lastChar))) {
                    return identifyKeywordOrIdentifier();
                }
                if (Pattern.matches("[0-9/.]", Character.toString(lastChar))) {
                    return identifyNumber();

                }

        }


        return new Token(tokenRow, tokenColumn, "ERROR", TokenType.ERROR);
    }

    //

    Token identifyNumber() {
        String lexema = "";
        boolean tokenFound = false;
        String state = "start";
        TokenType num_type = TokenType.ERROR;

        while (!tokenFound) {
            System.out.println(state);
            switch (state) {
                case "start":
                    if (lastChar >= '0' && lastChar <= '7') {
                        state = "int7";
                        num_type = TokenType.tk_num_int_dec;
                    } else if (lastChar == '8' || lastChar == '9') {
                        state = "int10";
                        num_type = TokenType.tk_num_int_dec;
                    } else if (lastChar == '.') {
                        state = "period";
                        num_type = TokenType.tk_period;
                    } else {
                        System.out.println("El primer carácter no es válido.");
                        return new Token(tokenRow, tokenColumn, "", TokenType.ERROR);
                    }
                    break;
                case "int7":
                    if (lastChar >= '0' && lastChar <= '7') {
                        break;
                    } else if (lastChar == '8' || lastChar == '9') {
                        state = "int10";
                    } else if (lastChar == '.') {
                        state = "real";
                        num_type = TokenType.tk_num_real;
                    } else if (Pattern.matches("[a-fA-F]", Character.toString(lastChar))) {
                        state = "hex";
                        num_type = TokenType.ERROR;
                    } else if (Character.toUpperCase(lastChar) == 'Q') {
                        num_type = TokenType.tk_num_int_oct;
                        tokenFound = true;
                    } else if (Character.toUpperCase(lastChar) == 'X') {
                        num_type = TokenType.tk_num_int_hex;
                        tokenFound = true;
                    } else {
                        tokenFound = true;
                    }
                    break;
                case "hex":
                    if (Pattern.matches("[0-9a-fA-F]", Character.toString(lastChar))) {
                        break;
                    } else if (Character.toUpperCase(lastChar) == 'X') {
                        num_type = TokenType.tk_num_int_hex;
                        tokenFound = true;

                    } else {
                        tokenFound = true;
                    }
                    break;
                case "int10":
                    if (lastChar >= '0' && lastChar <= '9') {
                        break;
                    } else if (lastChar == '.') {
                        state = "real";
                        num_type = TokenType.tk_num_real;
                    } else if (Pattern.matches("[a-fA-F]", Character.toString(lastChar))) {
                        state = "hex";
                        num_type = TokenType.ERROR;
                    } else if (Character.toUpperCase(lastChar) == 'X') {
                        num_type = TokenType.tk_num_int_hex;
                        tokenFound = true;
                    } else {
                        tokenFound = true;
                    }
                    break;
                case "period":
                    if (lastChar >= '0' && lastChar <= '9') {
                        state = "real";
                        num_type = TokenType.tk_num_real;
                    } else {
                        tokenFound = true;
                    }
                    break;
                case "real":
                    if (lastChar >= '0' && lastChar <= '9') {
                        System.out.println("numero");
                        break;
                    } else if (Character.toUpperCase(lastChar) == 'E') {
                        lexema += lastChar;
                        lastChar = reader.getNextChar();
                        if (lastChar == '+' || lastChar == '-') {
                            lexema += lastChar;
                            lastChar = reader.getNextChar();
                            if (lastChar >= '0' && lastChar <= '9') {
                                state = "realexp";
                                num_type = TokenType.tk_num_real;
                            } else {
                                num_type = TokenType.ERROR;
                                tokenFound = true;
                            }
                        } else if (lastChar >= '0' && lastChar <= '9') {
                            state = "realexp";
                            num_type = TokenType.tk_num_real;
                        } else {
                            num_type = TokenType.ERROR;
                            tokenFound = true;
                        }
                    }else {
                        tokenFound = true;
                    }
                    break;
                case "realexp":
                    if (lastChar >= '0' && lastChar <= '9') {
                        break;
                    } else {
                        tokenFound = true;
                    }
                    break;
            }
            lexema += lastChar;
            if (!tokenFound) {
                lastChar = reader.getNextChar();
            }
        }

        return (new Token(tokenRow, tokenColumn, lexema, num_type));
    }

/*
    Token identifyNumber() {

        String lexema = "";
        do {
            lexema += lastChar;
            lastChar = reader.getNextChar();
        } while ((lastChar >= '0' && lastChar <= '9'));
        return (new Token(tokenRow, tokenColumn, lexema, TokenType.tk_int));
    }*/

    Token identifyKeywordOrIdentifier() {
        String lexema = "";
        do {
            lexema += lastChar;
            lastChar = reader.getNextChar();
        } while ((lastChar >= 'a' && lastChar <= 'z') || (lastChar >= 'A' && lastChar <= 'Z') || (lastChar == '_') || (lastChar >= '0' && lastChar <= '9'));

        //revisar si el lexema es una palabra reservada. Si lo es retornar el token respectivo, si no, tk_id
        if (symbolTable.isResWord(lexema)) {
            return (new Token(tokenRow, tokenColumn, lexema, getTypeOfReservedWord(lexema)));
        }
        return (new Token(tokenRow, tokenColumn, lexema, TokenType.tk_id));
    }

    TokenType getTypeOfReservedWord(String word) {
        TokenType types[] = TokenType.values();
        for (TokenType type : types) {
            if (("tk_" + word).equals(type.toString())) {
                return type;
            }
        }
        return TokenType.ERROR;
    }


    public static void main(String args[]) {
        /*ScriptReader sr = new ScriptReader("prueba.txt");
        char myChar = 'x';
        boolean flag = true;
        while (flag) {
            //System.out.println(myChar+":"+sr.getRow()+","+sr.getColumn());
                myChar = sr.getNextChar();
                if (myChar == '¶') {
                    flag = false;
                } else if (myChar == ' ') {
                    System.out.println(true);
                } else {
                    System.out.println(myChar);
            }
        }*/
        LexicalAnalyzer lexer = new LexicalAnalyzer("prueba.txt");
        Token myToken = lexer.nextToken();
        while (myToken.type != TokenType.tk_eof) {
            if (myToken.type == TokenType.ERROR) {
                break;
            }
            System.out.println(myToken);
            myToken = lexer.nextToken();
        }
        System.out.println(myToken);

    }
}
