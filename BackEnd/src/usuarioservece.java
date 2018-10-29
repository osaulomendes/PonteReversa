import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.simpleframework.http.Query;
import org.simpleframework.http.Request;
public class usuarioservece {
	private usuariolist lista;
	private usuario p;

	public String adicionar(Request request) {
		int diferencial;//intentifica se e 1 morador 2 associaçao 
		 String nome  ;  
		 String email;
		 int senha;
		 int numerodeid;
		
		Query query = request.getQuery();
		numerodeid = query.getInteger("cpf/cnpj");
		nome = query.get("nome");
		email = query.get("email");
		diferencial = query.getInteger("tipo");
		senha = query.getInteger("senha");
		//p.setarusuario(diferencial, nome, email, numerodeid);
		//p.setNumerodeid(numerodeid);
		p.setDiferencial(diferencial);
		p.setEmail(email);
		p.setNome(nome);
		p.Senha(senha);
		/*switch (diferencial) {
		case 1:
			p.setarusuario(diferencial, nome, email, numerodeid);
			p.Senha(senha);
			break;
		case 2:
			p.setarusuario(diferencial, nome, email, numerodeid);
			p.Senha(senha);
			break;
 		}*/
		if (p != null) {
			lista.adicionar(p);
		}

		return p.toString();

	}
	public String consultar(Request request) {
			int  id;
			int senha;
			int p2;
		Query query = request.getQuery();
		id = (int) query.getFloat("cpf/cnpj");
		senha = query.getInteger("senha");
		 p2 =lista.consultar(id, senha);

		if(p2==0) {
			return null;
		}else {
		return("carregado com susseso ");
	}
}
}