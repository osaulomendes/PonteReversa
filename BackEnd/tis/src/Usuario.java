import org.json.JSONObject;

public class Usuario implements JsonFormatter {
    private String nome;
    private String senha;
    private String email;
    private String cpfcnpj;
   

	private String telefone;
    private String diferencial;//intentifica se e 0 morador 1 associa�ao 

    public Usuario(String nome, String senha, String email, String cpfcnpj, String telefone,String diferencial) throws ExceptionUsuario {
        setNome(nome);
        setSenha(senha);
        setEmail(email);
        setcpfcnpj(cpfcnpj);
        setTelefone(telefone);
        setDiferencial(diferencial);
    }

    public Usuario(String nome, String senha, String email) throws ExceptionUsuario {
        setNome(nome);
        setSenha(senha);
        setEmail(email);
        setcpfcnpj(null);
        setTelefone(null);
    }

    public String getNome() {
        return nome;
    }

    //Coloca um nome valido
    public void setNome(String nome) throws ExceptionUsuario {
        if (nome != null && nome != "")
            this.nome = nome;
        else
            throw new ExceptionUsuario("Nome invalido");
    }

    public String getEmail() {
        return email;
    }

    //Coloca um email valido
    public void setEmail(String email) throws ExceptionUsuario {
        if (email != null && email != "")
            this.email = email;
        else
            throw new ExceptionUsuario("Email invalido");
    }

    public String getcpfcnpj() {
        return cpfcnpj;
    }

    //Coloca um cpfcnpj valido
    public void setcpfcnpj(String cpfcnpj) {
        this.cpfcnpj = cpfcnpj;
    }

    //Coloca um telefone valido
    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public String getDiferencial() {
		return diferencial;
	}

	public void setDiferencial(String diferencial) {
		this.diferencial = diferencial;
	}

    //Coloca uma senha não nula ou vazia e maior de 8 caracteres
    private void setSenha(String senha) throws ExceptionUsuario {
        if (senha != null && senha != "" && senha.length() >= 8)
            this.senha = senha;
        else {
            if (senha == null || senha == "")
                throw new ExceptionUsuario("Senha invalida");
            else
                throw new ExceptionUsuario("Sua senha deve ter pelo menos 8 caracteres");
        }
    }

    //Get senha
    public String getSenha() {
        return this.senha;
    }

    //Altera a senha quando a senha atual passada for igual a senha do usuario
    public void alteraSenha(String senhaNova, String senhaAtual) throws ExceptionUsuario {
        if (senhaAtual == this.senha)
            setSenha(senhaNova);
        else
            throw new ExceptionUsuario("Senha atual invalida");
    }

    //Converter JSON
    public JSONObject toJson() {
        JSONObject obj = new JSONObject();
        obj.put("nome", this.nome);
        obj.put("senha", this.senha);
        obj.put("email", this.email);
        obj.put("cpfcnpj", this.cpfcnpj);
        obj.put("telefone", this.telefone);
        return obj;
    }
}
