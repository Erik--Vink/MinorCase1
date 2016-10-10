package data.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    private int id;
    private String title;
    private String code;
    private LocalDate startDate;
    private int duration;

    public Course(String title, String code, LocalDate startDate, int duration){
        this.title = title;
        this.code = code;
        this.startDate = startDate;
        this.duration = duration;
    }
}
