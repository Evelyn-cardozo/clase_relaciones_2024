package py.edu.facitec.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//soporta la arquitectura REST
//Para solicitudes en formato JSON

import py.edu.facitec.model.Suscrito;
import py.edu.facitec.respository.SuscritoRepository;

@RestController
@RequestMapping("api")
public class SuscritoController {
	@Autowired
	private SuscritoRepository suscritoRepository;

	// consulta de todos los si
	// objeto suscrito ---------> JSON

	@GetMapping("/suscritos")
	public ResponseEntity<List<Suscrito>> getSuscritos() {

		List<Suscrito> suscritos = suscritoRepository.findAll();

		return new ResponseEntity<List<Suscrito>>(suscritos, HttpStatus.OK);
	}

	// URL A SOLICITAR
	@PostMapping("/suscrito") // json -->java
	public ResponseEntity<Suscrito> guardarSuscrito(@RequestBody Suscrito suscrito) {

		suscritoRepository.save(suscrito);

		return new ResponseEntity<Suscrito>(suscrito, HttpStatus.OK);

	}

	@GetMapping("/suscrito/{id}")
	public ResponseEntity<Suscrito> getOneSuscrito(@PathVariable Long id) {

		Optional<Suscrito> susRetorno = suscritoRepository.findById(id);
		// si encuentra
		if (susRetorno.isPresent()) {

			return new ResponseEntity<Suscrito>(susRetorno.get(), HttpStatus.OK);

		} else { // si no se encuentra por la id

			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		}

	}
//putMapping
	@DeleteMapping("/suscrito/{id}")
	public ResponseEntity<Suscrito> removeOneSuscrito(@PathVariable Long id) {

		Optional<Suscrito> susRetorno = suscritoRepository.findById(id);
		// si encuentra
		if (susRetorno.isPresent()) {

			suscritoRepository.deleteById(id);

			return new ResponseEntity<>(HttpStatus.OK);

		} else { // si no se encuentra por la id

			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		}

	}

}
