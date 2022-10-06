package de.turing85.quarkus.extension.config.bug.runtime;

import io.quarkus.runtime.annotations.ConfigItem;
import io.quarkus.runtime.annotations.ConfigRoot;
import java.util.Map;

@ConfigRoot(name = "my-config")
public class MyConfigs {
  /**
   * Named configurations
   */
  @ConfigItem(name = ConfigItem.PARENT)
  Map<String, MyConfig> namedConfigs;
}
