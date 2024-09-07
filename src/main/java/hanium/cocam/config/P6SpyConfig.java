package hanium.cocam.config;

import com.p6spy.engine.spy.P6SpyOptions;
import com.p6spy.engine.spy.appender.CustomLineFormat;
import jakarta.annotation.PostConstruct;
import org.hibernate.engine.jdbc.internal.FormatStyle;
import org.springframework.context.annotation.Configuration;

@Configuration
public class P6SpyConfig extends CustomLineFormat {
    @PostConstruct
    public void setLogMessageFormat() {
        P6SpyOptions.getActiveInstance().setLogMessageFormat(this.getClass().getName());
    }

    @Override
    public String formatMessage(
            int connectionId,
            String now,
            long elapsed,
            String category,
            String prepared,
            String sql,
            String url
    ) {
        sql = formatSql(category, sql);
        return String.format("[%s] | %d ms | %s", category, elapsed, sql);
    }

    private String formatSql(String category, String sql) {
        if (sql != null && !sql.trim().isEmpty() && "statement".equalsIgnoreCase(category)) {
            String trimmedSQL = sql.trim().toLowerCase();
            if (trimmedSQL.startsWith("create") || trimmedSQL.startsWith("alter") || trimmedSQL.startsWith("comment")) {
                return FormatStyle.DDL.getFormatter().format(sql);
            } else {
                return FormatStyle.BASIC.getFormatter().format(sql);
            }
        }
        return sql;
    }
}
