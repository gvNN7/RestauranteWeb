package usjt.br.projeto.conexaoBanco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConexaoBD{
	
	Connection conexao;
	
	//CONEXAOBD (TENTA CONECTAR COM O BANCO DE DADOS)
	ConexaoBD(){
		try{
			try{ 
				Class.forName("com.mysql.jdbc.Driver");
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, "Erro ao encontrar driver de conex�o com o BD: \n"+e.getMessage(),"CONEX�O COM O BD N�O OBTIDA",0);
				}
			//conexao = DriverManager.getConnection("jdbc:mysql://localhost/projeto_restaurante","root","123456");
			conexao = DriverManager.getConnection("jdbc:mysql://localhost/projeto_restaurante","alunos","alunos");
		}catch(SQLException ex)
		{JOptionPane.showMessageDialog(null, "N�o foi poss�vel estabelecer conex�o com o banco de dados: \n"+ex.getMessage(),"CONEX�O COM O BD N�O OBTIDA",0);
		}
	}
	
	//GETCONEXAO (RETORNA UMA CONEX�O ABERTA COM O BD)
	Connection getConexao(){return conexao;}
	
	//CLOSECONEXAO (FECHA A CONEX�O)
	boolean closeConexao(){
		try{conexao.close();; return true;
		}catch(SQLException ex){JOptionPane.showMessageDialog(null, "N�o foi poss�vel fechar a conex�o com o banco de dados: \n"+ex.getMessage(),"CONEX�O COM O BD N�O FECHADA",0); return false;}
	}
}

