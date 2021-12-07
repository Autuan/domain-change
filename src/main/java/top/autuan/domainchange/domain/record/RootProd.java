package top.autuan.domainchange.domain.record;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "domain.root.prod")
public class RootProd extends BaseRecord {
}
