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
    public List<SandwichType> getSandwichTypes(){
        return sandwichTypeRepository.findAll();
    }

    @Override
    public SandwichType addSandwichType(SandwichType sandwichType) throws SandwichTypeAlreadyExistsException {
        SandwichType s=sandwichTypeRepository.findSandwichTypeById(sandwichType.getId());
        if (s!=null) throw new SandwichTypeAlreadyExistsException("sandwichtype with this Id already exists");
        return sandwichTypeRepository.save(sandwichType);
    }

    @Override
    public SandwichType findSandwichTypeById(int id) throws SandwichTypeNotFoundException{
        return sandwichTypeRepository.findSandwichTypeById(id);
    }

    @Override
    public SandwichType findSandwichType(String sandwichName) throws SandwichTypeNotFoundException{
        return sandwichTypeRepository.findSandwichTypeByName(sandwichName);
    }

    @Override
    public void removeSandwichType(SandwichType sandwichType) throws SandwichTypeNotFoundException{
        findSandwichTypeById(sandwichType.getId());
        sandwichTypeRepository.delete(sandwichType);
    }

}
