package designPattern.abstractFactory.exerciceAbsFac;

public class Jeu {
    FactionFactory _factionFactory;

    public Jeu( FactionFactory factionFactory){
        _factionFactory = factionFactory;
    }

    public void tirerAlArc(){
        Archer archer = _factionFactory .creerArcher();
        archer.tirerAlArc();
    }
    public void charger(){
        Cavalier cavalier = _factionFactory .creerCavalier();
        cavalier.charger();
    }
    public void CombattreCorpsaCorps(){
        Infanterie infanterie = _factionFactory .creerInfanterie();
        infanterie.CombattreCorpsaCorps();
    }

}
