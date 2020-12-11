package ru.geekbrains.controller;

import ru.geekbrains.registry.UnitOfWork;
import ru.geekbrains.service.BranchInfoDto;
import ru.geekbrains.service.BranchService;

@org.springframework.stereotype.Controller
public class Controller {

    private BranchService branchService;

    /**
     * создает новый пункт выдачи
     * при старте {@link UnitOfWork} инициирует новый кэш в {@link ThreadLocal}
     * по завершению запроса все сохраняется в бд через {@link ru.geekbrains.repository.BranchRepository}
     * @param branchInfoDto полная информация по новому пункту выдачи(название, координаты)
     * @return идентификатор нового пункта выдачи
     */
    public Integer createBranch(BranchInfoDto branchInfoDto){
        UnitOfWork.init();
        Integer branchId = branchService.createBranch(branchInfoDto);
        UnitOfWork.getCurrent().commit();
        return branchId;
    }
}
