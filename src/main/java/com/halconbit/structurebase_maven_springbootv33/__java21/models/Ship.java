package com.halconbit.structurebase_maven_springbootv33.__java21.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ship")
public class Ship {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="activation")
    private boolean activation;

    @Column(name="locked")
    private boolean locked;

    @Column(name="delete")
    private boolean delete;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="creationDate", nullable = false)
    private Date creationDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="modificationDate")
    private Date modificationDate;
}
