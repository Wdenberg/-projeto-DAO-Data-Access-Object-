package model.dao.impl;

import db.DB.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DepartmentDaoJDBC implements DepartmentDao {
    private Connection connection;
    public DepartmentDaoJDBC(Connection connection){
        this.connection = connection;
    }


    @Override
    public void insert(Department obj) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO department "
                            + "(Name) "
                            + "VALUES"
                            + "(?)",
                    Statement.RETURN_GENERATED_KEYS
            );

            preparedStatement.setString(1, obj.getName());
            int rowsAffected = preparedStatement.executeUpdate();

            if(rowsAffected > 0){
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if(resultSet.next()){
                    int id = resultSet.getInt(1);
                    obj.setId(id);
                }
            }
            else {
                throw  new DbException(" Unexpected error! No Rows Affected! ");
            }

        }catch (SQLException e){
            throw new DbException(e.getMessage());

        }finally {
            DB.closeStatement(preparedStatement);
        }
    }

    @Override
    public void update(Department obj) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "UPDATE department " +
                            "SET Name = ? " +
                            "WHERE Id = ?"
            );

            preparedStatement.setString(1,obj.getName());
            preparedStatement.setInt(2, obj.getId());
            preparedStatement.executeUpdate();



        }catch (SQLException e){
            throw new DbException(e.getMessage());

        }finally {
            DB.closeStatement(preparedStatement);
        }
    }

    @Override
    public void deletByID(Integer id) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "DELETE FROM department "
                            + "WHERE Id = ?"
            );
            preparedStatement.setInt(1,id);
            int rows = preparedStatement.executeUpdate();
            if(rows == 0){
                throw new DbException("Id Não Existe");

            }
        }catch (SQLException e ){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(preparedStatement);
        }
    }

    @Override
    public Department findById(Integer id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM department WHERE Id = ?"
            );
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                Department department = instantiateDepartment(resultSet);
                department.getId();
                department.getName();

                return  department;
            }
            return null;
        }catch (SQLException e ){
            throw new DbException(e.getMessage());
        }finally{
            DB.closeStatement(preparedStatement);
            DB.closeResultSet(resultSet);

        }
    }

    private Department instantiateDepartment(ResultSet resultSet) throws  SQLException{
        Department obj = new Department();
        obj.setId(resultSet.getInt("Id"));
        obj.setName(resultSet.getString("Name"));
        return  obj;
    }

    @Override
    public List<Department> findAll() {
        PreparedStatement preparedStatement= null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM department ORDER BY Id "
            );

            resultSet = preparedStatement.executeQuery();
            List<Department> list = new ArrayList<>();

            while (resultSet.next()){
                Department obj = new Department();

               obj.setId(resultSet.getInt("Id"));
               obj.setName(resultSet.getString("Name"));
               list.add(obj);

            }
            return list;
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(preparedStatement);
            DB.closeResultSet(resultSet);
        }

    }

}
