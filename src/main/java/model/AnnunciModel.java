package model;

import java.util.ArrayList;
import java.util.List;


public class AnnunciModel {
    private List<AnnuncioModel> listaAnnunci;

    public AnnunciModel() {
        listaAnnunci = new ArrayList<>();
    }

    public List<AnnuncioModel> getListaDiAnnunci() {
        return listaAnnunci;
    }


    //NELLA SET L'OBIETTIVO E' AGGIUNGERE E RIMUOVERE MAGARI? (PER ORA NON CREDO LA USERò MAI QUESTA SET)
    public void setListaManga(List<AnnuncioModel> listaAnnunci) {
        this.listaAnnunci = listaAnnunci;
    }



}

