package top.autuan.domainchange.domain.record;

import lombok.Data;

@Data
public class BaseRecord {
    private String id;
    private String prefix;
    private String valMain;
    private String valBackUp;
}
