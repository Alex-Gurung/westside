package com.example.spacetrader.Entity;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

/**
 * A Universe class that contains a HashSet of SolarSystems. The Universe is generated when the game
 * begins and will contain all Solar Systems that are initialized.
 */
public class Universe {
    private HashSet<SolarSystem> solarSystems;

    public HashSet<SolarSystem> getSolarSystems() {
        return solarSystems;
    }

    public void setSolarSystems(HashSet<SolarSystem> solarSystems) {
        this.solarSystems = solarSystems;
    }

    private String[] solarSystemNames = new String[] {
                "Acamar",
                "Adahn",		// The alternate personality for The Nameless One in "Planescape: Torment"
                "Aldea",
                "Andevian",
                "Antedi",
                "Balosnee",
                "Baratas",
                "Brax",			// One of the heroes in Master of Magic
                "Bretel",		// This is a Dutch device for keeping your pants up.
                "Calondia",
                "Campor",
                "Capelle",		// The city I lived in while programming this game
                "Carzon",
                "Castor",		// A Greek demi-god
                "Cestus",
                "Cheron",
                "Courteney",	// After Courteney Cox…
                "Daled",
                "Damast",
                "Davlos",
                "Deneb",
                "Deneva",
                "Devidia",
                "Draylon",
                "Drema",
                "Endor",
                "Esmee",		// One of the witches in Pratchett's Discworld
                "Exo",
                "Ferris",		// Iron
                "Festen",		// A great Scandinavian movie
                "Fourmi",		// An ant, in French
                "Frolix",		// A solar system in one of Philip K. Dick's novels
                "Gemulon",
                "Guinifer",		// One way of writing the name of king Arthur's wife
                "Hades",		// The underworld
                "Hamlet",		// From Shakespeare
                "Helena",		// Of Troy
                "Hulst",		// A Dutch plant
                "Iodine",		// An element
                "Iralius",
                "Janus",		// A seldom encountered Dutch boy's name
                "Japori",
                "Jarada",
                "Jason",		// A Greek hero
                "Kaylon",
                "Khefka",
                "Kira",			// My dog's name
                "Klaatu",		// From a classic SF movie
                "Klaestron",
                "Korma",		// An Indian sauce
                "Kravat",		// Interesting spelling of the French word for "tie"
                "Krios",
                "Laertes",		// A king in a Greek tragedy
                "Largo",
                "Lave",			// The starting system in Elite
                "Ligon",
                "Lowry",		// The name of the "hero" in Terry Gilliam's "Brazil"
                "Magrat",		// The second of the witches in Pratchett's Discworld
                "Malcoria",
                "Melina",
                "Mentar",		// The Psilon home system in Master of Orion
                "Merik",
                "Mintaka",
                "Montor",		// A city in Ultima III and Ultima VII part 2
                "Mordan",
                "Myrthe",		// The name of my daughter
                "Nelvana",
                "Nix",			// An interesting spelling of a word meaning "nothing" in Dutch
                "Nyle",			// An interesting spelling of the great river
                "Odet",
                "Og",			// The last of the witches in Pratchett's Discworld
                "Omega",		// The end of it all
                "Omphalos",		// Greek for navel
                "Orias",
                "Othello",		// From Shakespeare
                "Parade",		// This word means the same in Dutch and in English
                "Penthara",
                "Picard",		// The enigmatic captain from ST:TNG
                "Pollux",		// Brother of Castor
                "Quator",
                "Rakhar",
                "Ran",			// A film by Akira Kurosawa
                "Regulas",
                "Relva",
                "Rhymus",
                "Rochani",
                "Rubicum",		// The river Ceasar crossed to get into Rome
                "Rutia",
                "Sarpeidon",
                "Sefalla",
                "Seltrice",
                "Sigma",
                "Sol",			// That's our own solar system
                "Somari",
                "Stakoron",
                "Styris",
                "Talani",
                "Tamus",
                "Tantalos",		// A king from a Greek tragedy
                "Tanuga",
                "Tarchannen",
                "Terosa",
                "Thera",		// A seldom encountered Dutch girl's name
                "Titan",		// The largest moon of Jupiter
                "Torin",		// A hero from Master of Magic
                "Triacus",
                "Turkana",
                "Tyrus",
                "Umberlee",		// A god from AD&D, which has a prominent role in Baldur's Gate
                "Utopia",		// The ultimate goal
                "Vadera",
                "Vagra",
                "Vandor",
                "Ventax",
                "Xenon",
                "Xerxes",		// A Greek hero
                "Yew",			// A city which is in almost all of the Ultima games
                "Yojimbo",		// A film by Akira Kurosawa
                "Zalkon",
                "Zuul"			// From the first Ghostbusters movie
    };

    /**
     * public constructor that initializes the Universe object with a set number of Solar Systems
     *
     * @param numSolarSystems of type int to represent the number of Solar System the Universe will
     *                        hold
     */
    public Universe(int numSolarSystems) {
        List<Location> allCombos = new ArrayList<>();
        for(int i = 3; i < 40; i+= 3) {
            for (int j = 3; j < 40; j+= 3) {
                allCombos.add(new Location(i,j));
            }
        }
        Collections.shuffle(allCombos);
        List<Location> locations = allCombos.subList(0,numSolarSystems);
        solarSystems = new HashSet<>();
        /*Set<Location> locations = new HashSet<>();
        for (int i = 0; i < numSolarSystems; i++) {
            if(!locations.add(Location.getRandomLocation())) {
                i--;
            }
        }*/
        int solarSystemCount = 0;
        for (Location location: locations) {
            Random r = new Random();
            location.setX(location.getX() - 2+ r.nextInt(5));
            location.setY(location.getY() - 2 + r.nextInt(5));
            solarSystems.add(new SolarSystem(location, solarSystemNames[solarSystemCount]));
            solarSystemCount++;
        }
    }

    /**
     * default constructor to initialize a Universe object with 100 Solar Systems
     */
    public Universe() {
        this(100);
    }

    /**
     * toString method that overrides Object's toString method that returns a list of each Solar
     * System's name and location
     *
     * @return a String representation of each Solar System's name and location in the Universe
     */
    @Override
    public String toString() {
        String str = "";
        for (SolarSystem system: solarSystems) {
            str += system.toString();
        }
        return str;
    }
    public SolarSystem getRandomSolarSystem() {
        return (SolarSystem)solarSystems.toArray()[0];
    }
}
