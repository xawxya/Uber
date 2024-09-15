package uber.datastructure.log;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "log")
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime time;

    @Column(nullable = false)
    private String sourceModule;

    @Column(nullable = false)
    private String logLevel;  // INFO, DEBUG, WARNING, ERROR

    @Column(nullable = false)
    private String logContent;
}