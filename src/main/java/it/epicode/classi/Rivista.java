package it.epicode.classi;

import it.epicode.enums.Periodicita;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "riviste")
public class Rivista extends ElementoCatalogo {

    @Column
    @Enumerated(EnumType.STRING)
    private Periodicita periodicita;
}
