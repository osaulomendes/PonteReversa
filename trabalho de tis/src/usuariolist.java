import org.json.JSONArray;
import org.json.JSONObject;

public class usuariolist {


		private static final int MAX_usuarioS = 100;
		private usuario[] listausuario;
		private int numusuarios;

		public int getNumusuarios() {
			return numusuarios;
		}

		public void adicionar(usuario p) {
			if (numusuarios < MAX_usuarioS) {
				listausuario[numusuarios++] = p;
			}
		}

		public int consultar(int id,int senha1) {
			boolean b;
			usuario n = null;
			b=n.Senha(senha1);
			for (int pos = 0; pos < numusuarios; pos++) {
				if (id==listausuario[pos].getNumerodeid() ) {
					if(true==b) {
							return listausuario[pos].getNumerodeid();
						}	
					}
			}
			return 0; // usuario não encontrado.
		}

		
		
		

		
		@Override
		public String toString() {
			StringBuilder valor = new StringBuilder();
			for (int i = 0; i < numusuarios; i++) {
				valor.append(listausuario[i] + "\n");
			}
			return valor.toString();
		}

		
		/*@Override
		public JSONArray toJsonArray() {
			JSONArray array = new JSONArray();
			for (int i = 0; i < numusuarios; i++) {
				array.put(listausuario[i].toJson());
			}
			return array;
		}*/
		/*@Override
		public JSONObject toJson() {
			JSONObject obj = new JSONObject();
			obj.put("numusuarios", this.getNumusuarios());
			obj.put("listausuario", this.toJsonArray());
			return obj;
		}*/
		
		

	}

