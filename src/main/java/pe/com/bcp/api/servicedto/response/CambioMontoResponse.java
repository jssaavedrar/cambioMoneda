package pe.com.bcp.api.servicedto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CambioMontoResponse {

	private String monto;
	private String montoTipoCambio;
	private String monedaOrigen;
	private String monedaDestino;
	private String tipoCambio;

}
