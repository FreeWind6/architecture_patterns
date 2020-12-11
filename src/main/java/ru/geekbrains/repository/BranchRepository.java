package ru.geekbrains.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.geekbrains.entities.Branch;
import ru.geekbrains.registry.Registry;
import ru.geekbrains.registry.UnitOfWork;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class BranchRepository implements Repository<Branch> {

    private static final String QUERY_SAVE = "insert into branch(id, name, latitude, longitude) values (?, ?, ?, ?)";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public BranchRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Branch findById(Integer id) {
        Branch branch = Registry.getInstance().getIdentityMap().find(id);
        if(branch == null) {
            return jdbcTemplate.query(
                    "select id, name, latitude, longitude from branch where id = ?",
                    (r, i) -> Branch.builder()
                            .id(r.getInt(1))
                            .name(r.getString(2))
                            .latitude(r.getString(3))
                            .longitude(r.getString(4))
                            .build(),
                    id).stream().findAny().orElse(null);
        } else {
            return branch;
        }
    }

    /**
     * здесь происходит сохранение в бд
     * @param entity
     * @return
     */
    @Override
    public Branch save(Branch entity) {
        return null;
    }

    /**
     * делает массовую вставку в бд
     * используется в {@link UnitOfWork#insertNew()}
     * @param entities сущности которые собираемся сохранить в бд
     */
    public void saveBatch(List<Branch> entities){
        jdbcTemplate.batchUpdate(
                QUERY_SAVE,
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                        Branch branch = entities.get(i);
                        preparedStatement.setInt(1, branch.getId());
                        preparedStatement.setString(2, branch.getName());
                        preparedStatement.setString(3, branch.getLatitude());
                        preparedStatement.setString(4, branch.getLongitude());
                    }

                    @Override
                    public int getBatchSize() {
                        return entities.size();
                    }
                }
        );
    }

    @Override
    public void update(Branch entity) {
    }

    @Override
    public void delete(Branch entity) {

    }
}
