package com.example.tododevelop.entity;



import jakarta.persistence.*;
import lombok.Getter;



@Entity
@Getter
@Table(name = "comment")
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String comment;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    public Comment() {

    }

    public Comment(Long id, String comment, Member member, Schedule schedule) {
        this.id = id;
        this.comment = comment;
        this.member = member;
        this.schedule = schedule;
    }

    public Comment(String comment) {
        this.comment = comment;
    }
}
