import java.io.IOException;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import org.simpleframework.http.Request;
import org.simpleframework.http.Response;
import org.simpleframework.http.Status;
import org.simpleframework.http.core.Container;
import org.simpleframework.http.core.ContainerSocketProcessor;
import org.simpleframework.transport.connect.Connection;
import org.simpleframework.transport.connect.SocketConnection;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class AjaxJson implements Container {

	static Estoque estoque = new Estoque();

	public void handle(Request request, Response response) {
		try {
			Produto p = estoque.consultar("lEItE");
			this.enviaResposta(Status.CREATED, response, p.toJson().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void enviaResposta(Status status, Response response, String str) throws Exception {

		PrintStream body = response.getPrintStream();
		long time = System.currentTimeMillis();

		response.setValue("Content-Type", "application/json");
		response.setValue("Server", "Controle de EstoqueService (1.0)");
		response.setValue("Access-Control-Allow-Origin", "null");
		response.setDate("Date", time);
		response.setDate("Last-Modified", time);
		response.setStatus(status);

		if (str != null)
			body.println(str);
		body.close();
	}

	public static void main(String args[]) throws IOException {
		estoque.adicionar(new BemDeConsumo("Leite", 4.00F, 120, LocalDateTime.now(), LocalDate.now().plusMonths(6)));
		estoque.adicionar(new BemDuravel("Televisao", 1200.00F, 30, LocalDateTime.now(), 12));

		int porta = 7200;

		// Configura uma conexão soquete para o servidor HTTP.
		Container container = new AjaxJson();
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
