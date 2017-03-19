package ar.edu.unlp.dsa;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by acollard on 19/3/17.
 */
@ConfigurationProperties("ar.edu.unlp.dsa")
@Component
public class ApplicationConfigurationConstants {

    private String storageRootPath;
    private String apiPrefix;
    private String frontendPort;

    public String getStorageRootPath() {
        return storageRootPath;
    }

    public void setStorageRootPath(String storageRootPath) {
        this.storageRootPath = storageRootPath;
    }

    public String getApiPrefix() {
        return apiPrefix;
    }

    public void setApiPrefix(String apiPrefix) {
        this.apiPrefix = apiPrefix;
    }

    public String getFrontendPort() {
        return frontendPort;
    }

    public void setFrontendPort(String frontendPort) {
        this.frontendPort = frontendPort;
    }
}
