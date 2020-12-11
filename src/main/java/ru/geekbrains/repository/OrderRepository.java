package ru.geekbrains.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.geekbrains.entities.Client;
import ru.geekbrains.entities.Order;
import ru.geekbrains.entities.OrderStatus;
import ru.geekbrains.entities.Person;
import ru.geekbrains.registry.Registry;
import ru.geekbrains.repository.dto.OrderView;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class OrderRepository implements Repository<Order>{

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public OrderRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Order findById(Integer id) {
        OrderView view = jdbcTemplate.query(
                "select o.id, o.createdDate, o.status, c.id, c.name, c.passportId," +
                        " car.id, car.name, car.isFragile," +
                        " b.id, b.latitude, b.longitude" +
                        " from order o" +
                        "join client c on c.id = o.clientId" +
                        "join branch b on b.id = o.destinationId" +
                        "join cargo car on car.id = o.cargoId" +
                        "where o.id = ?",
                (r, i) -> OrderView.builder()
                .orderId(r.getInt(1))
                .createdDate(r.getTimestamp(2))
                .status(r.getString(3))
                .clientId(r.getInt(4))
                .clientName(r.getString(5))
                .passportId(r.getString(6))
                .build(),
                id
        ).stream().findAny().orElse(null);

        return mapOrder(view);
    }

    public List<Order> findByCreatedDate(LocalDate createdDate){
        List<OrderView> view = jdbcTemplate.query(
                "select o.id, o.createdDate, o.status, c.id, c.name, c.passportId," +
                        " car.id, car.name, car.isFragile," +
                        " b.id, b.latitude, b.longitude" +
                        " from order o" +
                        "join client c on c.id = o.clientId" +
                        "join branch b on b.id = o.destinationId" +
                        "join cargo car on car.id = o.cargoId" +
                        "where o.createdDate = ?",
                (r, i) -> OrderView.builder()
                        .orderId(r.getInt(1))
                        .createdDate(r.getTimestamp(2))
                        .status(r.getString(3))
                        .clientId(r.getInt(4))
                        .clientName(r.getString(5))
                        .passportId(r.getString(6))
                        .build(),
                Timestamp.valueOf(createdDate.atStartOfDay())
        );

        return view.stream().map(this::mapOrder).collect(Collectors.toList());
    }

    private Order mapOrder(OrderView view){
        return Order.builder()
                .id(view.getOrderId())
                .createdDate(view.getCreatedDate().toLocalDateTime().toLocalDate())
                .status(OrderStatus.valueOf(view.getStatus()))
                .client(mapPerson(view))
                .build();
    }

    private Person mapPerson(OrderView view){
        return Person.builder()
                .id(view.getClientId())
                .name(view.getClientName())
                .passportId(view.getPassportId())
                .build();
    }

    @Override
    public Order save(Order entity) {
        return null;
    }

    @Override
    public void update(Order entity) {

    }

    @Override
    public void delete(Order entity) {

    }
}
