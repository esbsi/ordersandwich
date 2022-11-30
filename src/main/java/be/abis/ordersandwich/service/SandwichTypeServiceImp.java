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
    public List<SandwichType> findSandwichtypesByShop(Shop shop) throws SandwichTypeNotFoundException {
        List<SandwichType> sandwichTypes = sandwichTypeRepository.getSandwichTypeByShop(shop);
        if (0 == sandwichTypes.size()) throw new SandwichTypeNotFoundException("Sandwich type not found.");
        return sandwichTypes;
    }

    @Override
    public SandwichType findSandwichTypeById(int id) throws SandwichTypeNotFoundException{
        SandwichType sandwichType = sandwichTypeRepository.findSandwichTypeById(id);
        if (null == sandwichType) throw new SandwichTypeNotFoundException("Sandwich type not found.");
        return sandwichType;
    }

    @Override
    public SandwichType findSandwichType(String sandwichName, int shopId) throws SandwichTypeNotFoundException{
        SandwichType sandwichType = sandwichTypeRepository.findSandwichTypeByNameAndShop_Id(sandwichName, shopId);
        if (null == sandwichType) throw new SandwichTypeNotFoundException("Sandwich type not found.");
        return sandwichType;
    }

    @Override
    public SandwichType addSandwichType(SandwichType sandwichType) throws SandwichTypeAlreadyExistsException {
        try {SandwichType s = findSandwichType(sandwichType.getName(), sandwichType.getShop().getId());
            if (s!=null) throw new SandwichTypeAlreadyExistsException("sandwichtype with this Id already exists");
        } catch (SandwichTypeNotFoundException e){}
        return sandwichTypeRepository.save(sandwichType);
    }

    @Override
    public void removeSandwichType(SandwichType sandwichType) throws SandwichTypeNotFoundException{
        findSandwichTypeById(sandwichType.getId());
        sandwichTypeRepository.delete(sandwichType);
    }

}
