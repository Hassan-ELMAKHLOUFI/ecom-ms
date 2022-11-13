package org.sid.billing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Objects;

@RestController

public class ConsulController {
    @Autowired
    private MyCondulConfig myCondulConfig;
    @Autowired
    private MyVaultConfig myVaultConfig ;



    @GetMapping("/myConfig")
    public Map<String, Object>  myConfig(){
        return Map.of("consulConfig",myCondulConfig,"vaultconfig",myVaultConfig);
    }
}
