package usjt.br.projeto.conexaoBanco;

import java.sql.*;
   import javax.swing.*;

   public class AcessoDB implements Connect {
   
   //atributo
      public Connection conn;
   
   //construtor
      public AcessoDB() {
         conn             =null;
         String url       ="jdbc:mysql://localhost/";
         String dbName    ="projeto_restaurante";
         String driver    ="com.mysql.jdbc.Driver";
         
         // AQUI ALTERA PARA O SERVER DA FACULDADE (USUARIO E SENHA)
         String username  ="root";
         String password  ="root";
      
         try {
            Class.forName (driver) ;
            conn = DriverManager.getConnection(url
               +dbName , username , password);
         }
            catch(Exception e){
               JOptionPane.showMessageDialog(
                  null,
                  "erro no banco de dados!!\n\n"+
                  "contate o administrador",
                  "Erro...",
                  JOptionPane.WARNING_MESSAGE);
               e.printStackTrace();
            }
      }
   	
      public Connection getConnection(){
         return conn;
      }
     
      public void closeConnection(){
         try{
            conn.close();
         }
            catch(Exception e){
               e.printStackTrace();
            }
      
      }
   }