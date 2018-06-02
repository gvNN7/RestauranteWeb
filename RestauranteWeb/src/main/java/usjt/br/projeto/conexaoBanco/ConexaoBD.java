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
					JOptionPane.showMessageDialog(null, "Erro ao encontrar driver de conexão com o BD: \n"+e.getMessage(),"CONEXÃO COM O BD NÃO OBTIDA",0);
				}
			//conexao = DriverManager.getConnection("jdbc:mysql://localhost/projeto_restaurante","root","123456");
			conexao = DriverManager.getConnection("jdbc:mysql://localhost/projeto_restaurante","alunos","alunos");
		}catch(SQLException ex)
		{JOptionPane.showMessageDialog(null, "Não foi possível estabelecer conexão com o banco de dados: \n"+ex.getMessage(),"CONEXÃO COM O BD NÃO OBTIDA",0);
		}
	}
	
	//GETCONEXAO (RETORNA UMA CONEXÃO ABERTA COM O BD)
	Connection getConexao(){return conexao;}
	
	//CLOSECONEXAO (FECHA A CONEXÃO)
	boolean closeConexao(){
		try{conexao.close();; return true;
		}catch(SQLException ex){JOptionPane.showMessageDialog(null, "Não foi possível fechar a conexão com o banco de dados: \n"+ex.getMessage(),"CONEXÃO COM O BD NÃO FECHADA",0); return false;}
	}
}

