package usjt.br.projeto.conexaoBanco;

import java.sql.Connection;

public interface Connect{

   public Connection getConnection();
   public void closeConnection();

}