package com.ja.spring.data.jpa.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "course")
@Table(
        uniqueConstraints = @UniqueConstraint(
                name = "url_unique",
                columnNames = "url"
        )
)
public class CourseMaterial {

    @Id
    @SequenceGenerator(
            sequenceName = "course_material_sequence",
            name = "course_material_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_material_sequence"
    )
    private Long courseMaterialId;

    @Column(nullable = false)
    private String url;

    @OneToOne(mappedBy = "courseMaterial", cascade = CascadeType.ALL, orphanRemoval = true)
    private Course course;

}
