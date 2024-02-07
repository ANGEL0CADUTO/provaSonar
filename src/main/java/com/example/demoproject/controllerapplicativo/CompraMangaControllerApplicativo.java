package com.example.demoproject.controllerapplicativo;

import com.example.demoproject.dao.AnnuncioDAO;
import com.example.demoproject.model.AnnunciModel;

public class CompraMangaControllerApplicativo {
    public AnnunciModel showAnnunce() {
     AnnuncioDAO dao = new AnnuncioDAO();
     return dao.getAnnunci();
    }
}
