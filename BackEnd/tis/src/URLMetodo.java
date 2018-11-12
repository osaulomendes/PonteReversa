import java.awt.Desktop;
import java.io.IOException;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import org.simpleframework.http.Query;
import org.simpleframework.http.Request;
import org.simpleframework.http.Response;
import org.simpleframework.http.Status;
import org.simpleframework.http.core.Container;
import org.simpleframework.http.core.ContainerSocketProcessor;
import org.simpleframework.transport.connect.Connection;
import org.simpleframework.transport.connect.SocketConnection;

public class URLMetodo implements Container {

    //Dados estaticos
    
    static UsuarioService usuario = new UsuarioService();
    static List<Usuario> listUsuario = new ArrayList<>();
    static Usuario user, c1, c3, ca;

    static {
        try {
           
            ca = new Usuario("ca", "324568791", "luquina@sga.pucminas.br");
            c1 = new Usuario("Pedrin", "112358Rinoceronte", "bigodewar@puc.br");
            c3 = new Usuario("stevreeper", "13111999", "c3@email.com");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (ca != null && c1 != null && c3 != null){
            listUsuario.add(ca);
            listUsuario.add(c1);
            listUsuario.add(c3);
        }
    }

    public void handle(Request request, Response response) {
        try {

            // Recupera a URL e o método utilizado.

            String path = request.getPath().getPath();

            // Verifica qual ação está sendo chamada

           
            if (path.startsWith("/cadastrarUsuario")) {
                JSONObject obj = new JSONObject();
                try {
                    user = usuario.cadastroUsuario(request);
                    listUsuario.add(user);
                    obj.put("status", 1);
                    obj.put("message", "Cadastro efetuado com sucesso");
                } catch (Exception e) {
                    obj.put("status", 0);
                    obj.put("type", e.getClass());
                    obj.put("stackTrace", e.getStackTrace());
                    obj.put("message", e.getMessage());
                   
                }
                this.enviaResposta(Status.CREATED, response, obj.toString());
            }

            if (path.startsWith("/loginUsuario")) {
                JSONObject obj = new JSONObject();
                Query query = request.getQuery();
                Boolean achouUsuario = false;
                Boolean senhaCorreta = false;
                for (int i = 0; i < listUsuario.size(); i++) {
                    if (listUsuario.get(i).getEmail() == query.get("email")) {
                        achouUsuario = true;
                        if (listUsuario.get(i).getSenha() == query.get("senha")) {
                            senhaCorreta = true;
                            user = listUsuario.get(i);
                        }
                    }
                }
                if (achouUsuario) {
                    if (senhaCorreta) {
                        obj.put("status", 1);
                        obj.put("message", "Login efetuado com sucesso");
                    } else {
                        obj.put("status", 0);
                        obj.put("message", "Senha incorreta");
                    }
                } else {
                    obj.put("status", -1);
                    obj.put("message", "Usuário não encontrado");
                }
                this.enviaResposta(Status.CREATED, response, obj.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void enviaResposta(Status status, Response response, String str) throws Exception {

        PrintStream body = response.getPrintStream();
        long time = System.currentTimeMillis();

        response.setValue("Content-Type", "application/json");
        response.setValue("Server", "Gestão de restaurantes (1.0)");
        response.setValue("Access-Control-Allow-Origin", "null");
        response.setDate("Date", time);
        response.setDate("Last-Modified", time);
        response.setStatus(status);

        if (str != null)
            body.println(str);
        body.close();
    }

    public static void main(String args[]) throws IOException {

        int porta = 7200;

        // Configura uma conexão soquete para o servidor HTTP.
        Container container = new URLMetodo();
        ContainerSocketProcessor servidor = new ContainerSocketProcessor(container);
        Connection conexao = new SocketConnection(servidor);
        SocketAddress endereco = new InetSocketAddress(porta);
        conexao.connect(endereco);

        System.out.println("Tecle ENTER para interromper o servidor...");
        System.in.read();

        conexao.close();
        servidor.stop();

    }

}
