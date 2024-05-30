package fr.sorbonne.paris.nord.university.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TeamDto {
    private Long id;
    private String name;
    private String slogan;
}
