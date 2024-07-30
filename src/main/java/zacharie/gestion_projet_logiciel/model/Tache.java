package zacharie.gestion_projet_logiciel.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Tache {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private int priority;
    private String responsible;

    @ManyToOne
    @JoinColumn(name = "projet_id")
    private Projet projet;

//    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
//    private List<Activity> activities;
}
