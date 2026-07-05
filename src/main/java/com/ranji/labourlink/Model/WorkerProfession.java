package com.ranji.labourlink.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "worker_profession")
public class WorkerProfession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "worker_id")
    private Worker worker;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profession_id")
    private Profession profession;

    private Integer experience;

    @Column(name = "daily_wage")
    private Integer dailyWage;

    public WorkerProfession() {
    }

    public WorkerProfession(Worker worker,
                            Profession profession,
                            Integer experience,
                            Integer dailyWage) {
        this.worker = worker;
        this.profession = profession;
        this.experience = experience;
        this.dailyWage = dailyWage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public Integer getDailyWage() {
        return dailyWage;
    }

    public void setDailyWage(Integer dailyWage) {
        this.dailyWage = dailyWage;
    }

    @Override
    public String toString() {
        return "WorkerProfession{" +
                "id=" + id +
                ", experience=" + experience +
                ", dailyWage=" + dailyWage +
                '}';
    }
}