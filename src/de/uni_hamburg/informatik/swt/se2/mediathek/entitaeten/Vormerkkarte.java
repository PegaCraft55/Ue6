package de.uni_hamburg.informatik.swt.se2.mediathek.entitaeten;

import java.util.LinkedList;

import de.uni_hamburg.informatik.swt.se2.mediathek.entitaeten.medien.Medium;

/**
 * Mit Hilfe von Vormerkkarten können Medien von maximal drei Kunden vorgemerkt werden.
 * 
 * Wenn Medien zurück gegeben werden, kann die zugehörige Verleihkarte entsorgt
 * werden. Um die Verwaltung der Karten kümmert sich der VerleihService
 * 
 * @author Niclas
 * @version SoSe 2026
 */
public class Vormerkkarte
{

    // Eigenschaften einer Verleihkarte
    private final LinkedList<Kunde> _vormerker;
    private final Medium _medium;

    /**
     * Initialisert eine neue Vormerkkarte mit den gegebenen Daten.
     * 
     * @param vormerker Eine Liste an Kunden, die das Medium vormerken.
     * @param medium Ein vorgemerktes Medium.
     * 
     * @require vormerker != null
     * @require medium != null
     * 
     * @ensure #getEntleiher() == vormerker
     * @ensure #getMedium() == medium
     * @ensure #getAusleihdatum() == ausleihdatum
     */
    public Vormerkkarte(Medium medium)
    {
        assert medium != null : "Vorbedingung verletzt: medium != null";

        _vormerker = new LinkedList<>();
        _medium = medium;
    }

    /**
     * Gibt den Entleiher zurück.
     * 
     * @return den Kunden, der das Medium entliehen hat.
     * 
     * @ensure result != null
     */
    public LinkedList<Kunde> getVormerker()
    {
        return new LinkedList<>(_vormerker);
    }

    /**
     * Gibt das Medium, dessen Ausleihe auf der Karte vermerkt ist, zurück.
     * 
     * @return Das Medium, dessen Ausleihe auf dieser Karte vermerkt ist.
     * 
     * @ensure result != null
     */
    public Medium getMedium()
    {
        return _medium;
    }
    
    public boolean istKunde(Kunde kunde)
    {
    	if (_vormerker.contains(kunde))
    	{
    		return false;
    	}
    	else
    	{
    		return true;
    	}
    }

    public boolean kannVorgemerktWerden()
    {
        return _vormerker.size() < 3;
    }

    /**
     * Fuegt einen Kunden hinzu.
     * 
     * @param kunde ist der hinzufügende Kunde
     * 
     * @require kannVorgemerktWerden() == true
     * @require kunde != null
     * @require !_vormerker.contains(kunde)
     * 
     */
    public void fuegeKundenHinzu(Kunde kunde)
    {
        assert kunde != null : "Vorbedingung verletzt: kunde != null";
        assert !kannVorgemerktWerden() : "Vorbedingung verletzt: !kannVorgemerktWerden()";
        assert !_vormerker.contains(
                kunde) : "Vorbedingung verletzt: !_vormerker.contains(kunde)";

        _vormerker.add(kunde);
    }
    

    /**
     * Entfernt einen Kunden aus der Vormerker-Liste.
     * 
     * @param kunde Der zu entfernende Kunde.
     * 
     * @require kunde != null
     */
    public void entferneKunden(Kunde kunde)
    {
        assert kunde != null : "Vorbedingung verletzt: kunde != null";
        
        _vormerker.remove(kunde);
    }
    
    /**
     * Gibt einen formatierten String mit den Informationen der Vormerkkarte zurück.
     *
     * @return Eine Textrepräsentation der Vormerkkarte.
     *
     * @ensure result != null
     */
    public String getFormatiertenString()
    {
        String result = "Vormerkkarte:\n";
        result += "  Medium: " + _medium.getTitel() + "\n";
        result += "  Vormerker:\n";
        for (Kunde k : _vormerker)
        {
            result += "    - " + k.getVorname() + " " + k.getNachname() + "\n";
        }
        return result;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((_vormerker == null) ? 0 : _vormerker.hashCode());
        result = prime * result + ((_medium == null) ? 0 : _medium.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (!(obj instanceof Vormerkkarte)) return false;
        Vormerkkarte other = (Vormerkkarte) obj;
        return _medium.equals(other._medium);
    }
    

    @Override
    public String toString()
    {
        return getFormatiertenString();
    }
}
