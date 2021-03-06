package org.vaadin.connectoridgenerator;

import java.util.ArrayList;
import java.util.List;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import javax.servlet.annotation.WebServlet;

public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest request) {
        VerticalLayout layout;
        setContent(layout = new VerticalLayout());

        Button button = new Button("Test Button", e-> {
            Notification.show("Clicked");
        });
        button.setId("test-button");

        TextField textfield = new TextField("Test TextField");
        textfield.setId("test-textfield");

        Grid<Person> gridSingle = new Grid<>();
        gridSingle.setSelectionMode(Grid.SelectionMode.SINGLE);
        gridSingle.setItems(getPersons());
        gridSingle.addColumn(Person::getName).setCaption("Name");
        gridSingle.addColumn(Person::getAge).setCaption("Age");
        gridSingle.setId("test-gridSingle");

        Grid<Person> gridMulti = new Grid<>();
        gridMulti.setSelectionMode(Grid.SelectionMode.MULTI);
        gridMulti.setItems(getPersons());
        gridMulti.addColumn(Person::getName).setCaption("Name");
        gridMulti.addColumn(Person::getAge).setCaption("Age");
        gridMulti.setId("test-gridMulti");


        layout.addComponents(button, textfield, gridSingle, gridMulti);
    }

    private List<Person> getPersons() {
        List<Person> persons = new ArrayList<>();
        for (int i=0; i<1000; i++) {
            persons.add(new Person("Test"+i,i));
        }
        return persons;
    }

    public class Person {

        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
