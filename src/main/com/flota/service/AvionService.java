package main.com.flota.service;

import main.com.flota.dao.ADAO;
import main.com.flota.model.Avion;

import java.util.List;

public class AvionService {

    private ADAO<Avion> avionDao;

    public AvionService(){

    }

    public AvionService(ADAO<Avion> avionDao) {
        this.avionDao = avionDao;
    }

    public void setAvionDao(ADAO<Avion> avionDao) {
        this.avionDao = avionDao;
    }

    public Avion registrarAvion(Avion avion){
        avionDao.registrarAvion(avion);
        return avion;
    }

    public void  eliminarAvion(Long id){
        avionDao.eliminarAvion(id);
    }

    public Avion buscarAvion(Long id){
        return avionDao.buscarAvion(id);
    }

    public List<Avion> buscarAviones(){
        return avionDao.buscarAviones();
    }



}
