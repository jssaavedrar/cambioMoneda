package pe.com.bcp.api.service.tipocambio;

import io.reactivex.Completable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.bcp.api.repository.MonedaRepository;
import pe.com.bcp.api.webdto.request.tipocambio.UpdateTipoCambioWebRequest;

@Service
@Slf4j
public class TipoCambioServiceImpl implements TipoCambioService {

	@Autowired
	private MonedaRepository monedaRepository;

	@Override
	public Completable updateTipoCambio(UpdateTipoCambioWebRequest request) throws Exception {
		valid(request);
		return tipoCambioRepository(request);
	}

	private Completable tipoCambioRepository(UpdateTipoCambioWebRequest request){
		monedaRepository.update(request.getMoneda(), request.getTipoCambio());
		return Completable.complete();
	}

	private void valid (UpdateTipoCambioWebRequest request) throws Exception {
		if (!validMoneda(request.getMoneda())) {
			throw new Exception("Tipo de moneda invalida");
		} else if (Double.parseDouble(request.getTipoCambio()) <= 0) {
			throw new Exception("Tipo de cambio mayor a 0");
		}
	}

	private boolean validMoneda (String moneda){
		return monedaRepository.findAll().stream().anyMatch(m -> m.getNombre().equalsIgnoreCase(moneda));
	}

}
