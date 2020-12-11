package ru.geekbrains.service;

import ru.geekbrains.entities.Branch;

import java.util.Random;

public class BranchFactory {
    private final static Random random = new Random();

    public static Branch createBranch(BranchInfoDto branchInfoDto){
        return Branch.builder()
                .id(random.nextInt(1000))
                .name(branchInfoDto.getName())
                .longitude(branchInfoDto.getLongitude())
                .latitude(branchInfoDto.getLatitude())
                .build();
    }
}
