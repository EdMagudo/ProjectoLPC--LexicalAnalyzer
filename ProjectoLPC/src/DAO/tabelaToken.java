package DAO;

public class tabelaToken {

    public int numero_linha;
    public String tipoToken;
    public String lexema;

    public tabelaToken(int numero_linha, String tipoToken, String lexema) {
        this.numero_linha = numero_linha;
        this.tipoToken = tipoToken;
        this.lexema = lexema;
    }

    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    public int getNumero_linha() {
        return numero_linha;
    }

    public void setNumero_linha(int numero_linha) {
        this.numero_linha = numero_linha;
    }

   
    public String getTipoToken() {
        return tipoToken;
    }

    public void setTipoToken(String tipoToken) {
        this.tipoToken = tipoToken;
    }

   
}