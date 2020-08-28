package pe.com.bcp.api.service.tipocambio;

import io.reactivex.Completable;
import io.reactivex.Single;
import pe.com.bcp.api.servicedto.response.CambioMontoResponse;
import pe.com.bcp.api.webdto.request.cambio.GetCambioMontoWebRequest;
import pe.com.bcp.api.webdto.request.tipocambio.UpdateTipoCambioWebRequest;

public interface TipoCambioService {
    Completable updateTipoCambio(UpdateTipoCambioWebRequest getTipoCambioRequest) throws Exception;
}
