
package model.dao;

import connection.ConnectionFactory;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.bean.Deposito;
import model.bean.Extrato;
import model.bean.Saque;
import model.bean.Transferencia;


public class ContaDAO {
    
    
    
    // LOGIN/CONTA!!!!
    
    
    
    public boolean conta(String numero, String agencia,String senha){
        // 1 - ABRINDO A CONEXAO
        // 2 - PREPARANDO O SQL
        // 3  - PEGANDO OS DADOS DA TABELA
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null; //INICIANDO O SQL SEM NENHUMA INSTRUÇÃO NO MOMENTO
        ResultSet rs = null; //NAO PEGANDO NADA DA TABELA ATE O MOMENTO
        
        boolean check = false;
        
        try {
            stmt = con.prepareStatement
                           ("SELECT * FROM conta WHERE con_numero = ? AND con_agencia = ? AND con_senha =?");
            stmt.setString(1, numero);
            stmt.setString(2, agencia);
            stmt.setString(3, senha);
           
            rs = stmt.executeQuery();//EXECUTANDO A BUSCA E GUANDANDO NO ResultSet
            
            if(rs.next()){ //SE OS CAMPOS EXISTIREM NO BD......
                check = true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Dados incorretos. Tente novamente");
        }finally{
            //FECHANDO A CONEXÃO DEPOIS DA BUSCA SER EXECUTADA.
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return check;// RETORNANDO O BOOLEAN PARA UMA NOVA CONSULTA
    }
    
    
//-------------------- VALIDANDO SENHA -----------------------------------------
    
    public String checkSenha(String senha){
        // 1 - ABRINDO A CONEXAO
        // 2 - PREPARANDO O SQL
        // 3  - PEGANDO OS DADOS DA TABELA
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null; //INICIANDO O SQL SEM NENHUMA INSTRUÇÃO NO MOMENTO
        ResultSet rs = null; //NAO PEGANDO NADA DA TABELA ATE O MOMENTO
        
        String res = "";
        
        try {
            stmt = con.prepareStatement ("SELECT * FROM conta WHERE con_senha = ?");
            stmt.setString(1, senha);    
            
            rs = stmt.executeQuery();//EXECUTANDO A BUSCA E GUANDANDO NO ResultSet
            
            if(rs.next()){ //SE O CAMPO EXISTIR NO BD......
                res = rs.getString("con_senha");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Senha incorreta. Tente novamente");
        }finally{
            //FECHANDO A CONEXÃO DEPOIS DA BUSCA SER EXECUTADA.
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return senha;    
    }
    

//----------------- TRANSFERENCIA (INSERINDO NA TABELA) ------------------------
    
    public void Transfer(String contaAlvo, double valor, String data){
        
        // 1 - ABRINDO A CONEXAO
        // 2 - PREPARANDO O SQL
        // 3 - PEGANDO OS DADOS DA TABELA
        
        Connection con = ConnectionFactory.getConnection();//ABRINDO CONEXÃO
        PreparedStatement stmt = null;//INICIANDO COMANDOS SQL
        ResultSet rs = null;// DECLARANDO A VARIAVEL DOS RESULTADOS 
        
//        boolean check  = false;
        
        try {
            stmt = con.prepareStatement
                    ("INSERT INTO transferencia (tra_contaAlvo, tra_valor, tra_data) VALUES (?,?,?)");
            
            stmt.setString(1, contaAlvo);
            stmt.setDouble(2,valor);
            stmt.setString(3,data);//PEGAR FUNCAO QUE INSERE A DATA
            stmt.executeUpdate();
                         
        } catch (SQLException ex) {
            Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
            //JOptionPane.showMessageDialog(null, "Dados incorretos. Tente novamente");
        }finally{
            //FECHANDO A CONEXÃO DEPOIS DA BUSCA SER EXECUTADA.
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        //return check;
        
    }
    
 //------------------ DEPOSITO -------------------------------------------------
    
      public void Deposito(double valor, String data){
        // 1 - ABRINDO A CONEXAO
        // 2 - PREPARANDO O SQL
        // 3 - PEGANDO OS DADOS DA TABELA
        
        Connection con = ConnectionFactory.getConnection();//ABRINDO CONEXÃO
        PreparedStatement stmt = null;//INICIANDO COMANDOS SQL
        ResultSet rs = null;// DECLARANDO A VARIAVEL DOS RESULTADOS 
        
                
        try {
            stmt=con.prepareStatement("INSERT INTO deposito (dep_valor,dep_data) VALUES (?,?)");
            stmt.setDouble(1, valor);
            stmt.setString(2, data);
            stmt.executeUpdate();                    
                    
        } catch (SQLException ex) {
            Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "ERRO");
        }finally{
            
            //FECHANDO A CONEXÃO DEPOIS DA BUSCA SER EXECUTADA.
            ConnectionFactory.closeConnection(con, stmt, rs);
            
        }

      }
     
     
      
      //---------------------- SAQUE -------------------------------------------
      
      public void Saque(double valor, String data){
        Connection con = ConnectionFactory.getConnection();//ABRINDO CONEXÃO
        PreparedStatement stmt = null;//INICIANDO COMANDOS SQL
        ResultSet rs = null;// DECLARANDO A VARIAVEL DOS RESULTADOS 
        
        
        //boolean check = false;
        try {
            
            stmt = con.prepareStatement("INSERT INTO saque (saq_valor,saq_data) VALUES (?,?)");
            stmt.setDouble(1, valor);
            stmt.setString(2, data);
            stmt.executeUpdate(); 
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "ERRO NO UPDATE DO SAQUE");
        }finally{
            //FECHANDO A CONEXÃO DEPOIS DA BUSCA SER EXECUTADA.
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        //return check;
      }
    
     //------------------------------- SALDO -----------------------------------
      
      public double Saldo(String conta){
          
        Connection con = ConnectionFactory.getConnection();//ABRINDO CONEXÃO
        PreparedStatement stmt = null;//INICIANDO COMANDOS SQL
        ResultSet rs = null;// DECLARANDO A VARIAVEL DOS RESULTADOS   


            double res = 0;//INICIALIZANDO A VARIAVEL DE RESULTADO DO SAQUE
              
            try {

                stmt = con.prepareStatement("SELECT con_saldo FROM conta WHERE con_numero = ?");
                stmt.setString(1,conta);
                rs = stmt.executeQuery();//EXECUTANDO A BUSCA E GUANDANDO NO ResultSet
                
                while(rs.next()){
                   res = rs.getDouble("con_saldo");
                   
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally{
                ConnectionFactory.closeConnection(con, stmt, rs);//FECHANDO CONEXÃO
            }
                         
        return res;
          
      }
          
      
//      --------------- SUBTRAINDO VALOR DO SALDO ------------------------------
      public void subSaldo(double valorSaque, String conta){
          
        Connection con = ConnectionFactory.getConnection();//ABRINDO CONEXÃO
        PreparedStatement stmt = null;//INICIANDO COMANDOS SQL
//        ResultSet rs = null;// DECLARANDO A VARIAVEL DOS RESULTADOS   
        
//        boolean check = false;//INICIALIZANDO VARIAVEL DE RETORNO
//        double res;//DECLARANDO A VARIAVEL DE RESULTADO DO SAQUE
        try {
            stmt = con.prepareStatement("UPDATE conta SET con_saldo = con_saldo - ? WHERE con_numero = ?");
            stmt.setDouble(1,valorSaque);
            stmt.setString(2, conta);
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(null, "ERRO NO UPDATE DO SALDO");
        }
            
      }
//      
      
      //-------------------- ADICIONANDO VALOR DO SALDO ------------------------
       public void addSaldo(double valor, String conta){
          
        Connection con = ConnectionFactory.getConnection();//ABRINDO CONEXÃO
        PreparedStatement stmt = null;//INICIANDO COMANDOS SQL
        ResultSet rs = null;// DECLARANDO A VARIAVEL DOS RESULTADOS   
        
        try {
            
                stmt = con.prepareStatement("UPDATE conta SET con_saldo = con_saldo + ? WHERE con_numero = ?");
                stmt.setDouble(1,valor);
                stmt.setString(2, conta);
                stmt.executeUpdate();            
            
        } catch (SQLException ex) {
            Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "ERRO NO UPDATE DO SALDO");
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
         
      }
      
     //-------------------- VERIFICANDO SE CONTA EXISTE ------------------------
       public boolean contaExists(String conta){
        
            Connection con = ConnectionFactory.getConnection();//ABRINDO CONEXÃO
            PreparedStatement stmt = null;//INICIANDO COMANDOS SQL
            ResultSet rs = null;// DECLARANDO A VARIAVEL DOS RESULTADOS 

             boolean check = false;

            try {

                stmt = con.prepareStatement("SELECT con_numero FROM conta WHERE con_numero = ?");
                stmt.setString(1,conta);
                rs = stmt.executeQuery();//EXECUTANDO A BUSCA E GUANDANDO NO ResultSet

                if(rs.next()){// SE EXISTIR.......
                   check = true;
                }

            } catch (SQLException ex) {
                Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);

            } finally{
                ConnectionFactory.closeConnection(con, stmt, rs);// FECHANDO CONEXAO
            }
        
            return check;
            
       }
       
       
    //-------------------- EXTRATO ---------------------------------------------
          
       
       //ADICIONANDO OS VALORES NA TABELA DO EXTRATO
       
    public void addExtrato(String data, String transacao, double valor, String conta){
        Connection con = ConnectionFactory.getConnection();//ABRINDO CONEXÃO
        PreparedStatement stmt = null;//INICIANDO COMANDOS SQL
        ResultSet rs = null;// DECLARANDO A VARIAVEL DOS RESULTADOS
        try {
            stmt = con.prepareStatement
               ("INSERT INTO extrato(ext_data, ext_transacao, ext_valor, con_numero) VALUES (?,?,?,?)");
            stmt.setString(1, data);
            stmt.setString(2,transacao);
            stmt.setDouble(3, valor);
            stmt.setString(4, conta);
            stmt.executeUpdate(); 
        
        } catch (SQLException ex) {
            Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "ERRO NO UPDATE DO SALDO");
        } finally{
                ConnectionFactory.closeConnection(con, stmt, rs);// FECHANDO CONEXAO
            }
            
        
    }
       

    
       // RECUPERANDO OS VALORES DO EXTRATO
    
    public DefaultTableModel extrato(DefaultTableModel tbl, String conta){
        
        Connection con = ConnectionFactory.getConnection();// 1 - ABRINDO A CONEXAO
        PreparedStatement stmt = null;// 2 - PREPARANDO O SQL
        ResultSet rs =  null; // 3  - PEGANDO OS DADOS DA TABELA
        
        tbl.setNumRows(0);
        
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        double valor = 0;
//        nf.format(valor);
        
        try {
            
            stmt = con.prepareStatement("SELECT ext_data, ext_transacao, ext_valor FROM extrato WHERE con_numero = ?");
            stmt.setString(1, conta);
            rs = stmt.executeQuery();
                     
            while(rs.next()){
                
                tbl.addRow(new String[]{
                    rs.getString("ext_data"),
                    rs.getString("ext_transacao"),
                   nf.format(rs.getDouble(("ext_valor")))
                    
                });
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return tbl;
       
    }

    //------------------ OBTENDO O NOME ----------------------------------------
    
    public String nome(String conta){
        
        Connection con = ConnectionFactory.getConnection();// 1 - ABRINDO A CONEXAO
        PreparedStatement stmt = null;// 2 - PREPARANDO O SQL
        ResultSet rs =  null;
        
        String res = "";
        
        try {
            
            stmt = con.prepareStatement("SELECT con_nome FROM conta WHERE con_numero = ?");
            stmt.setString(1, conta);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                res = rs.getString("con_nome");
            }
           
            
        } catch (SQLException ex) {
            Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
      return res;
        
    }
    
    
}
