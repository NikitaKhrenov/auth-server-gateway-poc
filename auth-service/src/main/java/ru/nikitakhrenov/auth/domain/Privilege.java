package ru.nikitakhrenov.auth.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "privileges")
@Entity
public class Privilege {

    @Id
    @SequenceGenerator(name = "privileges_id_seq", sequenceName = "privileges_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "privileges_id_seq")
    @Column(updatable = false)
    private Integer id;

    @Column
    private String name;
}
