package designPattern.abstractFactory.exerciceAbsFac;

public class OrcFactory implements FactionFactory{

    @Override
    public ArcherHumain creerArcher() {
        return null;
    }

    @Override
    public InfanterieHumain creerInfanterie() {
        return null;
    }

    @Override
    public CavalierHumain creerCavalier() {
        return null;
    }
}
