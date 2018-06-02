package usjt.br.projeto.conexaoBanco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class SQLCodes{
	ArrayList<String> dados = new ArrayList<String>();
	public SQLCodes(){}

	public boolean cud(String sqlCode){
		try{
			ConexaoBD cbd = new ConexaoBD();
			//Tenta obter conexão
			Connection conexao = cbd.getConexao();
			PreparedStatement st = conexao.prepareStatement(sqlCode);
			st.execute();
			st.close();
			conexao.close();
			return true;
		}catch(SQLException sql){
			JOptionPane.showMessageDialog(null, "Ocorreu um erro ao operar  os dados:\n"+sql.getMessage(),"ERRO AO OPERAR DADOS",0);return false;}
	}
	
	public Double buscaPreco(int cod_cardapio){
		try{

			Double dados = null;
			ConexaoBD cbd = new ConexaoBD();
			Connection conexao = cbd.getConexao();
			PreparedStatement st = conexao.prepareStatement("select preco from cardapio where cod_cardapio="+cod_cardapio+";");
			ResultSet rs = st.executeQuery();
			while(rs.next()){
				dados = rs.getDouble("preco");
			}
			conexao.close(); st.close(); return dados;
		}
		catch(SQLException sql){
				JOptionPane.showMessageDialog(null, "Ocorreu um erro ao operar  os dados:\n"+sql.getMessage(),"ERRO AO OPERAR DADOS",0); return 0.0;}
	}
	
	public ArrayList<Object> buscaCardapio(Object cod_cardapio){
		try{
			
			ArrayList<Object> dados = new ArrayList<Object>();
			ConexaoBD cbd = new ConexaoBD();
			Connection conexao = cbd.getConexao();
			PreparedStatement st = conexao.prepareStatement("select * from cardapio where cod_cardapio="+cod_cardapio+";");
			ResultSet rs = st.executeQuery();
			while(rs.next()){
				dados.add(rs.getInt("cod_cardapio"));
				dados.add(rs.getString("descricao"));
				dados.add(rs.getDouble("preco"));
				dados.add(rs.getString("tipo_cardapio"));
			}
			conexao.close(); st.close(); return dados;
		}
		catch(SQLException sql){
			JOptionPane.showMessageDialog(null, "Ocorreu um erro ao operar  os dados:\n"+sql.getMessage(),"ERRO AO OPERAR DADOS",0); return null;}
	}
	
	public ArrayList<Object> buscaMesa(int numero_mesa){
		try{
			
			ArrayList<Object> dados = new ArrayList<Object>();
			ConexaoBD cbd = new ConexaoBD();
			Connection conexao = cbd.getConexao();
			PreparedStatement st = conexao.prepareStatement("select cod_pedidos, c.descricao AS descricao , subtotal, quantidade from pedidos p LEFT JOIN cardapio c ON p.itemCardapio=c.cod_cardapio where numero_mesa="+numero_mesa+";");
			ResultSet rs = st.executeQuery();
			while(rs.next()){
				dados.add(rs.getInt("quantidade"));
				dados.add(rs.getString("c.descricao"));
				dados.add(rs.getDouble("subtotal"));
			}
			conexao.close(); st.close(); return dados;
		}
		catch(SQLException sql){
			JOptionPane.showMessageDialog(null, "Ocorreu um erro ao operar  os dados:\n"+sql.getMessage(),"ERRO AO OPERAR DADOS",0); return null;}
	}
	
	//SELECT (TRAZ TODOS OS DADOS NO BD DEMA RESPECTIVA ENTIDADE)
	public ArrayList<String> select(ArrayList<String> tipos, String SQLCode){
		ArrayList<String> dados = new ArrayList<String>();
		try{
			ConexaoBD cbd = new ConexaoBD();
			Connection conexao = cbd.getConexao();
			PreparedStatement st = conexao.prepareStatement(SQLCode);
			ResultSet rs = st.executeQuery();//O resultset serve para capturar os resultados
			//CAPTURA OS DADOS PARA O ARRAY
			
			/*ArrayList<String> p = new ArrayList<String>();
			p.add(""+2);p.add("mesa");
			p.add(""+4);p.add("hr_entrada");*/
			
			while(rs.next()){//Enquanto houver próximo no resultset
				for(int i = 0 ; i < tipos.size() ; i++){//Faz enquanto o contador é menor que o arraylist com os dados
					//1 PARA STRING
					if(		tipos.get(i).equals("1")){dados.add(rs.getString			(tipos.get(i+=1)));i-=1;}
					//2 PARA INTEIRO
					else if(tipos.get(i).equals("2")){dados.add(""+rs.getInt			(tipos.get(i+=1)));i-=1;}
					//3 PARA DOUBLE
					else if(tipos.get(i).equals("3")){dados.add(""+rs.getDouble	(tipos.get(i+=1)));i-=1;}
					//4 PARA DATE
					else if(tipos.get(i).equals("4")){dados.add(""+rs.getDate		(tipos.get(i+=1)));i-=1;}
					//4 PARA DATE
					else if(tipos.get(i).equals("5")){dados.add(""+rs.getBoolean	(tipos.get(i+=1)));i-=1;}
				}
				//Exibe mensagem caso o vetor esteja vazio (ou seja não achou nenhum dado)
			}if(dados.size()== 0 || dados.size() < 1)JOptionPane.showMessageDialog(null, "Não foi possível localizar nenhum dado\npara o cardápio informado.\n\nVerifique as informações e tente novamente.","NENHUM DADO ENCONTRADO",0);
			conexao.close(); st.close(); return dados;
		}catch(SQLException sql){JOptionPane.showMessageDialog(
				null, "Ocorreu um erro ao capturar os dadods:\n"+sql.getMessage(),"ERRO AO OPERAR OS DADOS",0);return dados;}
	}
	
	public ArrayList<String> getDados(){return dados;}
	public void setDados(ArrayList<String> dados){this.dados = dados;}
}


