package de.turing85.quarkus.extension.config.bug.runtime;

import io.quarkus.runtime.annotations.ConfigGroup;
import io.quarkus.runtime.annotations.ConfigItem;

@ConfigGroup
public class MyConfig {
  /**
   * The all-mighty fooBar config
   */
  @ConfigItem(name = "foo.bar", defaultValue= "baz")
  String fooBar;
}
