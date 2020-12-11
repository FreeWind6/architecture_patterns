package ru.geekbrains.registry;

import ru.geekbrains.entities.Branch;

import java.util.HashMap;
import java.util.Map;

public class IdentityMap {
    private static final ThreadLocal<IdentityMap> current = new ThreadLocal<>();

    public static void init(){
        current.set(new IdentityMap());
    }

    public static IdentityMap getCurrent(){
        return current.get();
    }

    private Map<Integer, Branch> entities = new HashMap<>();

    public Branch find(Integer id){
        return entities.get(id);
    }

    public void add(Branch branch){
        entities.put(branch.getId(), branch);
    }

    public void remove(Branch branch){
        entities.remove(branch.getId(), branch);
    }

    public void clear(){
        entities.clear();
    }
}
