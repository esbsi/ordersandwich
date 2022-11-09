package be.abis.ordersandwich.repository;

import be.abis.ordersandwich.exception.SandwichTypeAlreadyExistsException;
import be.abis.ordersandwich.exception.SandwichTypeNotFoundException;
import be.abis.ordersandwich.exception.ShopAlreadyExistsException;
import be.abis.ordersandwich.model.SandwichType;
import be.abis.ordersandwich.model.Shop;
import org.springframework.stereotype.Repository;

import java.io.*;
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
        this.fileDirectory = "src/main/resources/" + shop.getName() + "SandwichTypes.csv";
        loadSandwichTypes();
    }

    public void loadSandwichTypes() {
        List<SandwichType> sandwichTypeList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileDirectory))) {
            String line;
            int id = 0;
            while ((line = reader.readLine()) != null) {
                id++;
                String[] sandwichAttributes = line.split(";");
                SandwichType sandwichType = new SandwichType();
                sandwichType.setId(id);
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


    // business

    @Override
    public SandwichType findSandwichTypeById(int id) throws SandwichTypeNotFoundException {
        return getSandwichTypes().stream()
                .filter(sandwichType -> id == sandwichType.getId())
                .findFirst().orElseThrow(SandwichTypeNotFoundException::new);
    }

    @Override
    public SandwichType findSandwichType(String sandwichName) throws SandwichTypeNotFoundException {
        return getSandwichTypes().stream()
                .filter(sandwichType -> sandwichName.equals(sandwichType.getName()))
                .findFirst().orElseThrow(SandwichTypeNotFoundException::new);
    }

    public void appendSandwichTypeToFile(SandwichType sandwichType){
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileDirectory, true))){
            writer.append(sandwichType.getName() + ";" + sandwichType.getPrice() + ";" + sandwichType.getCategory() + ";" + sandwichType.getVegetarian() + ";\n");
        } catch (IOException e) {
            throw new RuntimeException(this.getClass().getSimpleName() + " cannot write to file.");
        }
    }
    
    @Override
    public void addSandwichType(SandwichType sandwichType) throws SandwichTypeAlreadyExistsException {
        if (sandwichTypes.contains(sandwichType)) {
            throw new SandwichTypeAlreadyExistsException("A sandwich with this name is already on this shops menu.");
        } else {
            appendSandwichTypeToFile(sandwichType);
            sandwichTypes.add(sandwichType);
        }
    }

    @Override
    public void removeSandwichType(SandwichType sandwichType) throws SandwichTypeNotFoundException {
        loadSandwichTypes();
        if (!sandwichTypes.remove(sandwichType)) {
            throw new SandwichTypeNotFoundException();
        } else {
            try (PrintWriter writer = new PrintWriter(new FileWriter(fileDirectory, false))) {
                writer.append("");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            for (SandwichType s : sandwichTypes){
                appendSandwichTypeToFile(s);
            }
        }
    }
        

    // getset

    @Override
    public Shop getShop() {
        return shop;
    }

    @Override
    public List<SandwichType> getSandwichTypes() {
        return sandwichTypes;
    }

}
