package io.duryskuba.interestmatcher.HappeningService.resource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "happenings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Happening {

    @Id
    private String id;
    private String name;
}
