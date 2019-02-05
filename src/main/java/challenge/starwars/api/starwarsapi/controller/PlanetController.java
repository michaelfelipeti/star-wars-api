package challenge.starwars.api.starwarsapi.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import challenge.starwars.api.starwarsapi.model.Planet;
import challenge.starwars.api.starwarsapi.repository.PlanetRepository;
import challenge.starwars.api.starwarsapi.util.CustomErrorType;
import challenge.starwars.api.starwarsapi.util.PlanetUtil;
import challenge.starwars.api.starwarsapi.util.PlanetUtilImpl;

@RestController
@RequestMapping("/api")
public class PlanetController {
	
	public static final Logger logger = LoggerFactory.getLogger(PlanetController.class);
	
	@Autowired
	PlanetRepository planetRepository;
	
	@Autowired(required = true)
	private PlanetUtil planetUtil;
	
	@Bean
	public PlanetUtil planetUtil() {
		return new PlanetUtilImpl();
	}
	
	
	// GET ALL
	@RequestMapping(value = "/planet/", method = RequestMethod.GET)
	public ResponseEntity<List<Planet>> listAllPlanets() {
		List<Planet> planets = planetRepository.findAll();
		if (planets.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Planet>>(planets, HttpStatus.OK);
	}
	
	
	// GET
	@RequestMapping(value = "/planet/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getPlanet(@PathVariable("id") String id) {
		logger.info("Buscando planeta com id {}", id);
		Planet planet = planetRepository.findBy_id(id);
		if (planet == null) {
			logger.error("Planeta com {} não encontrado.", id);
			return new ResponseEntity(new CustomErrorType("Planeta com id " + id 
					+ " não encontrado."), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Planet>(planet, HttpStatus.OK);
	}
	
	
	// POST
	@RequestMapping(value = "/planet/", method = RequestMethod.POST)
	public ResponseEntity<?> createPlanet(@RequestBody Planet planet, UriComponentsBuilder ucBuilder) {
		logger.info("Creating Planet : {}", planet);
		
		
		// Verifica se o planeta contém todas as informações
		if (! planetUtil.checkIfContainsAllTheInformation(planet)) {
			logger.error("O Planeta não recebeu todas as informações necessárias!");
			return new ResponseEntity(new CustomErrorType("O Planeta não recebeu todas as informações necessárias! "
														+ "As seguintes informações precisam ser enviadas: name, climate, terrain."),
					HttpStatus.BAD_REQUEST);
		}
		
		// Verifica se o planeta já existe (comparação através do nome)
		if (planetRepository.findByName(planet.getName()) != null) {
			logger.error("Não foi possível criar o Planeta. Nome {} já existente.", planet.getName());
			return new ResponseEntity(new CustomErrorType("Não foi possível criar o Planeta. Nome " + 
			planet.getName() + " já existente."),HttpStatus.CONFLICT);
		}
		planetRepository.save(planet);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/demo/planet/{id}").buildAndExpand(planet.get_id()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}
	
	// PUT
	@RequestMapping(value = "/planet/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updatePlanet(@PathVariable("id") String id, @RequestBody Planet planetUpdated) {
		logger.info("Atualizando planeta com id {}", id);

		Planet planetOutdated = planetRepository.findBy_id(id);
		
		// Verifica se o recurso enviado está vazio ou se possui o id nulo
		if (planetUpdated == null || planetRepository.findBy_id(id) == null) {
			logger.error("Não foi possível atualizar o planeta. Id {} não encontrado.", id);
			return new ResponseEntity(new CustomErrorType("Não foi possível atualizar o planeta. Id " + id + " não encontrado."),
					HttpStatus.NOT_FOUND);
		}
		
		// Verifica se o recurso enviado está completamente vazio.
		if(! planetUtil.checkForChange(planetOutdated, planetUpdated)) {
			logger.error("Não foi necessário atualizar o planeta com id " + id + ", valores mantidos.");
			return new ResponseEntity(new CustomErrorType("Não foi necessário atualizar o planeta com id" + id + ", valores mantidos."),
					HttpStatus.NOT_MODIFIED);
		}
		
		planetUpdated = planetUtil.receiveAttributes(planetOutdated, planetUpdated);

		planetRepository.save(planetUpdated);
		return new ResponseEntity<Planet>(planetUpdated, HttpStatus.OK);
	}
	
	
	// DELETE
	@RequestMapping(value = "/planet/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletePlanet(@PathVariable("id") String id) {
		logger.info("Procurando e Deletando Planeta com id {}", id);

		Planet planet = planetRepository.findBy_id(id);
		if (planet == null) {
			logger.error("Não foi possível excluir o planeta. Id {} não informado.", id);
			return new ResponseEntity(new CustomErrorType("Não foi possível excluir o planeta. Id " + id + " não encontrado."),
					HttpStatus.NOT_FOUND);
		}
		
		planetRepository.delete(planetRepository.findBy_id(id));
		return new ResponseEntity<Planet>(HttpStatus.NO_CONTENT);
	}
	
	
	// DELETE ALL
	@RequestMapping(value = "/planet/", method = RequestMethod.DELETE)
	public ResponseEntity<Planet> deleteAllPlanets(){
		logger.info("[*]Deletando todos os Planetas");
		
		planetRepository.deleteAll();
		return new ResponseEntity<Planet>(HttpStatus.NO_CONTENT);
	}

}

