package ru.geekbrains.registry;

import ru.geekbrains.entities.Branch;

public class Registry {
    private static final Registry instance = new Registry();

    public static Registry getInstance(){
        return instance;
    }

    protected BranchFinder branchFinder = new BranchFinder();

    public BranchFinder getBranchFinder(){
        return branchFinder;
    }

    protected UnitOfWork unitOfWork = UnitOfWork.getCurrent();

    public UnitOfWork getUnitOfWork(){
        return unitOfWork;
    }

    protected IdentityMap identityMap = IdentityMap.getCurrent();

    public IdentityMap getIdentityMap(){
        return identityMap;
    }

}
