package com.ryan.java.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

public class RmiClient {

    public static void main(String[] args) {
        //调用远程对象，注意RMI路径与接口必须与服务器配置一致
        try {
            PersonService personService = (PersonService) Naming.lookup("rmi://127.0.0.1:6600/PersonService");
            List<PersonEntity> list = personService.getList();
            for(PersonEntity entity : list) {
                System.out.println("Persion name = " + entity.getName() + " age = " + entity.getAge() + " id = " + entity.getId());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }
}
