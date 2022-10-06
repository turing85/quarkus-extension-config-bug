# Quarkus Extension development: bug with nested configurations

We have a [configuration `MyConfigs.java`][myConfigsJava] with content:

```java
@ConfigRoot(name = "my-config")
public class MyConfigs {
  /**
   * Named configurations
   */
  @ConfigItem(name = ConfigItem.PARENT)
  Map<String, MyConfig> namedConfigs;
}
```

with [`MyConfig.java`][myConfigJava] being defined as:

```java
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
```

If we now install the project (`./mvnw install`), the compilation of the integration-tests fails with:

```bash
...
Caused by: java.util.NoSuchElementException: SRCFG00014: The config property quarkus.my-config."named".foo.foo.bar is required but it could not be found in any config source
	at io.smallrye.config.SmallRyeConfig.convertValue(SmallRyeConfig.java:294)
...
```

This is caused by:
- the `"."` in `@ConfigItem(name = "foo.bar", defaultValue= "baz")` in [`MyConfig.java`][myConfigJava] and
- the `@ConfigItem(name = ConfigItem.PARENT) Map<String, MyConfig> namedConfigs;` in [`MyConfigs.java`][myConfigsJava].

If we replace all occurrences of `foo.bar` with `fooBar`, everything works as expected.

[myConfigsJava]: ./runtime/src/main/java/de/turing85/quarkus/extension/config/bug/runtime/MyConfigs.java
[myConfigJava]: ./runtime/src/main/java/de/turing85/quarkus/extension/config/bug/runtime/MyConfig.java