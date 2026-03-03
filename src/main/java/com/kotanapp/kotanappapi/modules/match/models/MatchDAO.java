package com.kotanapp.kotanappapi.modules.match.models;

import com.kotanapp.kotanappapi.modules.competition.models.CompetitionDAO;
import com.kotanapp.kotanappapi.modules.team.models.TeamDAO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.config.YamlProcessor;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "match")
public class MatchDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "home_team_id")
    private TeamDAO homeTeam;

    @ManyToOne
    @JoinColumn(name = "away_team_id")
    private TeamDAO awayTeam;

    @ManyToOne
    @JoinColumn(name = "competition_id")
    private CompetitionDAO competition;

    @Column(name = "match_date")
    private LocalDateTime matchDate;

    @Column(name = "stadium")
    private String stadium;

    @Column(name = "home_score")
    private Integer homeScore;

    @Column(name = "away_score")
    private Integer awayScore;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private YamlProcessor.MatchStatus status;

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
        MatchDAO other = (MatchDAO) obj;

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