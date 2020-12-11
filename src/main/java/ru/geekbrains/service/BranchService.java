package ru.geekbrains.service;

import ru.geekbrains.entities.Branch;
import ru.geekbrains.registry.IdentityMap;
import ru.geekbrains.repository.BranchRepository;
import ru.geekbrains.repository.Repository;

public class BranchService {

    private BranchRepository repository;

    public String getBranchInfo(Integer id){
        Branch branch = repository.findById(id);
        return branch.getName() + " находится в "
                + branch.getLatitude()
                + " "
                + branch.getLongitude()
                ;
    }

    public Integer createBranch(BranchInfoDto infoDto){
        Branch newBranch = BranchFactory.createBranch(infoDto);
        IdentityMap.getCurrent().add(newBranch);
        return newBranch.getId();
    }
}
