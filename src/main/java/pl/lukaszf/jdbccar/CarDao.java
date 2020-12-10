package pl.lukaszf.jdbccar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CarDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CarDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Car car) {
        String sql = "INSERT INTO car VALUES(?,?,?,?)";
        jdbcTemplate.update(sql, new Object[]{
                car.getCarId(),
                car.getMark(),
                car.getModel(),
                car.getColor()
        });
    }

    public List<Map<String, Object>> showByMArk(String mark) {
        String sql = "SELECT * FROM car WHERE mark LIKE ?";
        return jdbcTemplate.queryForList(sql, new Object[] {mark});
    }

    @EventListener(ApplicationReadyEvent.class)
    public void dbInit() {
        save(new Car(1, "Fiat", "126p", "red"));
        save(new Car(2, "Fiat", "125p", "black"));
        save(new Car(3, "Audi", "Q7", "white"));
        save(new Car(4, "Audi", "A6", "purple"));
        //String sql = "CREATE TABLE car(car_id int, mark varchar(255), model varchar(255), color varchar(255));";
        //Config.getJdbcTemplate().update(sql);
    }
}
