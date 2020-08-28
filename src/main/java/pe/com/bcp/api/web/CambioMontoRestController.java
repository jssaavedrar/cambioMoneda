package pe.com.bcp.api.web;

import io.reactivex.Completable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import pe.com.bcp.api.service.cambio.CambioMontoService;
import pe.com.bcp.api.service.tipocambio.TipoCambioService;
import pe.com.bcp.api.servicedto.response.CambioMontoResponse;
import pe.com.bcp.api.webdto.request.cambio.GetCambioMontoWebRequest;
import pe.com.bcp.api.webdto.request.tipocambio.UpdateTipoCambioWebRequest;
import pe.com.bcp.api.webdto.response.BaseWebResponse;
import pe.com.bcp.api.webdto.response.CambioMontoWebResponse;

@Slf4j
@RestController
@RequestMapping(value = "/api/cambiomonto")
public class CambioMontoRestController {

	@Autowired
	private CambioMontoService cambioMontoService;
	@Autowired
	private TipoCambioService tipoCambioService;

	private CambioMontoWebResponse toCambioMontoWebResponse(CambioMontoResponse cambioMontoResponse) {
		CambioMontoWebResponse cambioMontoWebResponse = new CambioMontoWebResponse();
		BeanUtils.copyProperties(cambioMontoResponse, cambioMontoWebResponse);
		return cambioMontoWebResponse;
	}

    @GetMapping(
            value = "/{monedaOrigen}/{monedaDestino}/{monto}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
	@ResponseStatus(HttpStatus.OK)
	public Single<BaseWebResponse<CambioMontoWebResponse>> getCambioMonto(
			@PathVariable(value = "monto") String monto, @PathVariable(value = "monedaOrigen") String monedaOrigen,
			@PathVariable(value = "monedaDestino") String monedaDestino) {

		GetCambioMontoWebRequest getCambioMontoRequest = new GetCambioMontoWebRequest();

		getCambioMontoRequest.setMonedaDestino(monedaDestino);
		getCambioMontoRequest.setMonedaOrigen(monedaOrigen);
		getCambioMontoRequest.setMonto(monto);

		return cambioMontoService.getCambioMonto(getCambioMontoRequest).subscribeOn(Schedulers.io())
				.map(cambioMontoResponse -> BaseWebResponse.successWithData(toCambioMontoWebResponse(cambioMontoResponse)));
	}

	@PostMapping(
			value = "/tipocambio",
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Completable getActualizarTipoCambio(
			@RequestBody UpdateTipoCambioWebRequest request) throws Exception {

		return tipoCambioService.updateTipoCambio(request)
				.doFinally(() -> log.info("Termino la actualizacion"));}
}
