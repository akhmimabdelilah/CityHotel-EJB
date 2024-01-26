package com.a00n.entities;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.*;

/**
 *
 * @author ay0ub
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Hotel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String address;
    private String phone;

    @ManyToOne
    private Ville ville;

}
