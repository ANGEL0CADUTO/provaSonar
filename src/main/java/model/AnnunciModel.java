package model;

import java.util.ArrayList;


public class AnnunciModel {
    private ArrayList<Object[]> listaAnnunci;

    public AnnunciModel() {
        listaAnnunci = new ArrayList<Object[]>();
    }

    public ArrayList<Object[]> getListaDiAnnunci() {
        return listaAnnunci;
    }


    //NELLA SET L'OBIETTIVO E' AGGIUNGERE E RIMUOVERE MAGARI? (PER ORA NON CREDO LA USERò MAI QUESTA SET)
    public void setListaManga(ArrayList<Object[]> listaAnnunci) {
        this.listaAnnunci = listaAnnunci;
    }



}

