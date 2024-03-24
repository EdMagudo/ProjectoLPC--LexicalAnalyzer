package DAO;

import java.util.ArrayList;
import java.util.List;

public class AnalisadorToken {

    
     public  List<String> divid(String texto) {
        List<String> partes = new ArrayList<>();
        int numeroLinha = 1;
        int inicioPalavra = 0;

        for (int i = 0; i < texto.length(); i++) {
            char caractere = texto.charAt(i);

            if (caractere == '\n') {
                numeroLinha++;
                inicioPalavra = i + 1; // Avança o início da próxima palavra
            } else if (Character.isWhitespace(caractere)) {
                if (i > inicioPalavra) {
                    partes.add(texto.substring(inicioPalavra, i) + ", Linha: " + numeroLinha);
                }
                inicioPalavra = i + 1; // Avança o início da próxima palavra
            } else if (i == texto.length() - 1) { // Último caractere
                partes.add(texto.substring(inicioPalavra, i + 1) + ", Linha: " + numeroLinha);
            }
        }

        return partes;
    }
     
     public  void analisarCodigoFonte(List<String> codigoFonte) {
        List<String> tokens = new ArrayList<>();
        int numeroLinha = 1;

        for (String palavra : codigoFonte) {
            if (palavra.equals("\n")) {
                numeroLinha++;
            } else if (isDelimitador(palavra) || isOperadorAritmetico(palavra) || isOperadorRelacional(palavra) || isCaracterEspecial(palavra)) {
                adicionarToken(tokens, palavra, numeroLinha);
            } else {
                adicionarToken(tokens, palavra, numeroLinha);
            }
        }

        for (String token : tokens) {
            System.out.println("Token: " + token);
        }
    }

        
    

    public static void adicionarToken(List<String> tokens, String lexema, int numeroLinha) {
        tokens.add("Token: " + lexema + " Lexema: "+identificarToken(lexema) +" "+"Linha: "+ numeroLinha);
    }
    
    

    private static boolean isDelimitador(String caractere) {
        for (String delimitador : PalavrasReservadas.delimitadores) {
            if (delimitador.equals(caractere)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isOperadorAritmetico(String caractere) {
        for (String operador : PalavrasReservadas.operadoresAritmeticos) {
            if ( operador.equals(caractere)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isOperadorRelacional(String caractere) {
        for (String operador : PalavrasReservadas.operadoreRelacional) {
            if (operador.equals(caractere)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isCaracterEspecial(String caractere) {
        for (String especial : PalavrasReservadas.caracteresEspeciais) {
            if (especial.equals(caractere)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isSinalAtrimetrico(String a) {
        return a.equals(PalavrasReservadas.sinalAtribuicao);
    }

    private static void adicionarToken(List<String> tokens, String token) {
        if (!token.isEmpty()) {
            tokens.add(token);
        }
    }


    /*
        Esse metodo recebe um token (Palavra) como parametro e retorna o tipo correspondente com base na categoria do token.
     */
    public static String identificarToken(String token) {

        switch (token) {

            // Operadores aritméticos
            case Tokens.token_soma:
            case Tokens.token_subtraccao:
            case Tokens.token_multiplicacao:
            case Tokens.token_divisao:
                return "Operador Aritmético";

            // Operadores relacionais
            case Tokens.token_igual:
            case Tokens.token_diferente:
            case Tokens.token_menor:
            case Tokens.token_maior:
            case Tokens.token_maior_igual:
            case Tokens.token_menor_igual:
                return "Operador Relacional";

            // Delimitadores
            case Tokens.token_abrir_parenteses:
            case Tokens.token_fechar_parenteses:
            case Tokens.token_abrir_conchetes:
            case Tokens.token_fechar_conchetes:
            case Tokens.token_ponto_virgula:
            case Tokens.token_ponto:
            case Tokens.token_virgula:
            case Tokens.token_dois_pontos:
                return "Delimitador";

            // Tokens de operadores lógicos
            case Tokens.token_or:
            case Tokens.token_and:
            case Tokens.token_not:
                return "Operador Lógico";

            // Tokens de controle de fluxo
            case Tokens.token_if:
            case Tokens.token_then:
            case Tokens.token_else:
            case Tokens.token_while:
            case Tokens.token_do:
            case Tokens.token_begin:
            case Tokens.token_end:
                return "Controle de Fluxo";

            // Tokens de entrada e saida
            case Tokens.token_read:
            case Tokens.token_write:
            case Tokens.token_writeln:
            case Tokens.token_readln:
                return "Entrada ou saida";

            // Tokens de tipos de dados
            case Tokens.token_true:
            case Tokens.token_false:
            case Tokens.token_char:
            case Tokens.token_integer:
            case Tokens.token_boolean:
                return "Tipo de Dados";

            //Token de erro   
            case Tokens.token_erro:
                return "Erro";

            // Tokens de declaração
            case Tokens.token_var:
            case Tokens.token_array:
            case Tokens.token_function:
            case Tokens.token_procedure:
            case Tokens.token_program:
                return "Declaração";

            // Se não for nenhum dos tipos acima, é um identificador
            default:
                return "Identificador";
        }
    }
}
