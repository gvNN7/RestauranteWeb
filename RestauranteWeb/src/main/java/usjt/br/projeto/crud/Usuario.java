package usjt.br.projeto.crud;
public class Usuario {
	public int id;
	public String usuario;
	private String senha;
	public String email;
	
	public Usuario(String usuario,String senha,String email) {
		this.usuario = usuario;
		this.senha = senha;
		this.email = email;
	}

	private String getUsuario() {
		return usuario;
	}

	private void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	private String getSenha() {
		return senha;
	}

	private void setSenha(String senha) {
		this.senha = senha;
	}

	private String getEmail() {
		return email;
	}

	private void setEmail(String email) {
		this.email = email;
	}
	
}
