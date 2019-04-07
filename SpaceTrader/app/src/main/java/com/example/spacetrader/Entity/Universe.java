package com.example.spacetrader.Entity;


import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A Universe class that contains a HashSet of SolarSystems. The Universe is generated when the game
 * begins and will contain all Solar Systems that are initialized.
 */
public class Universe implements Serializable {
    private final HashSet<SolarSystem> solarSystems;

    /**
     * @return a set of solar systems in this universe
     */
    public Set<SolarSystem> getSolarSystems() {
        return Collections.unmodifiableSet(solarSystems);
    }

//    /**
//     * sets the solar systems in this universe
//     * @param solarSystems the set of solar systems in this universe
//     */
//    public void setSolarSystems(HashSet<SolarSystem> solarSystems) {
//        this.solarSystems = solarSystems;
//    }

    /**
     * public constructor that initializes the Universe object with a set number of Solar Systems
     *
     * @param numSolarSystems of type int to represent the number of Solar System the Universe will
     *                        hold
     */
    public Universe(int numSolarSystems) {
        List<Location> allCombos = new ArrayList<>();
        for(int i = 2; i <= 33; i++) {
            for (int j = 2; j <= 33; j++) {
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
            // The alternate personality for The Nameless One in "Planescape: Torment"
            // One of the heroes in Master of Magic
            // This is a Dutch device for keeping your pants up.
            // The city I lived in while programming this game
            // A Greek demi-god
            // After Courteney Cox…
            // One of the witches in Pratchett's Discworld
            // Iron
            // A great Scandinavian movie
            // An ant, in French
            // A solar system in one of Philip K. Dick's novels
            // One way of writing the name of king Arthur's wife
            // The underworld
            // From Shakespeare
            // Of Troy
            // A Dutch plant
            // An element
            // A seldom encountered Dutch boy's name
            // A Greek hero
            // My dog's name
            // From a classic SF movie
            // An Indian sauce
            // Interesting spelling of the French word for "tie"
            // A king in a Greek tragedy
            // The starting system in Elite
            // The name of the "hero" in Terry Gilliam's "Brazil"
            // The second of the witches in Pratchett's Discworld
            // The Psilon home system in Master of Orion
            // A city in Ultima III and Ultima VII part 2
            // The name of my daughter
            // An interesting spelling of a word meaning "nothing" in Dutch
            // An interesting spelling of the great river
            // The last of the witches in Pratchett's Discworld
            // The end of it all
            // Greek for navel
            // From Shakespeare
            // This word means the same in Dutch and in English
            // The enigmatic captain from ST:TNG
            // Brother of Castor
            // A film by Akira Kurosawa
            // The river Ceasar crossed to get into Rome
            // That's our own solar system
            // A king from a Greek tragedy
            // A seldom encountered Dutch girl's name
            // The largest moon of Jupiter
            // A hero from Master of Magic
            // A god from AD&D, which has a prominent role in Baldur's Gate
            // The ultimate goal
            // A Greek hero
            // A city which is in almost all of the Ultima games
            // A film by Akira Kurosawa
            // From the first Ghostbusters movie
            String[] solarSystemNames = new String[]{
                    "Acamar",
                    "Adahn",
                    "Aldea",
                    "Andevian",
                    "Antedi",
                    "Balosnee",
                    "Baratas",
                    "Brax",            // One of the heroes in Master of Magic
                    "Bretel",        // This is a Dutch device for keeping your pants up.
                    "Calondia",
                    "Campor",
                    "Capelle",        // The city I lived in while programming this game
                    "Carzon",
                    "Castor",        // A Greek demi-god
                    "Cestus",
                    "Cheron",
                    "Courteney",    // After Courteney Cox…
                    "Daled",
                    "Damast",
                    "Davlos",
                    "Deneb",
                    "Deneva",
                    "Devidia",
                    "Draylon",
                    "Drema",
                    "Endor",
                    "Esmee",        // One of the witches in Pratchett's Discworld
                    "Exo",
                    "Ferris",        // Iron
                    "Festen",        // A great Scandinavian movie
                    "Fourmi",        // An ant, in French
                    "Frolix",        // A solar system in one of Philip K. Dick's novels
                    "Gemulon",
                    "Guinifer",        // One way of writing the name of king Arthur's wife
                    "Hades",        // The underworld
                    "Hamlet",        // From Shakespeare
                    "Helena",        // Of Troy
                    "Hulst",        // A Dutch plant
                    "Iodine",        // An element
                    "Iralius",
                    "Janus",        // A seldom encountered Dutch boy's name
                    "Japori",
                    "Jarada",
                    "Jason",        // A Greek hero
                    "Kaylon",
                    "Khefka",
                    "Kira",            // My dog's name
                    "Klaatu",        // From a classic SF movie
                    "Klaestron",
                    "Korma",        // An Indian sauce
                    "Kravat",        // Interesting spelling of the French word for "tie"
                    "Krios",
                    "Laertes",        // A king in a Greek tragedy
                    "Largo",
                    "Lave",            // The starting system in Elite
                    "Ligon",
                    "Lowry",        // The name of the "hero" in Terry Gilliam's "Brazil"
                    "Magrat",        // The second of the witches in Pratchett's Discworld
                    "Malcoria",
                    "Melina",
                    "Mentar",        // The Psilon home system in Master of Orion
                    "Merik",
                    "Mintaka",
                    "Montor",        // A city in Ultima III and Ultima VII part 2
                    "Mordan",
                    "Myrthe",        // The name of my daughter
                    "Nelvana",
                    "Nix",
                    "Nyle",            // An interesting spelling of the great river
                    "Odet",
                    "Og",            // The last of the witches in Pratchett's Discworld
                    "Omega",        // The end of it all
                    "Omphalos",        // Greek for navel
                    "Orias",
                    "Othello",        // From Shakespeare
                    "Parade",        // This word means the same in Dutch and in English
                    "Penthara",
                    "Picard",        // The enigmatic captain from ST:TNG
                    "Pollux",        // Brother of Castor
                    "Quator",
                    "Rakhar",
                    "Ran",            // A film by Akira Kurosawa
                    "Regulas",
                    "Relva",
                    "Rhymus",
                    "Rochani",
                    "Rubicum",        // The river Ceasar crossed to get into Rome
                    "Rutia",
                    "Sarpeidon",
                    "Sefalla",
                    "Seltrice",
                    "Sigma",
                    "Sol",            // That's our own solar system
                    "Somari",
                    "Stakoron",
                    "Styris",
                    "Talani",
                    "Tamus",
                    "Tantalos",        // A king from a Greek tragedy
                    "Tanuga",
                    "Tarchannen",
                    "Terosa",
                    "Thera",        // A seldom encountered Dutch girl's name
                    "Titan",        // The largest moon of Jupiter
                    "Torin",        // A hero from Master of Magic
                    "Triacus",
                    "Turkana",
                    "Tyrus",
                    "Umberlee",
                    "Utopia",        // The ultimate goal
                    "Vadera",
                    "Vagra",
                    "Vandor",
                    "Ventax",
                    "Xenon",
                    "Xerxes",        // A Greek hero
                    "Yew",            // A city which is in almost all of the Ultima games
                    "Yojimbo",        // A film by Akira Kurosawa
                    "Zalkon",
                    "Zuul"            // From the first Ghostbusters movie
            };
            solarSystems.add(new SolarSystem(location, solarSystemNames[solarSystemCount]));
            solarSystemCount++;
        }
    }

//    /**
//     * default constructor to initialize a Universe object with 100 Solar Systems
//     */
//    public Universe() {
//        this(100);
//    }

    /**
     * toString method that overrides Object's toString method that returns a list of each Solar
     * System's name and location
     *
     * @return a String representation of each Solar System's name and location in the Universe
     */
    @NonNull
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (SolarSystem system: solarSystems) {
            str.append(system.toString());
        }
        return str.toString();
    }

    /**
     * method that gets a random solar system
     * @return the solar system that was randomly chosen
     */
    public SolarSystem getRandomSolarSystem() {
        return (SolarSystem)solarSystems.toArray()[0];
    }
}
