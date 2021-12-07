package top.autuan.domainchange.domain.check;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckItem {
    private String domain;
    private String branch;
    private String jobName;
}
