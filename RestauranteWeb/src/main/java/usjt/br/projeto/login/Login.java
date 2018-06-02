
package usjt.br.projeto.login;
import java.io.File;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Login {
	// ATRIBUTOS
	private int codigo, tipo;
	private String nome, senha;
	
	// CONSTRUTORES
	// Construtor Padrão
	public Login() {
		codigo = 0;
		tipo = 0;
		nome = null;
		senha = null;
	}
	
	public Login(int codigo){
		setCodigo(codigo);
	}
	
	// Construtor para Login
	public Login(String nome, String senha) {
		setNome(nome);
		setSenha(senha);
	}

	// Construtor para setar valores
	public Login(String nome, String senha, int tipo) {
		setNome(nome);
		setCodigo(codigo);
		setTipo(tipo);
		setSenha(senha);
	}

	// CREATE (CRIA UM NOVO ARQUIVO)
	public boolean criar() {
		// Variavel para armazenar o último id (mais alto)
		int codigoMaior = 0;
		try {
			// Captura os dados já existentes
			ArrayList<String> info = read();
			// Cria um novo arquivo .txt
			Formatter arquivo = new Formatter("Usuarios.txt");
			// Preenche o arquivo txt com os dados antigos
			// O for funciona da seguinte maneira, (indice;
			// indice<dados.size()-(último dados.get); indice+= (ultimo
			// dados.get+1)
			for (int i = 0; i < info.size() - 3; i += 4) {
				// Pega o codigo com número mais alto para inserir o novo dado.
				if (Integer.parseInt(info.get(i)) > codigoMaior)
					codigoMaior = Integer.parseInt(info.get(i));
				// Insere o dado no bloco de notas
				arquivo.format("%s %s %s %s %n", info.get(i), info.get(i + 1), info.get(i + 2), info.get(i + 3));
			}
			// Insere os novos dados
			arquivo.format("%s %s %s %s %n", (codigoMaior + 1) + "", getNome(), getSenha(), getTipo());
			arquivo.close();
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	// READ (LÊ DADOS DO ARQUIVO)
	public ArrayList<String> read() {
		// Verifica se existe algum arquivo criado
		ArrayList<String> dados = new ArrayList<String>();
		try {
			Scanner txt = new Scanner(new File("Usuarios.txt"));
			while (txt.hasNext())
				dados.add(txt.next());
			txt.close();
			return dados;
		} catch (Exception ex) {
			ex.printStackTrace();
			return dados;
		}
	}
	
	// READ (LÊ DADOS DO ARQUIVO)
		public ArrayList<String> Listar() {
			// Verifica se existe algum arquivo criado
			ArrayList<String> dados = new ArrayList<String>();
			try {
				Scanner txt = new Scanner(new File("Usuarios.txt"));
				while (txt.hasNext())
					dados.add(txt.next());
				txt.close();
				return dados;
			} catch (Exception ex) {
				ex.printStackTrace();
				return dados;			
			}
		}
		

	// DELETE (DELETA UM USUÁRIO)
	public boolean delete() {
		try {
			// Captura os dados já existentes
			ArrayList<String> dados = read();
			// Cria um novo arquivo .txt
			Formatter arquivo = new Formatter("Usuarios.txt");
			// Preenche o arquivo txt com os dados antigos
			boolean excluido = false;
			for (int i = 0; i < dados.size() - 3; i += 4)
				if (getCodigo() != Integer.parseInt(dados.get(i)))
					arquivo.format("%s %s %s %s %n", dados.get(i), dados.get(i + 1), dados.get(i + 2),
							dados.get(i + 3));
				else
					excluido = true;
			arquivo.close();
			if (excluido == true)
				return true;
			else {
				JOptionPane.showMessageDialog(null, "Usuário à ser excluído não encontrado", "USUÁRIO NÃO EXCLUÍDO", 1);
				return false;
			}
		} catch (Exception ex) {
			// Apresenta Mensagem de Erro
			JOptionPane.showMessageDialog(null, "Ocorreu um erro ao manipular os dados: " + ex.getMessage(),
					"ERRO AO MANIPULAR OS DADOS", 0);
			return false;
		}
	}

	// LOGAR (VERIFICA DADOS DE LOGIN E SE O LOGIN FOR VERDADEIRO CHAMA O MÉTODO
	// SETTIPO PASSANDO O TIPO DO USUÁRIO LOGADO)
	public boolean logar() {
		ArrayList<String> dados = read();
		// Verifica validade do login
		String usuario = null;
		String[][] user = new String[dados.size()/4][3];
		int line = 1 ;
		for(int i  = 0; i < dados.size()/4; i++){
				user[i][0] = dados.get(line);
				user[i][1] = dados.get(line+1);
				user[i][2] = dados.get(line+2);
				line+=4;
				System.out.println(user[i][0]);
				System.out.println(user[i][1]);
				System.out.println(user[i][2]);
			}
		
		int ini1,fim1,trocalogin, trocasenha, trocaperfil;
		   
		   for(ini1=0;ini1<user.length-1;ini1++)
		   {
			   for(fim1=user.length-1;fim1>ini1;fim1--)
			   {
				   if(Integer.parseInt(user[fim1][0])<Integer.parseInt(user[fim1-1][0]))
				   {
					   trocalogin=Integer.parseInt(user[fim1][0]);
					   trocasenha=Integer.parseInt(user[fim1][1]);
					   trocaperfil=Integer.parseInt(user[fim1][2]);
					   
					   //USUARIO
					   user[fim1][0]=user[fim1-1][0];
					   user[fim1-1][0]=""+trocalogin;
					   
					   //SENHA ACOMPANHA USUARIO
					   user[fim1][1]=user[fim1-1][1];
					   user[fim1-1][1]=""+trocasenha;
					   
					   //PERFIL ACOMPANHA SENHA
					   user[fim1][2]=user[fim1-1][2];
					   user[fim1-1][2]=""+trocaperfil;
				   }
			   }
		   }
		   
		   System.out.println("BUSCA BINARIA\n");
		   for(int i = 0; i<user.length-1; i++){
			   System.out.print(user  [i][0]+"    ");
			   System.out.print(user[i][1]);
			   System.out.println(user[i][2]);
		   }
		   
		// busca binária
		int ini = 0;
		int fim = user.length-1;
		int meio =0;
		int find = Integer.parseInt(getNome());
		boolean saida = false;
		
		do{
			
         meio = (ini + fim) / 2;
         
         int k = Integer.parseInt(user[meio][0]);
         if   (k < find){
        	 ini = meio + 1;
         }
         
         else if (k > find){
        	 fim = meio - 1;
         }
         
         else {
            usuario = ""+k;
            saida=true;
         }
		}while(ini<=fim && saida==false);
		
		
		if (user[meio][1].equals(getSenha())) {
			setTipo(Integer.parseInt(user[meio][2]));
			
			return true;
		}
		
		JOptionPane.showMessageDialog(null, "DESLOGADOO");
		return false;
		
	}
	// SET's
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	// GET's
	public int getCodigo() {
		return codigo;
	}

	public int getTipo() {
		return tipo;
	}

	public String getNome() {
		return nome;
	}

	public String getSenha() {
		return senha;
	}
	
	public static void main (String args[]){
		new Login(4).delete();
	}

}
