package SpringFlux.Start.Lesson_33_WebClient.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Castomer {


    private String id;
    private String name;
    private String surname;
    private Long age;

    public Castomer() {
    }

    @Override
    public String toString() {
        return "Castomer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                '}';
    }

    public Castomer(String id, String name, String surname, Long age) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
    }
}
