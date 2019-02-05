package challenge.starwars.api.starwarsapi.util;

import challenge.starwars.api.starwarsapi.model.Planet;

public interface PlanetUtil {
	
	public boolean checkIfContainsAllTheInformation(Planet planet);
	
	public Planet receiveAttributes(Planet planetOutdated, Planet planetUpdated);
	
	public boolean checkForChange(Planet planetOutdated, Planet planetUpdated);
}
