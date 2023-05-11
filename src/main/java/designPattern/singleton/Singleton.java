package designPattern.singleton;

// singleton no thread safe car
//fonctionnera sans problème dans le cadre d'une application mono thread.
//Par contre avec le multi thread chaque thread traité par l' o.s. se voit
//attribuer un jeton et peut si une partie du jeton a été lue (par deux thread différents) puis que l'o.s. s'arrête,
//et revient lire la fin du code malgré tout instancier cette classe 2 fois.

public class Singleton {

    private static Singleton instance = null;

    private Singleton(){


    }

    public static Singleton getInstance(){
        if(instance == null)
            instance = new Singleton();
        return instance;
    }
}
