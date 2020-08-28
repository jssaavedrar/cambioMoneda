package pe.com.bcp.api.webdto.request.cambio;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetCambioMontoWebRequest {

	private String monto;
	private String monedaOrigen;
	private String monedaDestino;

}
