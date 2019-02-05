package challenge.starwars.api.starwarsapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import challenge.starwars.api.starwarsapi.model.Planet;

@RepositoryRestResource(path ="planet")
public interface PlanetRepository extends MongoRepository<Planet, String>{
	
	Planet findBy_id(@Param("id") String id);
	
	Planet findByName(@Param("name") String name);
}