/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nf.DPI.DM;

/**
 *
 * @author Loïc
 */
public enum TypeActe {
    // valeurs de l'enum :

    /**
     *
     */
    DIAGNOSTIQUE("DIAGNOSTIQUE"),

    /**
     *
     */
    THERAPEUTIQUE("THERAPEUTIQUE");
    
    // attributs de l'enum :
    private String typeActe;

    private TypeActe(String typeActe) {
        this.typeActe = typeActe;
    }
    
    @Override
    public String toString(){
        return this.getTypeActe();
    }

    /**
     * @return the typeActe
     */
    public String getTypeActe() {
        return typeActe;
    }
}
