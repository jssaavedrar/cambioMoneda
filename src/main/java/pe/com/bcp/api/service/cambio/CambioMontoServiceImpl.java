package pe.com.bcp.api.service.cambio;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.reactivex.Single;
import pe.com.bcp.api.entidad.Moneda;
import pe.com.bcp.api.repository.MonedaRepository;
import pe.com.bcp.api.servicedto.response.CambioMontoResponse;
import pe.com.bcp.api.webdto.request.cambio.GetCambioMontoWebRequest;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CambioMontoServiceImpl implements CambioMontoService {

	@Autowired
	private MonedaRepository monedaRepository;

	@Override
	public Single<CambioMontoResponse> getCambioMonto(GetCambioMontoWebRequest getCambioMontoRequest) {
		return cambiarMontoRepository(getCambioMontoRequest);
	}

	private Single<CambioMontoResponse> cambiarMontoRepository(
			GetCambioMontoWebRequest getCambioMontoRequest) {

		return Single.create(singleSubscriber -> {
			List<String> nombres = new ArrayList<>();
			nombres.add(getCambioMontoRequest.getMonedaOrigen());
			nombres.add(getCambioMontoRequest.getMonedaDestino());
			List<Moneda> tipoCambios = monedaRepository.findByNombre(nombres);

			Double tipoCambioOrigen = Double.parseDouble(getTipoCambio(tipoCambios, getCambioMontoRequest.getMonedaOrigen()));
			Double tipoCambioDestino = Double.parseDouble(getTipoCambio(tipoCambios, getCambioMontoRequest.getMonedaDestino()));
			Double tipoCambio = (tipoCambioOrigen/tipoCambioDestino);
			Double montoTipoCambio = tipoCambio*Integer.parseInt(getCambioMontoRequest.getMonto());

			CambioMontoResponse cambioMontoResponse = new CambioMontoResponse();
			cambioMontoResponse.setMonto(getCambioMontoRequest.getMonto());
			cambioMontoResponse.setMonedaOrigen(getCambioMontoRequest.getMonedaOrigen());
			cambioMontoResponse.setMonedaDestino(getCambioMontoRequest.getMonedaDestino());
			cambioMontoResponse.setMontoTipoCambio(String.valueOf(montoTipoCambio));
			cambioMontoResponse.setTipoCambio(String.valueOf(tipoCambio));

			singleSubscriber.onSuccess(cambioMontoResponse);

		});
	}

	private String getTipoCambio(List<Moneda> tipoCambios, String moneda) throws Exception {
		return tipoCambios.stream().filter(m -> m.getNombre().equalsIgnoreCase(moneda)).findFirst()
				.map(m -> m.getTipo()).orElseThrow(() -> new Exception("No se encontro el tipo de moneda"));
	}

}
