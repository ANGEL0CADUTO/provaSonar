package com.example.demoproject.model;

import com.example.demoproject.model.CopiaMangaModel;

import java.util.ArrayList;

//PER ORA MI SERVE PER MOSTRARE LA LISTA MANGA ALL'UTENTE//

public class CopiaMangaCollectionModel {
    private ArrayList<CopiaMangaModel> listaManga;

    public CopiaMangaCollectionModel() {
        listaManga = new ArrayList<>();
    }

    public ArrayList<CopiaMangaModel> getListaManga() {
        return listaManga;
    }


    //NELLA SET L'OBIETTIVO E' AGGIUNGERE E RIMUOVERE MAGARI? (PER ORA NON CREDO LA USERò MAI QUESTA SET)
    public void setListaManga(ArrayList<CopiaMangaModel> listaManga) {
        this.listaManga = listaManga;
    }
}
