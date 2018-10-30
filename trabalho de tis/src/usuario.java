import org.json.JSONObject;
public class usuario {
		private int diferencial;//intentifica se e 0 morador 1 associaçao 
		private String nome  ;  
		private String email;
		private int senha=0;
		private int numerodeid;//e cpf ou cnpj
		
	public usuario() {
		// TODO Auto-generated constructor stub
	
	}
	public boolean Senha(int versenha) {
	
		if(senha==0) {
			this.senha=versenha;
			return true;
			
		}else {
			if(senha==versenha) {
				
				return true;
			}else {
				return false;
			}
		}
		
		
	}
	public void setarusuario(int diferencial2,String nomea,String email2,int numerodeida)
	{
		this.diferencial=diferencial2;
		this.nome=nomea;
		this.email=email2;
		this.numerodeid=numerodeida;
	}
	public int getDiferencial() {
		return diferencial;
	}
	public void setDiferencial(int diferencial) {
		this.diferencial = diferencial;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getNumerodeid() {
		return numerodeid;
	}
	public void setNumerodeid(int numerodeid) {
		this.numerodeid = numerodeid;
	}
	

}
