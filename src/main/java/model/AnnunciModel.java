package model;

import java.util.ArrayList;


public class AnnunciModel {
    private ArrayList<AnnuncioModel> listaAnnunci;

    public AnnunciModel() {
        listaAnnunci = new ArrayList<AnnuncioModel>();
    }

    public ArrayList<AnnuncioModel> getListaDiAnnunci() {
        return listaAnnunci;
    }


    //NELLA SET L'OBIETTIVO E' AGGIUNGERE E RIMUOVERE MAGARI? (PER ORA NON CREDO LA USERò MAI QUESTA SET)
    public void setListaManga(ArrayList<AnnuncioModel> listaAnnunci) {
        this.listaAnnunci = listaAnnunci;
    }



}

