package ru.geekbrains.registry;

import ru.geekbrains.entities.Branch;

public class BranchFinder {
    public Branch find(Integer id){
        return Branch.builder()
                .name("1")
                .latitude("55.55")
                .longitude("55.55")
                .build();
    };
}
