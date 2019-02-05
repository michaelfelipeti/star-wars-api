package challenge.starwars.api.starwarsapi.util;

import challenge.starwars.api.starwarsapi.model.Planet;

public class PlanetUtilImpl implements PlanetUtil {
	
	public boolean checkForChange(Planet planetOutdated, Planet planetUpdated) {
		if(!checkIfContainsAllTheInformation(planetUpdated)) {
			return true;
		}
		
		if (planetUpdated.getName().equalsIgnoreCase(planetOutdated.getName()) &&
				planetUpdated.getClimate().equalsIgnoreCase(planetOutdated.getClimate()) &&
				planetUpdated.getTerrain().equalsIgnoreCase(planetOutdated.getTerrain())){
			return false;
		}

		return true;
	}
	
	
	public boolean checkIfContainsAllTheInformation(Planet planet) {
		if (planet.getName() == null  || planet.getClimate() == null || planet.getTerrain() == null) {return false;}
		return true;
	}

	public Planet receiveAttributes(Planet planetOutdated, Planet planetUpdated) {
		if (planetUpdated.get_id() == null) {planetUpdated.set_id(planetOutdated.get_id());}
		if (planetUpdated.getName() == null) {planetUpdated.setName(planetOutdated.getName());}
		if (planetUpdated.getClimate() == null) {planetUpdated.setClimate(planetOutdated.getClimate());} 
		if (planetUpdated.getTerrain() == null) {planetUpdated.setTerrain(planetOutdated.getTerrain());}

		return planetUpdated;
	}

}