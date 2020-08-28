package pe.com.bcp.api.webdto.request.tipocambio;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateTipoCambioWebRequest {
    private String tipoCambio;
    private String moneda;

}
