package click.itkon.skytest.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@ToString
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length = 36, columnDefinition = "char(36)", updatable = false, nullable = false)
    private UUID id;

    @Embedded
    private UserName name;

    @Email
    @Column(unique = true, nullable = false)
    @NotNull
    @Size(min = 3, max = 255)
    private String email;

    @Column(nullable = false)
    @NotNull
    @Size(min = 3)
    private String password;

    @Builder.Default
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExternalProject> externalProjects = new ArrayList<>();

    @CreationTimestamp
    private OffsetDateTime dateCreated;

    @UpdateTimestamp
    private OffsetDateTime dateUpdated;
}
