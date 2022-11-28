package be.abis.ordersandwich.service;

import be.abis.ordersandwich.exception.SandwichTypeAlreadyExistsException;
import be.abis.ordersandwich.exception.SandwichTypeNotFoundException;
import be.abis.ordersandwich.model.SandwichType;
import be.abis.ordersandwich.model.Shop;
import be.abis.ordersandwich.repository.SandwichTypeJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SandwichTypeServiceImp implements SandwichTypeService {

    @Autowired
    public SandwichTypeJpaRepository sandwichTypeRepository;

    @Override
    public void setShop(Shop shop){
        sandwichTypeRepository.setShop(shop);
    }

    @Override
    public Shop getShop(){
        return sandwichTypeRepository.getShop();
    }

    @Override
    public List<SandwichType> getSandwichTypes(){
        return sandwichTypeRepository.getSandwichTypes();
    }

    @Override
    public void addSandwichType(SandwichType sandwichType) throws SandwichTypeAlreadyExistsException {
        sandwichTypeRepository.addSandwichType(sandwichType);
    }

    @Override
    public SandwichType findSandwichTypeById(int id) throws SandwichTypeNotFoundException{
        return sandwichTypeRepository.findSandwichTypeById(id);
    }

    @Override
    public SandwichType findSandwichType(String sandwichName) throws SandwichTypeNotFoundException{
        return sandwichTypeRepository.findSandwichType(sandwichName);
    }

    @Override
    public void removeSandwichType(SandwichType sandwichType) throws SandwichTypeNotFoundException{
        sandwichTypeRepository.removeSandwichType(sandwichType);
    }

}
