package DAO;

public class tabelaToken {

    public Integer numero_linha;
    public String token;
    public String lexema;
    public String erro;

    public tabelaToken(int numero_linha, String token, String lexema) {
        this.numero_linha = numero_linha;
        this.token = token;
        this.lexema = lexema;
        
    }

    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }

    public int getNumero_linha() {
        return numero_linha;
    }

    public void setNumero_linha(int numero_linha) {
        this.numero_linha = numero_linha;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    @Override
    public String toString() {
        return "tabelaToken{" + "numero_linha=" + numero_linha + ", token=" + token + ", lexema=" + lexema
                + ", erro=" + erro + '}';
    }

}
