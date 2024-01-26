/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.a00n.entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;
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
public class Ville implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;

    @ToString.Exclude
    @OneToMany(mappedBy = "ville", fetch = FetchType.EAGER)
    private List<Hotel> hotels;

}
