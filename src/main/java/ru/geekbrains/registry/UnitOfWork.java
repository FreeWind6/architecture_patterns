package ru.geekbrains.registry;

import ru.geekbrains.entities.Branch;
import ru.geekbrains.repository.BranchRepository;

import java.util.LinkedList;
import java.util.List;

/**
 * Класс для поддержки транзакций в сущности {@link }
 */
public class UnitOfWork {

    //Используем ThreadLocal , то поможет инициировать новый объект UnitOfWork в рамках текущего потока
    private static ThreadLocal<UnitOfWork> current = new ThreadLocal<>();

    private IdentityMap identityMap;

    private BranchRepository repository;

    private List<Branch> newBranches = new LinkedList<>();
    private List<Branch> dirtyBranches = new LinkedList<>();
    private List<Branch> removedBranches = new LinkedList<>();

    public UnitOfWork(IdentityMap identityMap) {
        this.identityMap = identityMap;
    }

    /**
     * процесс инициализации транзакции
     * создаются новые объекты {@link UnitOfWork} и {@link IdentityMap},
     * т.к. сам {@link UnitOfWork} нуждается в {@link IdentityMap} при регистрации новых и удалении старых сущностей
     */
    public static void init(){
        IdentityMap.init();
        setCurrent(new UnitOfWork(IdentityMap.getCurrent()));
    }

    /**
     * устанавливает текущий {@link UnitOfWork} в локальный(текущий) поток
     * @param unitOfWork
     */
    private static void setCurrent(UnitOfWork unitOfWork){
        current.set(unitOfWork);
    }

    /**
     * возвращает текущий объект {@link UnitOfWork}
     * альтернатива использованию {@link Registry}
     * @return
     */
    public static UnitOfWork getCurrent(){
        return current.get();
    }

    /**
     * При создании новой сущности {@link Branch} добавим ее в кэш созданных новых
     * @param entity новая созданная сущность
     */
    public void registerNew(Branch entity){
        identityMap.add((Branch)entity);
        if(!newBranches.contains(entity)){
            newBranches.add(entity);
        }
    }

    /**
     * По аналогии регистрируем измененные сущности
     * @param branch
     */
    public void registerDirty(Branch branch){
        if(newBranches.contains(branch)){
            throw new IllegalArgumentException();
        }

        dirtyBranches.add(branch);
    }

    /**
     * регистрация удаленных из бд сущностей
     * @param order
     */
    public void registerRemoved(Branch order){
        newBranches.remove(order);
        dirtyBranches.remove(order);
        removedBranches.add(order);
    }

    /**
     * в самом конце обработки запроса фиксируем изменения в бд( проливаем сущности из коллекций в бд единым скриптом)
     */
    public void commit(){
        insertNew();
        update();
        delete();
    }

    private void delete() {

    }

    private void update() {

    }

    /**
     * сохраняем все сущности из newBranches
     */
    private void insertNew() {
        repository.saveBatch(newBranches);
    }
}
