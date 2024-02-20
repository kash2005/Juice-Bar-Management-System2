package lk.ijse.layardproject.dao;

import lk.ijse.layardproject.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.layardproject.dao.custom.impl.IngredientDAOImpl;
import lk.ijse.layardproject.dao.custom.impl.SupplierDAOImpl;

public class DAOFactory {
    public static DAOFactory daoFactory;

    public DAOFactory(){}

    public static DAOFactory getDaoFactory(){
        return (daoFactory == null) ? new DAOFactory() : daoFactory;
    }

    public enum DAOTypes{
        CUSTOMER,SUPPLIER,INGREDIENT
    }

    public SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case CUSTOMER :
                return new CustomerDAOImpl();
            case SUPPLIER:
                return new SupplierDAOImpl();
            case INGREDIENT:
                return new IngredientDAOImpl();
            default:
                return null;
        }
    }
}
