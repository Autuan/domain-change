package top.autuan.domainchange.domain.record;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "domain.www.prod")
public class WwwProd extends BaseRecord {
}
