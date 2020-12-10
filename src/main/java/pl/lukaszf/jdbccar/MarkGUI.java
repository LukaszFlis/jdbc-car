package pl.lukaszf.jdbccar;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@Route
public class MarkGUI extends VerticalLayout {
    private TextField textFieldMark;
    private TextArea textArea;
    private Button button;
    CarDao carDao;

    /**
     * Constructs an empty layout with spacing and padding on by default.
     */
    @Autowired
    public MarkGUI(CarDao carDao) {
        this.textFieldMark = new TextField("Mark");
        this.textArea = new TextArea();
        this.button = new Button("show");
        this.carDao = carDao;

        add(textFieldMark, textArea, button);

        //ToDo validation
        button.addClickListener(x ->{
            List <Map<String, Object>> maps = carDao.showByMArk(textFieldMark.getValue());
            textArea.setValue(maps.toString());
        });
    }
}
