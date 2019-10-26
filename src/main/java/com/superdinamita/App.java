package com.superdinamita;


import com.superdinamita.parser.GrammarReader;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {


        System.out.println( "Hello World!" );
         /*      LexicalAnalyzer lexer = new LexicalAnalyzer("data/3.txt");
        Token myToken = lexer.nextToken();
        while (myToken.type != TokenType.tk_eof) {
            if (myToken.type == TokenType.ERROR) {
                break;
            }
        System.out.println(myToken);
        myToken = lexer.nextToken();
        }
        System.out.println(myToken);
*/
        GrammarReader gr = new GrammarReader("grammar/test.txt");

    }
}
