package lk.ijse.layardproject.dao;

import lk.ijse.layardproject.dao.custom.impl.CustomerDAOImpl;

public class DAOFactory {
    public static DAOFactory daoFactory;

    public DAOFactory(){}

    public static DAOFactory getDaoFactory(){
        return (daoFactory == null) ? new DAOFactory() : daoFactory;
    }

    public enum DAOTypes{
        CUSTOMER
    }

    public SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case CUSTOMER :
                return new CustomerDAOImpl();
            default:
                return null;
        }
    }
}
