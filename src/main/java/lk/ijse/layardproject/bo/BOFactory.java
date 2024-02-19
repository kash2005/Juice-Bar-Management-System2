package lk.ijse.layardproject.bo;

import lk.ijse.layardproject.bo.custom.CustomerBO;
import lk.ijse.layardproject.bo.custom.impl.CustomerBOImpl;

public class BOFactory {
    public static BOFactory boFactory;

    private BOFactory(){}

    public static BOFactory getBoFactory(){
        return (boFactory==null) ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes{
        CUSTOMER
    }

    public SuperBO getBO(BOTypes boTypes){
        switch (boTypes){
            case CUSTOMER :
                return new CustomerBOImpl();
            default:
                return null;
        }
    }
}