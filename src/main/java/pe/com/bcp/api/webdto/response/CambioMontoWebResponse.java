package pe.com.bcp.api.webdto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CambioMontoWebResponse {
	
	private String monto;
	private String montoTipoCambio;
	private String monedaOrigen;
	private String monedaDestino;
	private String tipoCambio;

}
