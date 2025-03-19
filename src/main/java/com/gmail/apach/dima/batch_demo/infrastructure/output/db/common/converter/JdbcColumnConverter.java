package com.gmail.apach.dima.batch_demo.infrastructure.output.db.common.converter;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JdbcColumnConverter {

    public static LocalDateTime toLocalDateTime(@NonNull ResultSet rs, @NonNull String columnName)
        throws SQLException {
        return Optional
            .ofNullable(rs.getTimestamp(columnName))
            .map(Timestamp::toLocalDateTime)
            .orElse(null);
    }
}
