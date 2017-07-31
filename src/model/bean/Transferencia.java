
package model.bean;

public class Transferencia {
    private String data;
    private double valor;
    private String contaAlvo;
    private String transacao = "TRANSFERÊNCIA";

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getContaAlvo() {
        return contaAlvo;
    }

    public void setContaAlvo(String contaAlvo) {
        this.contaAlvo = contaAlvo;
    }

    public String getTransacao() {
        return transacao;
    }

    public void setTransacao(String transacao) {
        this.transacao = transacao;
    }


}
