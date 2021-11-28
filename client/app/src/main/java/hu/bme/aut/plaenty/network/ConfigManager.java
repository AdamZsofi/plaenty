package hu.bme.aut.plaenty.network;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import hu.bme.aut.plaenty.model.Configuration;


public class ConfigManager {

    private static Configuration activeConfiguration = null;

    public static void setActiveConfiguration(Configuration activeConfiguration, Runnable error){
        NetworkManager.callApi(NetworkManager.getInstance().getActiveConfigurationAPI().activeConfigurationIdPut(activeConfiguration.getId()),
                configuration -> updateConfigurations(error),
                error);
    }

    public static void saveConfiguration(Configuration configuration, Runnable error){
        NetworkManager.callApi(NetworkManager.getInstance().getConfigAPI().configurationIdPut(configuration),
                c -> updateConfigurations(error),
                error);
    }

    public static void uploadNewConfiguration(Configuration configuration, Runnable error){
        NetworkManager.callApi(NetworkManager.getInstance().getConfigAPI().configurationPost(configuration),
                c -> updateConfigurations(error),
                error);
    }

    private static void updateActiveConfiguration(Configuration configuration){
        ConfigManager.activeConfiguration = configuration;
        listeners.forEach(l -> l.activeConfigurationChanged(configuration));
    }

    private static List<Configuration> configurations = new ArrayList<>();

    public static Optional<Configuration> getConfigWithId(Long id){
        if(configurations == null) return Optional.ofNullable(null);
        return configurations.stream().filter(configuration -> configuration.getId() == id).findFirst();
    }

    private static void updateConfiguration(List<Configuration> configurations){
        ConfigManager.configurations.clear();
        ConfigManager.configurations.addAll(configurations);
        listeners.forEach(l -> l.configurationsChanged(configurations));
    }

    public interface ConfigurationChangeListener{
        void configurationsChanged(List<Configuration> configurations);
        void activeConfigurationChanged(Configuration activeConfiguration);
    }

    private static List<ConfigurationChangeListener> listeners = new ArrayList<>();

    public static void addListener(ConfigurationChangeListener listener){
        ConfigManager.listeners.add(listener);
        if(activeConfiguration!=null) listener.activeConfigurationChanged(activeConfiguration);
        if(configurations!=null) listener.configurationsChanged(configurations);
    }

    public static void updateConfigurations(Runnable error){
        NetworkManager.callApi(NetworkManager.getInstance().getConfigAPI().configurationListGet(),
                ConfigManager::updateConfiguration,
                error);
        NetworkManager.callApi(NetworkManager.getInstance().getActiveConfigurationAPI().getActiveConfiguration(),
                ConfigManager::updateActiveConfiguration,
                error);
    }

}
