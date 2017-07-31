
package Session;

public class UsuarioLogado {
     private String numero;
     private String agencia;
     private String senha;
     
     private UsuarioLogado() {}
     
      public void setSenha(String senha) {
        this.senha = senha;
    }

	private static class UsuarioLogadoHolder { 
		private final static UsuarioLogado instance = new UsuarioLogado();
	}
        
        public static UsuarioLogado getInstance() {
		return UsuarioLogadoHolder.instance;
	}

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getSenha() {
        return senha;
    }

   
        
        
        
}
