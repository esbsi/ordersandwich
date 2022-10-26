package be.abis.ordersandwich.repository;

import be.abis.ordersandwich.exception.SandwichTypeNotFoundException;
import be.abis.ordersandwich.model.SandwichType;
import be.abis.ordersandwich.model.Shop;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FileSandwichTypeRepository implements SandwichTypeRepository{

    private Shop shop;
    private String fileDirectory;
    private List<SandwichType> sandwichTypes;

    public FileSandwichTypeRepository() {
    }

    @Override
    public void setShop(Shop shop) {
        this.shop = shop;
        this.fileDirectory = shop.getName() + "SandwichTypes.csv";
        loadSandwichTypes();
    }

    @Override
    public Shop getShop() {
        return shop;
    }


    // ToDo at setShop()
    public void loadSandwichTypes() {
        List<SandwichType> sandwichTypeList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileDirectory))) {
                String line;
            while ((line = reader.readLine()) != null) {
                String[] sandwichAttributes = line.split(";");
                SandwichType sandwichType = new SandwichType();
                sandwichType.setName(sandwichAttributes[0]);
                sandwichType.setPrice(Double.parseDouble(sandwichAttributes[1]));
                sandwichType.setCategory(sandwichAttributes[2]);
                sandwichType.setVegetarian(Boolean.parseBoolean(sandwichAttributes[3]));
                if (!"null".equals(sandwichAttributes[4])){
                    sandwichType.setDescription(sandwichAttributes[4]);
                }
                sandwichTypeList.add(sandwichType);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } this.sandwichTypes = sandwichTypeList;
    }

    @Override
    public SandwichType findSandwichType(String sandwichName) throws SandwichTypeNotFoundException {
        return getSandwichTypes().stream()
                .filter(sandwichType -> sandwichName.equals(sandwichType.getName()))
                .findFirst().orElseThrow(SandwichTypeNotFoundException::new);
    }


    // getset

    @Override
    public List<SandwichType> getSandwichTypes() {
        return sandwichTypes;
    }

}
