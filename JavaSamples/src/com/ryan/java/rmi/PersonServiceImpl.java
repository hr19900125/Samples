package com.ryan.java.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.List;

public class PersonServiceImpl extends UnicastRemoteObject implements PersonService{

    protected PersonServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public List<PersonEntity> getList() throws RemoteException {
        System.out.println("Get Person Start!");
        List<PersonEntity> personList = new LinkedList<PersonEntity>();
        
        PersonEntity p1 = new PersonEntity();
        p1.setAge(18);
        p1.setId(0);
        p1.setName("Tom");
        personList.add(p1);
        
        PersonEntity p2 = new PersonEntity();
        p2.setAge(16);
        p2.setId(1);
        p2.setName("Jimmy");
        personList.add(p2);
        
        return personList;
    }

}
