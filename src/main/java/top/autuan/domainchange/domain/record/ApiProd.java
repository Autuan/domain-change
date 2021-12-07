package top.autuan.domainchange.domain.record;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "domain.api.prod")
public class ApiProd extends BaseRecord {
}
