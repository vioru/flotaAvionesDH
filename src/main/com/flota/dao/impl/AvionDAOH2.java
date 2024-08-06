package main.com.flota.dao.impl;

import main.com.flota.dao.ADAO;
import main.com.flota.model.Avion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AvionDAOH2  implements ADAO<Avion>{

    private final static String DB_JDBC_DRIVER = "org.h2.Driver";
    private final static String DB_URL = "jdbc:h2:~/db_aviones";
    private final static String DB_USER ="sa";
    private final static String DB_PASSWORD = "";

    public AvionDAOH2() {
    }

    @Override
    public Avion registrarAvion(Avion avion) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);

            preparedStatement =connection.prepareStatement("INSERT INTO aviones VALUES(?,?,?,?,?)");
            preparedStatement.setLong(1,avion.getId());
            preparedStatement.setString(2,avion.getMarca());
            preparedStatement.setString(3,avion.getModelo());
            preparedStatement.setString(4,avion.getMatricula());
            preparedStatement.setString(5,avion.getFechaEntrada());

            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return avion;
    }

    @Override
    public Avion buscarAvion(Long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Avion avion = null;

        try{
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);

            preparedStatement =connection.prepareStatement("SELECT id,modelo,marca,matricula, fechaEntrada FROM aviones where id = ?");
            preparedStatement.setLong(1,id);

            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                Long idAvion = result.getLong("id");
                String modelo = result.getString("modelo");
                String marca = result.getString("marca");
                String matricula = result.getString("matricula");
                String fechaEntrada = result.getString("fechaEntrada");
                avion = new Avion(idAvion , modelo, marca, matricula,fechaEntrada);
            }


            preparedStatement.close();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return avion;
    }

    @Override
    public void eliminarAvion(Long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;


        try{
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);

            preparedStatement =connection.prepareStatement("DELETE FROM aviones where id = ?");
            preparedStatement.setLong(1,id);


            preparedStatement.executeUpdate();
            preparedStatement.close();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Avion> buscarAviones() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Avion> aviones = new ArrayList<>();

        try{
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);

            preparedStatement = connection.prepareStatement("SELECT * FROM aviones");

            ResultSet result= preparedStatement.executeQuery();

            while (result.next()) {
                Long idAvion = result.getLong("id");
                String modelo = result.getString("modelo");
                String marca = result.getString("marca");
                String matricula = result.getString("matricula");
                String fechaEntrada = result.getString("fechaEntrada");
                aviones.add(new Avion(idAvion , modelo, marca, matricula,fechaEntrada));
            }

            preparedStatement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return aviones;

    }
}
