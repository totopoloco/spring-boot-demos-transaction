package at.mavila.transaction.db.repository;

import at.mavila.transaction.db.entity.LogEntry;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogEntryRepository extends JpaRepository<LogEntry, UUID> {
}
