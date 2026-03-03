package com.kotanapp.kotanappapi.modules.player.models;

import com.kotanapp.kotanappapi.modules.team.models.TeamDAO;
import com.kotanapp.kotanappapi.utils.enums.PositionEnum;
import com.kotanapp.kotanappapi.utils.enums.PreferredFootEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "player")
public class PlayerDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private TeamDAO team;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "nationality")
    private String nationality;

    @Column(name = "height")
    private Long height;

    @Column(name = "weight")
    private Long weight;

    @Column(name = "preferred_foot")
    @Enumerated(EnumType.STRING)
    private PreferredFootEnum preferredFoot;

    @Column(name = "position")
    @Enumerated(EnumType.STRING)
    private PositionEnum position;

    @Column(name = "photo")
    private String photo;

    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;

    @Column(name = "archived_at")
    private Instant archivedAt;

    @Column(name = "is_archived")
    private Boolean isArchived = false;

    @Override
    public int hashCode() {
        return id != null ? Objects.hash(id) : getClass().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        PlayerDAO other = (PlayerDAO) obj;

        if (id != null && other.id != null) {
            return Objects.equals(id, other.id);
        }

        return this == other;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " [id=" + (id != null ? id : "null") + "]";
    }
}