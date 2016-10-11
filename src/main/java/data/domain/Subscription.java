package data.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Erik on 11-10-2016.
 */
@NoArgsConstructor
@Data
@Builder
public class Subscription {

    private int courseId;
    private int courseParticipantId;

    public Subscription(int courseId, int courseParticipantId){
        this.courseId = courseId;
        this.courseParticipantId = courseParticipantId;
    }
}
