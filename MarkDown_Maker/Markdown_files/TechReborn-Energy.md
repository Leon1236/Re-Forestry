# TechReborn-Energy

## Directory structure

```text
TechReborn-Energy/
├── .github/
│   └── workflows/
│       ├── build.yml
│       └── release.yml
├── gradle/
│   └── wrapper/
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── team/
│   │   │       └── reborn/
│   │   │           └── energy/
│   │   │               ├── api/
│   │   │               │   ├── base/
│   │   │               │   │   ├── DelegatingEnergyStorage.java
│   │   │               │   │   ├── InfiniteEnergyStorage.java
│   │   │               │   │   ├── LimitingEnergyStorage.java
│   │   │               │   │   ├── SimpleEnergyItem.java
│   │   │               │   │   ├── SimpleEnergyStorage.java
│   │   │               │   │   └── SimpleSidedEnergyContainer.java
│   │   │               │   ├── EnergyStorage.java
│   │   │               │   └── EnergyStorageUtil.java
│   │   │               └── impl/
│   │   │                   ├── EmptyEnergyStorage.java
│   │   │                   ├── EnergyImpl.java
│   │   │                   └── SimpleItemEnergyStorageImpl.java
│   │   └── resources/
│   │       ├── assets/
│   │       │   └── team_reborn_energy/
│   │       │       └── icon.png
│   │       └── fabric.mod.json
│   └── test/
│       ├── java/
│       │   └── team/
│       │       └── reborn/
│       │           └── energy/
│       │               └── test/
│       │                   ├── EnergyTests.java
│       │                   └── TestBatteryItem.java
│       └── resources/
│           └── fabric.mod.json
├── .gitignore
├── build.gradle
├── gradle.properties
├── gradlew
├── gradlew.bat
├── LICENSE
├── README.md
└── settings.gradle
```

## File contents

### .github/workflows/build.yml

```yaml

name: Build
on: [push, pull_request]
jobs:
  build:
    runs-on: ubuntu-24.04
    container:
      image: mcr.microsoft.com/openjdk/jdk:25-ubuntu
      options: --user root
    steps:
      - uses: actions/checkout@v4
      - uses: gradle/actions/wrapper-validation@v3
      - run: ./gradlew check build publishToMavenLocal --stacktrace --parallel --warning-mode=fail
      - uses: actions/upload-artifact@v4
        with:
          name: Artifacts
          path: build/libs/
```

### .github/workflows/release.yml

```yaml
name: Release
on: [workflow_dispatch] # Manual trigger
jobs:
  build:
    runs-on: ubuntu-24.04
    container:
      image: mcr.microsoft.com/openjdk/jdk:25-ubuntu
      options: --user root
    steps:
      - uses: actions/checkout@v4
      - uses: gradle/actions/wrapper-validation@v3
      - run: ./gradlew build publish --stacktrace
        env:
          MAVEN_URL: ${{ secrets.MAVEN_URL }}
          MAVEN_USERNAME: ${{ secrets.MAVEN_USERNAME }}
          MAVEN_PASSWORD: ${{ secrets.MAVEN_PASSWORD }}
      - uses: actions/upload-artifact@v4
        with:
          name: Artifacts
          path: build/libs/
```

### .gitignore

```text
# Ignore everything
/*

# Folders
!/gradle
!/src

# Files
!/.gitignore
!/build.gradle
!/gradlew
!/gradlew.bat
!/LICENSE
!/README.md
!/settings.gradle
!/gradle.properties
!/.github
```

### build.gradle

```groovy
import groovy.xml.XmlSlurper

plugins {
	id 'net.fabricmc.fabric-loom' version '1.14-SNAPSHOT'
	id 'java'
	id 'maven-publish'
}

base {
	archivesName = "energy"
}

def ENV = System.getenv()
group = "teamreborn"
version = project.mod_version

dependencies {
	minecraft "com.mojang:minecraft:${project.minecraft_version}"

	implementation "net.fabricmc:fabric-loader:${project.loader_version}"

	api fabricApi.module("fabric-transfer-api-v1", project.fabric_version)

	testImplementation "net.fabricmc:fabric-loader-junit:${project.loader_version}"
	testImplementation fabricApi.module("fabric-registry-sync-v0", project.fabric_version)
}

test {
	useJUnitPlatform()
}

processResources {
	inputs.property "version", project.version

	filesMatching("fabric.mod.json") {
		expand "version": inputs.properties.version
	}
}

tasks.withType(JavaCompile).configureEach {
	it.options.encoding = "UTF-8"
	it.options.release = 25
}

jar {
	from "LICENSE"
}

java {
	withSourcesJar()
	sourceCompatibility = JavaVersion.VERSION_25
	targetCompatibility = JavaVersion.VERSION_25
}

publishing {
	publications {
		create("maven", MavenPublication) {
			groupId = 'teamreborn'
			artifactId = project.base.archivesName.get()
			version = project.version

			from components.java
		}
	}
	repositories {
		if (ENV.MAVEN_URL) {
			maven {
				url = ENV.MAVEN_URL
				credentials {
					username = ENV.MAVEN_USERNAME
					password = ENV.MAVEN_PASSWORD
				}
			}
		}
	}
}

// A task to ensure that the version being released has not already been released.
tasks.register('checkVersion') {
	doFirst {
		def xml = new URL("https://maven.fabricmc.net/teamreborn/energy/maven-metadata.xml").text
		def metadata = new XmlSlurper().parseText(xml)
		def versions = metadata.versioning.versions.version*.text();
		if (versions.contains(version)) {
			throw new RuntimeException("${version} has already been released!")
		}
	}
}
publish.mustRunAfter checkVersion
```

### gradle.properties

```properties
# Done to increase the memory available to gradle.
org.gradle.jvmargs=-Xmx1G

mod_version=5.0.0

# Fabric Properties
# check these on https://fabricmc.net/versions.html
minecraft_version=26.1-snapshot-1
loader_version=0.18.4

# Dependencies
fabric_version=0.140.3+26.1
```

### gradle/wrapper/gradle-wrapper.properties

```properties
distributionBase=GRADLE_USER_HOME
distributionPath=wrapper/dists
distributionUrl=https\://services.gradle.org/distributions/gradle-9.2.1-bin.zip
networkTimeout=10000
validateDistributionUrl=true
zipStoreBase=GRADLE_USER_HOME
zipStorePath=wrapper/dists
```

### gradlew

```
#!/bin/sh

#
# Copyright © 2015-2021 the original authors.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      https://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

##############################################################################
#
#   Gradle start up script for POSIX generated by Gradle.
#
#   Important for running:
#
#   (1) You need a POSIX-compliant shell to run this script. If your /bin/sh is
#       noncompliant, but you have some other compliant shell such as ksh or
#       bash, then to run this script, type that shell name before the whole
#       command line, like:
#
#           ksh Gradle
#
#       Busybox and similar reduced shells will NOT work, because this script
#       requires all of these POSIX shell features:
#         * functions;
#         * expansions «$var», «${var}», «${var:-default}», «${var+SET}»,
#           «${var#prefix}», «${var%suffix}», and «$( cmd )»;
#         * compound commands having a testable exit status, especially «case»;
#         * various built-in commands including «command», «set», and «ulimit».
#
#   Important for patching:
#
#   (2) This script targets any POSIX shell, so it avoids extensions provided
#       by Bash, Ksh, etc; in particular arrays are avoided.
#
#       The "traditional" practice of packing multiple parameters into a
#       space-separated string is a well documented source of bugs and security
#       problems, so this is (mostly) avoided, by progressively accumulating
#       options in "$@", and eventually passing that to Java.
#
#       Where the inherited environment variables (DEFAULT_JVM_OPTS, JAVA_OPTS,
#       and GRADLE_OPTS) rely on word-splitting, this is performed explicitly;
#       see the in-line comments for details.
#
#       There are tweaks for specific operating systems such as AIX, CygWin,
#       Darwin, MinGW, and NonStop.
#
#   (3) This script is generated from the Groovy template
#       https://github.com/gradle/gradle/blob/HEAD/subprojects/plugins/src/main/resources/org/gradle/api/internal/plugins/unixStartScript.txt
#       within the Gradle project.
#
#       You can find Gradle at https://github.com/gradle/gradle/.
#
##############################################################################

# Attempt to set APP_HOME

# Resolve links: $0 may be a link
app_path=$0

# Need this for daisy-chained symlinks.
while
    APP_HOME=${app_path%"${app_path##*/}"}  # leaves a trailing /; empty if no leading path
    [ -h "$app_path" ]
do
    ls=$( ls -ld "$app_path" )
    link=${ls#*' -> '}
    case $link in             #(
      /*)   app_path=$link ;; #(
      *)    app_path=$APP_HOME$link ;;
    esac
done

# This is normally unused
# shellcheck disable=SC2034
APP_BASE_NAME=${0##*/}
# Discard cd standard output in case $CDPATH is set (https://github.com/gradle/gradle/issues/25036)
APP_HOME=$( cd "${APP_HOME:-./}" > /dev/null && pwd -P ) || exit

# Use the maximum available, or set MAX_FD != -1 to use that value.
MAX_FD=maximum

warn () {
    echo "$*"
} >&2

die () {
    echo
    echo "$*"
    echo
    exit 1
} >&2

# OS specific support (must be 'true' or 'false').
cygwin=false
msys=false
darwin=false
nonstop=false
case "$( uname )" in                #(
  CYGWIN* )         cygwin=true  ;; #(
  Darwin* )         darwin=true  ;; #(
  MSYS* | MINGW* )  msys=true    ;; #(
  NONSTOP* )        nonstop=true ;;
esac

CLASSPATH=$APP_HOME/gradle/wrapper/gradle-wrapper.jar


# Determine the Java command to use to start the JVM.
if [ -n "$JAVA_HOME" ] ; then
    if [ -x "$JAVA_HOME/jre/sh/java" ] ; then
        # IBM's JDK on AIX uses strange locations for the executables
        JAVACMD=$JAVA_HOME/jre/sh/java
    else
        JAVACMD=$JAVA_HOME/bin/java
    fi
    if [ ! -x "$JAVACMD" ] ; then
        die "ERROR: JAVA_HOME is set to an invalid directory: $JAVA_HOME

Please set the JAVA_HOME variable in your environment to match the
location of your Java installation."
    fi
else
    JAVACMD=java
    if ! command -v java >/dev/null 2>&1
    then
        die "ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.

Please set the JAVA_HOME variable in your environment to match the
location of your Java installation."
    fi
fi

# Increase the maximum file descriptors if we can.
if ! "$cygwin" && ! "$darwin" && ! "$nonstop" ; then
    case $MAX_FD in #(
      max*)
        # In POSIX sh, ulimit -H is undefined. That's why the result is checked to see if it worked.
        # shellcheck disable=SC2039,SC3045
        MAX_FD=$( ulimit -H -n ) ||
            warn "Could not query maximum file descriptor limit"
    esac
    case $MAX_FD in  #(
      '' | soft) :;; #(
      *)
        # In POSIX sh, ulimit -n is undefined. That's why the result is checked to see if it worked.
        # shellcheck disable=SC2039,SC3045
        ulimit -n "$MAX_FD" ||
            warn "Could not set maximum file descriptor limit to $MAX_FD"
    esac
fi

# Collect all arguments for the java command, stacking in reverse order:
#   * args from the command line
#   * the main class name
#   * -classpath
#   * -D...appname settings
#   * --module-path (only if needed)
#   * DEFAULT_JVM_OPTS, JAVA_OPTS, and GRADLE_OPTS environment variables.

# For Cygwin or MSYS, switch paths to Windows format before running java
if "$cygwin" || "$msys" ; then
    APP_HOME=$( cygpath --path --mixed "$APP_HOME" )
    CLASSPATH=$( cygpath --path --mixed "$CLASSPATH" )

    JAVACMD=$( cygpath --unix "$JAVACMD" )

    # Now convert the arguments - kludge to limit ourselves to /bin/sh
    for arg do
        if
            case $arg in                                #(
              -*)   false ;;                            # don't mess with options #(
              /?*)  t=${arg#/} t=/${t%%/*}              # looks like a POSIX filepath
                    [ -e "$t" ] ;;                      #(
              *)    false ;;
            esac
        then
            arg=$( cygpath --path --ignore --mixed "$arg" )
        fi
        # Roll the args list around exactly as many times as the number of
        # args, so each arg winds up back in the position where it started, but
        # possibly modified.
        #
        # NB: a `for` loop captures its iteration list before it begins, so
        # changing the positional parameters here affects neither the number of
        # iterations, nor the values presented in `arg`.
        shift                   # remove old arg
        set -- "$@" "$arg"      # push replacement arg
    done
fi


# Add default JVM options here. You can also use JAVA_OPTS and GRADLE_OPTS to pass JVM options to this script.
DEFAULT_JVM_OPTS='"-Xmx64m" "-Xms64m"'

# Collect all arguments for the java command:
#   * DEFAULT_JVM_OPTS, JAVA_OPTS, JAVA_OPTS, and optsEnvironmentVar are not allowed to contain shell fragments,
#     and any embedded shellness will be escaped.
#   * For example: A user cannot expect ${Hostname} to be expanded, as it is an environment variable and will be
#     treated as '${Hostname}' itself on the command line.

set -- \
        "-Dorg.gradle.appname=$APP_BASE_NAME" \
        -classpath "$CLASSPATH" \
        org.gradle.wrapper.GradleWrapperMain \
        "$@"

# Stop when "xargs" is not available.
if ! command -v xargs >/dev/null 2>&1
then
    die "xargs is not available"
fi

# Use "xargs" to parse quoted args.
#
# With -n1 it outputs one arg per line, with the quotes and backslashes removed.
#
# In Bash we could simply go:
#
#   readarray ARGS < <( xargs -n1 <<<"$var" ) &&
#   set -- "${ARGS[@]}" "$@"
#
# but POSIX shell has neither arrays nor command substitution, so instead we
# post-process each arg (as a line of input to sed) to backslash-escape any
# character that might be a shell metacharacter, then use eval to reverse
# that process (while maintaining the separation between arguments), and wrap
# the whole thing up as a single "set" statement.
#
# This will of course break if any of these variables contains a newline or
# an unmatched quote.
#

eval "set -- $(
        printf '%s\n' "$DEFAULT_JVM_OPTS $JAVA_OPTS $GRADLE_OPTS" |
        xargs -n1 |
        sed ' s~[^-[:alnum:]+,./:=@_]~\\&~g; ' |
        tr '\n' ' '
    )" '"$@"'

exec "$JAVACMD" "$@"
```

### gradlew.bat

```batch
@rem
@rem Copyright 2015 the original author or authors.
@rem
@rem Licensed under the Apache License, Version 2.0 (the "License");
@rem you may not use this file except in compliance with the License.
@rem You may obtain a copy of the License at
@rem
@rem      https://www.apache.org/licenses/LICENSE-2.0
@rem
@rem Unless required by applicable law or agreed to in writing, software
@rem distributed under the License is distributed on an "AS IS" BASIS,
@rem WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@rem See the License for the specific language governing permissions and
@rem limitations under the License.
@rem

@if "%DEBUG%"=="" @echo off
@rem ##########################################################################
@rem
@rem  Gradle startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%"=="" set DIRNAME=.
@rem This is normally unused
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%

@rem Resolve any "." and ".." in APP_HOME to make it shorter.
for %%i in ("%APP_HOME%") do set APP_HOME=%%~fi

@rem Add default JVM options here. You can also use JAVA_OPTS and GRADLE_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS="-Xmx64m" "-Xms64m"

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if %ERRORLEVEL% equ 0 goto execute

echo. 1>&2
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH. 1>&2
echo. 1>&2
echo Please set the JAVA_HOME variable in your environment to match the 1>&2
echo location of your Java installation. 1>&2

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto execute

echo. 1>&2
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME% 1>&2
echo. 1>&2
echo Please set the JAVA_HOME variable in your environment to match the 1>&2
echo location of your Java installation. 1>&2

goto fail

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\gradle\wrapper\gradle-wrapper.jar


@rem Execute Gradle
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %GRADLE_OPTS% "-Dorg.gradle.appname=%APP_BASE_NAME%" -classpath "%CLASSPATH%" org.gradle.wrapper.GradleWrapperMain %*

:end
@rem End local scope for the variables with windows NT shell
if %ERRORLEVEL% equ 0 goto mainEnd

:fail
rem Set variable GRADLE_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
set EXIT_CODE=%ERRORLEVEL%
if %EXIT_CODE% equ 0 set EXIT_CODE=1
if not ""=="%GRADLE_EXIT_CONSOLE%" exit %EXIT_CODE%
exit /b %EXIT_CODE%

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
```

### LICENSE

```text
MIT License

Copyright (c) 2022

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

### README.md

````markdown
# Energy

An Energy API for Fabric mods, originally written for TechReborn.

Uses Fabric's API Lookup and Transaction systems.

# Conventions
To ensure good interop between all the mods using this API, here are a few conventions that should be followed.

* Reference energy values
  * 1 coal = 4000
  * 1 plank = 750
* The system is push-based.
  * This means that power sources are responsible for pushing power to nearby machines.
  * Machines and wires should NOT pull power from other sources.

# Including the API in your project

Find the latest versions [here](https://maven.fabricmc.net/teamreborn/energy/)

- Version 3.x should be used for Minecraft 1.19.4 -> 1.20.4
- Version 4.0.x should be used for Minecraft 1.20.5 or later
- Version 4.1.x should be used for Minecraft 1.21 or later
- Version 4.2.x should be used for Minecraft 1.21.5 or later
- Version 5.x should be used for Minecraft 26.1 or later

Add the following into your dependencies block in build.gradle

```groovy
include modApi('teamreborn:energy:<latest_version>')
```

# Documentation
The API revolves around [`EnergyStorage`](src/main/java/team/reborn/energy/api/EnergyStorage.java).
Make sure to check out the documentation.

A few examples follow to get you started.

## Implementing energy-containing blocks
The easiest way, with fixed capacity and insertion/extraction limits:
```groovy
public class MyBlockEntity extends BlockEntity {
    // Store a SimpleEnergyStorage in the block entity class.
    public final SimpleEnergyStorage energyStorage = new SimpleEnergyStorage(CAPACITY, MAX_INSERT, MAX_EXTRACT) {
        @Override
        protected void onFinalCommit() {
            setChanged();
        }
    };
    
    // Use the energy internally, for example in tick()
    public void tick() {
        if (!level.isClientSide() && energyStorage.amount >= 10) {
            energyStorage.amount -= 10;
            // do something with the 10 energy we just used.
            setChanged();
        }
    }
    
    // Don't forget to save/read the energy in the block entity NBT.
}

// Don't forget to register the energy storage. Make sure to call this after you create the block entity type.
BlockEntityType<MyBlockEntity> MY_BLOCK_ENTITY;
EnergyStorage.SIDED.registerForBlockEntity((myBlockEntity, direction) -> myBlockEntity.energyStorage, MY_BLOCK_ENTITY);
```

`SimpleSidedEnergyContainer` may be used if the I/O limits are side-dependent.

If you know what you are doing, you can also implement `EnergyStorage` directly, but in most cases that's not necessary.
Refer to the documentation of this API, of the Transaction API and API Lookup for details.

## Usage example (blocks)
Get an energy storage:
```groovy
@Nullable
EnergyStorage maybeStorage = EnergyStorage.SIDED.find(world, pos, direction);
```
Get an adjacent energy storage:
```groovy
// Known things
Level level; BlockPos currentPos; Direction adjacentDirection;
// Get adjacent energy storage, or null if there is none
@Nullable
EnergyStorage maybeStorage = EnergyStorage.SIDED.find(level, currentPos.relative(adjacentDirection), adjacentDirection.getOpposite());
```
Move energy between two storages:
```groovy
EnergyStorage source, target;

long amountMoved = EnergyStorageUtil.move(
        source, // from source
        target, // into target
        Long.MAX_VALUE, // no limit on the amount
        null // create a new transaction for this operation 
);
```
Try to extract an exact amount of energy:
```groovy
EnergyStorage source;
long amountToUse;

// Open a transaction: this allows cancelling the operation if it doesn't go as expected.
try (Transaction transaction = Transaction.openOuter()) {
    // Try to extract, will return how much was actually extracted
    long amountExtracted = source.extract(amountToUse, transaction);
    if (amountExtracted == amountToUse) {
        // "Commit" the transaction to make sure the change is applied.
        transaction.commit();
    } else {
        // Doing nothing "aborts" the transaction, cancelling the change.
    }
}
```

## Creating chargeable items
The easiest way to create an item that can be charged by supported mods is by implementing `SimpleEnergyItem` on your item class.
The functions should be self-explanatory.

For more complex items, `EnergyStorage.ITEM` may be used directly.
Make sure you read the documentation of `ContainerItemContext` if you go down that path.

## Charging items
Check out how you can create a `ContainerItemContext`,
and use it to query an `EnergyStorage` implementation with `EnergyStorage.SIDED`.
````

### settings.gradle

```groovy
pluginManagement {
    repositories {
        maven {
            name = 'Fabric'
            url = 'https://maven.fabricmc.net/'
        }
        gradlePluginPortal()
    }
}
```

### src/main/java/team/reborn/energy/api/base/DelegatingEnergyStorage.java

```java
package team.reborn.energy.api.base;

import net.fabricmc.fabric.api.transfer.v1.storage.StoragePreconditions;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import org.jetbrains.annotations.Nullable;
import team.reborn.energy.api.EnergyStorage;

import java.util.Objects;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

/**
 * An energy storage that delegates to another energy storage,
 * with an optional boolean supplier to check that the storage is still valid.
 * This can be used for easier item energy storage implementation, or overridden for custom delegation logic.
 */
public class DelegatingEnergyStorage implements EnergyStorage {
	protected final Supplier<EnergyStorage> backingStorage;
	protected final BooleanSupplier validPredicate;

	/**
	 * Create a new instance.
	 * @param backingStorage Storage to delegate to.
	 * @param validPredicate A function that can return false to prevent any operation, or true to call the delegate as usual.
	 *                       {@code null} can be passed if no filtering is necessary.
	 */
	public DelegatingEnergyStorage(EnergyStorage backingStorage, @Nullable BooleanSupplier validPredicate) {
		this(() -> backingStorage, validPredicate);
		Objects.requireNonNull(backingStorage);
	}

	/**
	 * More general constructor that allows the backing storage to change over time.
	 */
	public DelegatingEnergyStorage(Supplier<EnergyStorage> backingStorage, @Nullable BooleanSupplier validPredicate) {
		this.backingStorage = Objects.requireNonNull(backingStorage);
		this.validPredicate = validPredicate == null ? () -> true : validPredicate;
	}

	@Override
	public boolean supportsInsertion() {
		return validPredicate.getAsBoolean() && backingStorage.get().supportsInsertion();
	}

	@Override
	public long insert(long maxAmount, TransactionContext transaction) {
		StoragePreconditions.notNegative(maxAmount);

		if (validPredicate.getAsBoolean()) {
			return backingStorage.get().insert(maxAmount, transaction);
		} else {
			return 0;
		}
	}

	@Override
	public boolean supportsExtraction() {
		return validPredicate.getAsBoolean() && backingStorage.get().supportsExtraction();
	}

	@Override
	public long extract(long maxAmount, TransactionContext transaction) {
		StoragePreconditions.notNegative(maxAmount);

		if (validPredicate.getAsBoolean()) {
			return backingStorage.get().extract(maxAmount, transaction);
		} else {
			return 0;
		}
	}

	@Override
	public long getAmount() {
		if (validPredicate.getAsBoolean()) {
			return backingStorage.get().getAmount();
		} else {
			return 0;
		}
	}

	@Override
	public long getCapacity() {
		if (validPredicate.getAsBoolean()) {
			return backingStorage.get().getCapacity();
		} else {
			return 0;
		}
	}
}
```

### src/main/java/team/reborn/energy/api/base/InfiniteEnergyStorage.java

```java
package team.reborn.energy.api.base;

import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import team.reborn.energy.api.EnergyStorage;

/**
 * An energy storage that can't accept energy, but will allow extracting any amount of energy.
 * Creative batteries are a possible use case.
 * {@link #INSTANCE} can be used instead of creating a new object every time.
 */
public class InfiniteEnergyStorage implements EnergyStorage {
	public static final InfiniteEnergyStorage INSTANCE = new InfiniteEnergyStorage();

	@Override
	public boolean supportsInsertion() {
		return false;
	}

	@Override
	public long insert(long maxAmount, TransactionContext transaction) {
		return 0;
	}

	@Override
	public long extract(long maxAmount, TransactionContext transaction) {
		return maxAmount;
	}

	@Override
	public long getAmount() {
		return Long.MAX_VALUE;
	}

	@Override
	public long getCapacity() {
		return Long.MAX_VALUE;
	}
}
```

### src/main/java/team/reborn/energy/api/base/LimitingEnergyStorage.java

```java
package team.reborn.energy.api.base;

import net.fabricmc.fabric.api.transfer.v1.storage.StoragePreconditions;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import team.reborn.energy.api.EnergyStorage;

import java.util.Objects;

/**
 * An energy storage that will apply additional per-insert and per-extract limits to another storage.
 */
public class LimitingEnergyStorage implements EnergyStorage {
	protected final EnergyStorage backingStorage;
	protected final long maxInsert, maxExtract;

	/**
	 * Create a new limiting storage.
	 * @param backingStorage Storage to delegate to.
	 * @param maxInsert The maximum amount of energy that can be inserted in one operation.
	 * @param maxExtract The maximum amount of energy that can be extracted in one operation.
	 */
	public LimitingEnergyStorage(EnergyStorage backingStorage, long maxInsert, long maxExtract) {
		Objects.requireNonNull(backingStorage);
		StoragePreconditions.notNegative(maxInsert);
		StoragePreconditions.notNegative(maxExtract);

		this.backingStorage = backingStorage;
		this.maxInsert = maxInsert;
		this.maxExtract = maxExtract;
	}

	@Override
	public boolean supportsInsertion() {
		return maxInsert > 0 && backingStorage.supportsInsertion();
	}

	@Override
	public long insert(long maxAmount, TransactionContext transaction) {
		return backingStorage.insert(Math.min(maxAmount, maxInsert), transaction);
	}

	@Override
	public boolean supportsExtraction() {
		return maxExtract > 0 && backingStorage.supportsExtraction();
	}

	@Override
	public long extract(long maxAmount, TransactionContext transaction) {
		return backingStorage.extract(Math.min(maxAmount, maxExtract), transaction);
	}

	@Override
	public long getAmount() {
		return backingStorage.getAmount();
	}

	@Override
	public long getCapacity() {
		return backingStorage.getCapacity();
	}
}
```

### src/main/java/team/reborn/energy/api/base/SimpleEnergyItem.java

```java
package team.reborn.energy.api.base;

import net.fabricmc.fabric.api.transfer.v1.context.ContainerItemContext;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import team.reborn.energy.api.EnergyStorage;
import team.reborn.energy.impl.SimpleItemEnergyStorageImpl;

import java.util.Optional;

/**
 * Simple battery-like energy containing item. If this is implemented on an item:
 * <ul>
 *     <li>The energy will directly be stored in the components.</li>
 *     <li>Helper functions in this class to work with the stored energy can be used.</li>
 *     <li>An EnergyStorage will automatically be provided for queries through {@link EnergyStorage#ITEM}.</li>
 * </ul>
 */
// TODO: Consider adding a tooltip and a recipe input -> output energy transfer handler like RC has.
public interface SimpleEnergyItem {

	/**
	 * Return a base energy storage implementation for items, with fixed capacity, and per-operation insertion and extraction limits.
	 * This is used internally for items that implement SimpleEnergyItem, but it may also be used outside of that.
	 * The energy is stored in the {@link EnergyStorage#ENERGY_COMPONENT} of the stacks.
	 *
	 * <p>Stackable energy containers are supported just fine, and they will distribute energy evenly.
	 * For example, insertion of 3 units of energy into a stack of 2 items using this class will either insert 0 or 2 depending on the remaining capacity.
	 */
	static EnergyStorage createStorage(ContainerItemContext ctx, long capacity, long maxInsert, long maxExtract) {
		return SimpleItemEnergyStorageImpl.createSimpleStorage(ctx, capacity, maxInsert, maxExtract);
	}

	/**
	 * @param stack Current stack.
	 * @return The max energy that can be stored in this item stack (ignoring current stack size).
	 */
	long getEnergyCapacity(ItemStack stack);

	/**
	 * @param stack Current stack.
	 * @return The max amount of energy that can be inserted in this item stack (ignoring current stack size) in a single operation.
	 */
	long getEnergyMaxInput(ItemStack stack);

	/**
	 * @param stack Current stack.
	 * @return The max amount of energy that can be extracted from this item stack (ignoring current stack size) in a single operation.
	 */
	long getEnergyMaxOutput(ItemStack stack);

	/**
	 * @return The energy stored in the stack. Count is ignored.
	 */
	default long getStoredEnergy(ItemStack stack) {
		return getStoredEnergyUnchecked(stack);
	}

	/**
	 * Directly set the energy stored in the stack. Count is ignored.
	 * It's up to callers to ensure that the new amount is >= 0 and <= capacity.
	 */
	default void setStoredEnergy(ItemStack stack, long newAmount) {
		setStoredEnergyUnchecked(stack, newAmount);
	}

	/**
	 * Try to use exactly {@code amount} energy if there is enough available and return true if successful,
	 * otherwise do nothing and return false.
	 * @throws IllegalArgumentException If the count of the stack is not exactly 1!
	 */
	default boolean tryUseEnergy(ItemStack stack, long amount) {
		if (stack.getCount() != 1) {
			throw new IllegalArgumentException("Invalid count: " + stack.getCount());
		}

		long newAmount = getStoredEnergy(stack) - amount;

		if (newAmount < 0) {
			return false;
		} else {
			setStoredEnergy(stack, newAmount);
			return true;
		}
	}

	/**
	 * @return The currently stored energy, ignoring the count and without checking the current item.
	 */
	static long getStoredEnergyUnchecked(ItemStack stack) {
		return stack.getOrDefault(EnergyStorage.ENERGY_COMPONENT, 0L);
	}

	static long getStoredEnergyUnchecked(ItemVariant variant) {
		return getStoredEnergyUnchecked(variant.getComponents());
	}

	static long getStoredEnergyUnchecked(DataComponentMap components) {
		return components.getOrDefault(EnergyStorage.ENERGY_COMPONENT, 0L);
	}

	/**
	 * Set the energy, ignoring the count and without checking the current item.
	 */
	static void setStoredEnergyUnchecked(ItemStack stack, long newAmount) {
		if (newAmount <= 0) {
			// Make sure newly crafted energy containers stack with emptied ones.
			stack.remove(EnergyStorage.ENERGY_COMPONENT);
		} else {
			stack.set(EnergyStorage.ENERGY_COMPONENT, newAmount);
		}
	}
}
```

### src/main/java/team/reborn/energy/api/base/SimpleEnergyStorage.java

```java
package team.reborn.energy.api.base;

import net.fabricmc.fabric.api.transfer.v1.storage.StoragePreconditions;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.fabricmc.fabric.api.transfer.v1.transaction.base.SnapshotParticipant;
import team.reborn.energy.api.EnergyStorage;

/**
 * A base energy storage implementation with fixed capacity, and per-operation insertion and extraction limits.
 * Make sure to override {@link #onFinalCommit} to call {@code markDirty} and similar functions.
 */
@SuppressWarnings({"unused"})
public class SimpleEnergyStorage extends SnapshotParticipant<Long> implements EnergyStorage {
	public long amount = 0;
	public final long capacity;
	public final long maxInsert, maxExtract;

	public SimpleEnergyStorage(long capacity, long maxInsert, long maxExtract) {
		StoragePreconditions.notNegative(capacity);
		StoragePreconditions.notNegative(maxInsert);
		StoragePreconditions.notNegative(maxExtract);

		this.capacity = capacity;
		this.maxInsert = maxInsert;
		this.maxExtract = maxExtract;
	}

	@Override
	protected Long createSnapshot() {
		return amount;
	}

	@Override
	protected void readSnapshot(Long snapshot) {
		amount = snapshot;
	}

	@Override
	public boolean supportsInsertion() {
		return maxInsert > 0;
	}

	@Override
	public long insert(long maxAmount, TransactionContext transaction) {
		StoragePreconditions.notNegative(maxAmount);

		long inserted = Math.min(maxInsert, Math.min(maxAmount, capacity - amount));

		if (inserted > 0) {
			updateSnapshots(transaction);
			amount += inserted;
			return inserted;
		}

		return 0;
	}

	@Override
	public boolean supportsExtraction() {
		return maxExtract > 0;
	}

	@Override
	public long extract(long maxAmount, TransactionContext transaction) {
		StoragePreconditions.notNegative(maxAmount);

		long extracted = Math.min(maxExtract, Math.min(maxAmount, amount));

		if (extracted > 0) {
			updateSnapshots(transaction);
			amount -= extracted;
			return extracted;
		}

		return 0;
	}

	@Override
	public long getAmount() {
		return amount;
	}

	@Override
	public long getCapacity() {
		return capacity;
	}
}
```

### src/main/java/team/reborn/energy/api/base/SimpleSidedEnergyContainer.java

```java
package team.reborn.energy.api.base;

import net.fabricmc.fabric.api.transfer.v1.storage.StoragePreconditions;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.fabricmc.fabric.api.transfer.v1.transaction.base.SnapshotParticipant;
import net.minecraft.core.Direction;
import org.jetbrains.annotations.Nullable;
import team.reborn.energy.api.EnergyStorage;

/**
 * A base energy storage implementation with a dynamic capacity, and per-side per-operation insertion and extraction limits.
 * {@link #getSideStorage} can be used to get an {@code EnergyStorage} implementation for a given side.
 * Make sure to override {@link #onFinalCommit} to call {@code markDirty} and similar functions.
 */
@SuppressWarnings({"unused"})
public abstract class SimpleSidedEnergyContainer extends SnapshotParticipant<Long> {
	public long amount = 0;
	private final SideStorage[] sideStorages = new SideStorage[7];

	public SimpleSidedEnergyContainer() {
		for (int i = 0; i < 7; ++i) {
			sideStorages[i] = new SideStorage(i == 6 ? null : Direction.from3DDataValue(i));
		}
	}

	/**
	 * @return The current capacity of this storage.
	 */
	public abstract long getCapacity();

	/**
	 * @return The maximum amount of energy that can be inserted in a single operation from the passed side.
	 */
	public abstract long getMaxInsert(@Nullable Direction side);

	/**
	 * @return The maximum amount of energy that can be extracted in a single operation from the passed side.
	 */
	public abstract long getMaxExtract(@Nullable Direction side);

	/**
	 * @return An {@link EnergyStorage} implementation for the passed side.
	 */
	public EnergyStorage getSideStorage(@Nullable Direction side) {
		return sideStorages[side == null ? 6 : side.get3DDataValue()];
	}

	@Override
	protected Long createSnapshot() {
		return amount;
	}

	@Override
	protected void readSnapshot(Long snapshot) {
		amount = snapshot;
	}

	private class SideStorage implements EnergyStorage {
		private final Direction side;

		private SideStorage(Direction side) {
			this.side = side;
		}

		@Override
		public boolean supportsInsertion() {
			return getMaxInsert(side) > 0;
		}

		@Override
		public long insert(long maxAmount, TransactionContext transaction) {
			StoragePreconditions.notNegative(maxAmount);

			long inserted = Math.min(getMaxInsert(side), Math.min(maxAmount, getCapacity() - amount));

			if (inserted > 0) {
				updateSnapshots(transaction);
				amount += inserted;
				return inserted;
			}

			return 0;
		}

		@Override
		public boolean supportsExtraction() {
			return getMaxExtract(side) > 0;
		}

		@Override
		public long extract(long maxAmount, TransactionContext transaction) {
			StoragePreconditions.notNegative(maxAmount);

			long extracted = Math.min(getMaxExtract(side), Math.min(maxAmount, amount));

			if (extracted > 0) {
				updateSnapshots(transaction);
				amount -= extracted;
				return extracted;
			}

			return 0;
		}

		@Override
		public long getAmount() {
			return amount;
		}

		@Override
		public long getCapacity() {
			return SimpleSidedEnergyContainer.this.getCapacity();
		}
	}
}
```

### src/main/java/team/reborn/energy/api/EnergyStorage.java

```java
package team.reborn.energy.api;

import net.fabricmc.fabric.api.lookup.v1.block.BlockApiLookup;
import net.fabricmc.fabric.api.lookup.v1.item.ItemApiLookup;
import net.fabricmc.fabric.api.transfer.v1.context.ContainerItemContext;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.Nullable;
import team.reborn.energy.api.base.DelegatingEnergyStorage;
import team.reborn.energy.api.base.SimpleEnergyItem;
import team.reborn.energy.api.base.SimpleEnergyStorage;
import team.reborn.energy.api.base.SimpleSidedEnergyContainer;
import team.reborn.energy.impl.EmptyEnergyStorage;
import team.reborn.energy.impl.EnergyImpl;
import team.reborn.energy.impl.SimpleItemEnergyStorageImpl;

import java.util.Objects;

/**
 * An object that can store energy.
 *
 * <p><ul>
 *     <li>{@link #supportsInsertion} and {@link #supportsExtraction} can be used to tell if insertion and extraction
 *     functionality are possibly supported by this storage.</li>
 *     <li>{@link #insert} and {@link #extract} can be used to insert or extract resources from this storage.</li>
 *     <li>{@link #getAmount} and {@link #getCapacity} can be used to query the current amount and capacity of this storage.
 *     There is no guarantee that the current amount of energy can be extracted,
 *     nor that something can be inserted if capacity > amount.
 *     If you want to know, you can simulate the operation with {@link #insert} and {@link #extract}.
 *     </li>
 * </ul>
 *
 * @see Transaction
 */
@SuppressWarnings({"unused"})
public interface EnergyStorage {
	/**
	 * Sided block access to energy storages.
	 * The {@code Direction} parameter may be null, meaning that the full storage (ignoring side restrictions) should be queried.
	 * Refer to {@link BlockApiLookup} for documentation on how to use this field.
	 *
	 * <p>The system is push based. That means that power sources are responsible for pushing power to nearby machines.
	 * Machines and wires should NOT pull power from other sources.
	 *
	 * <p>{@link SimpleEnergyStorage} and {@link SimpleSidedEnergyContainer} are provided as base implementations.
	 *
	 * <p>When the operations supported by an energy storage change,
	 * that is if the return value of {@link EnergyStorage#supportsInsertion} or {@link EnergyStorage#supportsExtraction} changes,
	 * the storage should notify its neighbors with a block update so that they can refresh their connections if necessary.
	 *
	 * <p>This may be queried safely both on the logical server and on the logical client threads.
	 * On the server thread (i.e. with a server world), all transfer functionality is always supported.
	 * On the client thread (i.e. with a client world), contents of queried EnergyStorages are unreliable and should not be modified.
	 */
	BlockApiLookup<EnergyStorage, @Nullable Direction> SIDED =
			BlockApiLookup.get(Identifier.fromNamespaceAndPath("teamreborn", "sided_energy"), EnergyStorage.class, Direction.class);

	/**
	 * Item access to energy storages.
	 * Querying should always happen through {@link ContainerItemContext#find}.
	 *
	 * <p>{@link SimpleItemEnergyStorageImpl} is provided as an implementation example.
	 * Instances of it can be optained through {@link SimpleEnergyItem#createStorage}.
	 * Custom implementations should treat the context as a wrapper around a single slot,
	 * and always check the current item variant and amount before any operation, like {@code SimpleItemEnergyStorageImpl} does it.
	 * The check can be handled by {@link DelegatingEnergyStorage}.
	 *
	 * <p>This may be queried both client-side and server-side.
	 * Returned APIs should behave the same regardless of the logical side.
	 */
	ItemApiLookup<EnergyStorage, ContainerItemContext> ITEM =
			ItemApiLookup.get(Identifier.fromNamespaceAndPath("teamreborn", "energy"), EnergyStorage.class, ContainerItemContext.class);

	/**
	 * Always empty energy storage.
	 */
	EnergyStorage EMPTY = Objects.requireNonNull(EmptyEnergyStorage.EMPTY);

	/**
	 * Stock data component type for energy.
	 *
	 * <p><b>This component should only be used on item stacks from your mod.</b>
	 * Otherwise, do not query it or assume it exists.
	 * Inter-mod energy interactions should happen using {@link #ITEM}.</b>
	 */
	DataComponentType<Long> ENERGY_COMPONENT = Objects.requireNonNull(EnergyImpl.ENERGY_COMPONENT);

	/**
	 * Return false if calling {@link #insert} will absolutely always return 0, or true otherwise or in doubt.
	 *
	 * <p>Note: This function is meant to be used by cables or other devices that can transfer energy to know if
	 * they should interact with this storage at all.
	 */
	default boolean supportsInsertion() {
		return true;
	}

	/**
	 * Try to insert up to some amount of energy into this storage.
	 *
	 * @param maxAmount The maximum amount of energy to insert. May not be negative.
	 * @param transaction The transaction this operation is part of.
	 * @return A nonnegative integer not greater than maxAmount: the amount that was inserted.
	 */
	long insert(long maxAmount, TransactionContext transaction);

	/**
	 * Return false if calling {@link #extract} will absolutely always return 0, or true otherwise or in doubt.
	 *
	 * <p>Note: This function is meant to be used by cables or other devices that can transfer energy to know if
	 * they should interact with this storage at all.
	 */
	default boolean supportsExtraction() {
		return true;
	}

	/**
	 * Try to extract up to some amount of energy from this storage.
	 *
	 * @param maxAmount The maximum amount of energy to extract. May not be negative.
	 * @param transaction The transaction this operation is part of.
	 * @return A nonnegative integer not greater than maxAmount: the amount that was extracted.
	 */
	long extract(long maxAmount, TransactionContext transaction);

	/**
	 * Return the current amount of energy that is stored.
	 */
	long getAmount();

	/**
	 * Return the maximum amount of energy that could be stored.
	 */
	long getCapacity();
}
```

### src/main/java/team/reborn/energy/api/EnergyStorageUtil.java

```java
package team.reborn.energy.api;

import net.fabricmc.fabric.api.transfer.v1.context.ContainerItemContext;
import net.fabricmc.fabric.api.transfer.v1.storage.StoragePreconditions;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

/**
 * Helper functions to work with {@link EnergyStorage}s.
 */
@SuppressWarnings({"unused"})
public class EnergyStorageUtil {
	/**
	 * Move energy between two energy storages, and return the amount that was successfully moved.
	 *
	 * @param from The source storage. May be null.
	 * @param to The target storage. May be null.
	 * @param maxAmount The maximum amount that may be moved.
	 * @param transaction The transaction this transfer is part of,
	 *                    or {@code null} if a transaction should be opened just for this transfer.
	 * @return The amount of energy that was successfully moved.
	 */
	public static long move(@Nullable EnergyStorage from, @Nullable EnergyStorage to, long maxAmount, @Nullable TransactionContext transaction) {
		if (from == null || to == null) return 0;

		StoragePreconditions.notNegative(maxAmount);

		// Simulate extraction first.
		long maxExtracted;

		try (Transaction extractionTestTransaction = Transaction.openNested(transaction)) {
			maxExtracted = from.extract(maxAmount, extractionTestTransaction);
		}

		try (Transaction moveTransaction = Transaction.openNested(transaction)) {
			// Then insert what can be extracted.
			long accepted = to.insert(maxExtracted, moveTransaction);

			// Extract for real.
			if (from.extract(accepted, moveTransaction) == accepted) {
				// Commit if the amounts match.
				moveTransaction.commit();
				return accepted;
			}
		}

		return 0;
	}

	/**
	 * Return true if the passed stack offers an energy storage through {@link EnergyStorage#ITEM}.
	 * This can typically be used for inventories or slots that want to accept energy storages only.
	 */
	public static boolean isEnergyStorage(ItemStack stack) {
		return ContainerItemContext.withConstant(stack).find(EnergyStorage.ITEM) != null;
	}

	private EnergyStorageUtil() {
	}
}
```

### src/main/java/team/reborn/energy/impl/EmptyEnergyStorage.java

```java
package team.reborn.energy.impl;

import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import org.jetbrains.annotations.ApiStatus;
import team.reborn.energy.api.EnergyStorage;

@ApiStatus.Internal
public final class EmptyEnergyStorage implements EnergyStorage {
	public static final EnergyStorage EMPTY = new EmptyEnergyStorage();

	private EmptyEnergyStorage() {
	}

	@Override
	public boolean supportsInsertion() {
		return false;
	}

	@Override
	public long insert(long maxAmount, TransactionContext transaction) {
		return 0;
	}

	@Override
	public boolean supportsExtraction() {
		return false;
	}

	@Override
	public long extract(long maxAmount, TransactionContext transaction) {
		return 0;
	}

	@Override
	public long getAmount() {
		return 0;
	}

	@Override
	public long getCapacity() {
		return 0;
	}
}
```

### src/main/java/team/reborn/energy/impl/EnergyImpl.java

```java
package team.reborn.energy.impl;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.ApiStatus;
import team.reborn.energy.api.EnergyStorage;
import team.reborn.energy.api.base.SimpleEnergyItem;

@ApiStatus.Internal
public class EnergyImpl {
	public static final DataComponentType<Long> ENERGY_COMPONENT = DataComponentType.<Long>builder()
		.persistent(nonNegativeLong())
		.networkSynchronized(ByteBufCodecs.VAR_LONG)
		.build();

	public static void init() {
		Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, Identifier.fromNamespaceAndPath("team_reborn_energy", "energy"), ENERGY_COMPONENT);
		EnergyStorage.ITEM.registerFallback((stack, ctx) -> {
			if (stack.getItem() instanceof SimpleEnergyItem energyItem) {
				return SimpleEnergyItem.createStorage(ctx, energyItem.getEnergyCapacity(stack), energyItem.getEnergyMaxInput(stack), energyItem.getEnergyMaxOutput(stack));
			} else {
				return null;
			}
		});
	}

	private static Codec<Long> nonNegativeLong() {
		return Codec.LONG.validate((Long value) -> {
			if (value >= 0) {
				return DataResult.success(value);
			}

			return DataResult.error(() -> "Energy value must be non-negative: " + value);
		});
	}
}
```

### src/main/java/team/reborn/energy/impl/SimpleItemEnergyStorageImpl.java

```java
package team.reborn.energy.impl;

import net.fabricmc.fabric.api.transfer.v1.context.ContainerItemContext;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.StoragePreconditions;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.ApiStatus;
import team.reborn.energy.api.EnergyStorage;
import team.reborn.energy.api.base.DelegatingEnergyStorage;
import team.reborn.energy.api.base.SimpleEnergyItem;

/**
 * Note: instances of this class do not perform any context validation,
 * that is handled by the DelegatingEnergyStorage they are wrapped behind.
 */
@ApiStatus.Internal
public class SimpleItemEnergyStorageImpl implements EnergyStorage {
	public static EnergyStorage createSimpleStorage(ContainerItemContext ctx, long capacity, long maxInsert, long maxExtract) {
		StoragePreconditions.notNegative(capacity);
		StoragePreconditions.notNegative(maxInsert);
		StoragePreconditions.notNegative(maxExtract);

		Item startingItem = ctx.getItemVariant().getItem();

		return new DelegatingEnergyStorage(
				new SimpleItemEnergyStorageImpl(ctx, capacity, maxInsert, maxExtract),
				() -> ctx.getItemVariant().isOf(startingItem) && ctx.getAmount() > 0
		);
	}

	private final ContainerItemContext ctx;
	private final long capacity;
	private final long maxInsert, maxExtract;

	private SimpleItemEnergyStorageImpl(ContainerItemContext ctx, long capacity, long maxInsert, long maxExtract) {
		this.ctx = ctx;
		this.capacity = capacity;
		this.maxInsert = maxInsert;
		this.maxExtract = maxExtract;
	}

	/**
	 * Try to set the energy of the stack to {@code energyAmountPerCount}, return true if success.
	 */
	private boolean trySetEnergy(long energyAmountPerCount, long count, TransactionContext transaction) {
		ItemStack newStack = ctx.getItemVariant().toStack();
		SimpleEnergyItem.setStoredEnergyUnchecked(newStack, energyAmountPerCount);
		ItemVariant newVariant = ItemVariant.of(newStack);

		// Try to convert exactly `count` items.
		try (Transaction nested = transaction.openNested()) {
			if (ctx.extract(ctx.getItemVariant(), count, nested) == count && ctx.insert(newVariant, count, nested) == count) {
				nested.commit();
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean supportsInsertion() {
		return maxInsert > 0;
	}

	@Override
	public long insert(long maxAmount, TransactionContext transaction) {
		long count = ctx.getAmount();

		long maxAmountPerCount = maxAmount / count;
		long currentAmountPerCount = getAmount() / count;
		long insertedPerCount = Math.min(maxInsert, Math.min(maxAmountPerCount, capacity - currentAmountPerCount));

		if (insertedPerCount > 0) {
			if (trySetEnergy(currentAmountPerCount + insertedPerCount, count, transaction)) {
				return insertedPerCount * count;
			}
		}

		return 0;
	}

	@Override
	public boolean supportsExtraction() {
		return maxExtract > 0;
	}

	@Override
	public long extract(long maxAmount, TransactionContext transaction) {
		long count = ctx.getAmount();

		long maxAmountPerCount = maxAmount / count;
		long currentAmountPerCount = getAmount() / count;
		long extractedPerCount = Math.min(maxExtract, Math.min(maxAmountPerCount, currentAmountPerCount));

		if (extractedPerCount > 0) {
			if (trySetEnergy(currentAmountPerCount - extractedPerCount, count, transaction)) {
				return extractedPerCount * count;
			}
		}

		return 0;
	}

	@Override
	public long getAmount() {
		return ctx.getAmount() * SimpleEnergyItem.getStoredEnergyUnchecked(ctx.getItemVariant().getComponents());
	}

	@Override
	public long getCapacity() {
		return ctx.getAmount() * capacity;
	}
}
```

### src/main/resources/fabric.mod.json

```json
{
  "schemaVersion": 1,
  "id": "team_reborn_energy",
  "version": "${version}",
  "name": "Energy",
  "description": "The Energy API created and used by Team Reborn",
  "authors": [
    "Team Reborn",
    "modmuss50"
  ],
  "contact": {
    "homepage": "https://github.com/TechReborn/Energy",
    "sources": "https://github.com/TechReborn/Energy",
    "issues": "https://github.com/TechReborn/Energy/issues"
  },
  "license": "MIT",
  "environment": "*",
  "icon": "assets/team_reborn_energy/icon.png",
  "entrypoints": {
    "main": [
      "team.reborn.energy.impl.EnergyImpl::init"
    ]
  },
  "depends": {
    "java": ">=25",
    "minecraft": ">=26.1-",
    "fabric-transfer-api-v1": ">=5.1.0"
  }
}
```

### src/test/java/team/reborn/energy/test/EnergyTests.java

```java
package team.reborn.energy.test;

import net.fabricmc.fabric.api.transfer.v1.context.ContainerItemContext;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.minecraft.SharedConstants;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.server.Bootstrap;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import team.reborn.energy.api.EnergyStorage;
import team.reborn.energy.api.EnergyStorageUtil;
import team.reborn.energy.api.base.SimpleEnergyItem;
import team.reborn.energy.api.base.SimpleEnergyStorage;
import team.reborn.energy.impl.EnergyImpl;

import static org.junit.jupiter.api.Assertions.*;
import static team.reborn.energy.api.base.SimpleEnergyItem.getStoredEnergyUnchecked;

public class EnergyTests {
	private static TestBatteryItem item;

	@BeforeAll
	public static void setup() {
		SharedConstants.tryDetectVersion();
		Bootstrap.bootStrap();
		EnergyImpl.init();

		item = new TestBatteryItem(60, 50, 50);
		Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath("energy_test", "battery"), item);
	}

	@Test
	public void testEmptyStorage() {
		try (Transaction transaction = Transaction.openOuter()) {
			ensureEmpty(EnergyStorage.EMPTY, transaction);
		}
	}

	@Test
	public void testSimpleEnergyStorage() {
		SimpleEnergyStorage simpleStorage = new SimpleEnergyStorage(100, 5, 10);
		assertEquals(0, simpleStorage.getAmount());

		try (Transaction transaction = Transaction.openOuter()) {
			assertEquals(5, simpleStorage.insert(100, transaction));
			assertEquals(3, simpleStorage.insert(3, transaction));
			assertEquals(8, simpleStorage.getAmount());
		}

		assertEquals(0, simpleStorage.getAmount());

		try (Transaction transaction = Transaction.openOuter()) {
			assertEquals(5, simpleStorage.insert(100, transaction));
			assertEquals(3, simpleStorage.insert(3, transaction));
			assertEquals(8, simpleStorage.getAmount());

			transaction.commit();
		}

		assertEquals(8, simpleStorage.getAmount());
	}

	@Test
	public void testItemEnergyStorage() {
		SingleVariantStorage<ItemVariant> slot = new SingleVariantStorage<>() {
			@Override
			protected ItemVariant getBlankVariant() {
				return ItemVariant.blank();
			}

			@Override
			protected long getCapacity(ItemVariant variant) {
				return 2;
			}
		};
		ContainerItemContext ctx = ContainerItemContext.ofSingleSlot(slot);

		// Set starting items (diamonds here)
		slot.variant = ItemVariant.of(Items.DIAMOND);
		slot.amount = 2;

		// Create the energy storage
		EnergyStorage energyStorage = SimpleEnergyItem.createStorage(ctx, 60, 50, 30);

		try (Transaction transaction = Transaction.openOuter()) {
			assertTrue(energyStorage.supportsInsertion());
			assertTrue(energyStorage.supportsExtraction());
			// Insertion of 200 should only insert 100 (50 per item).
			assertEquals(100, energyStorage.insert(200, transaction));
			assertEquals(50, getStoredEnergyUnchecked(slot.variant));
			// Insertion of 200 should only insert 20 (10 per item) due to the capacity.
			assertEquals(20, energyStorage.insert(200, transaction));
			assertEquals(60, getStoredEnergyUnchecked(slot.variant));
			// Extraction of 30 should extract 30 (15 per item).
			assertEquals(30, energyStorage.extract(30, transaction));
			assertEquals(45, getStoredEnergyUnchecked(slot.variant));
			// Check amount and capacity.
			assertEquals(90, energyStorage.getAmount());
			assertEquals(120, energyStorage.getCapacity());

			// Now check that everything returns 0 if we change the item in the slot.
			slot.variant = ItemVariant.blank();
			ensureEmpty(energyStorage, transaction);
		}
	}

	private static void ensureEmpty(EnergyStorage energyStorage, TransactionContext transaction) {
		assertFalse(energyStorage.supportsInsertion());
		assertFalse(energyStorage.supportsExtraction());
		assertEquals(0, energyStorage.insert(Long.MAX_VALUE, transaction));
		assertEquals(0, energyStorage.extract(Long.MAX_VALUE, transaction));
		assertEquals(0, energyStorage.getAmount());
		assertEquals(0, energyStorage.getCapacity());
	}

	@Test
	public void testBatteryItem() {
		ItemStack stack = new ItemStack(item);

		assertEquals(0, item.getStoredEnergy(stack));
		assertTrue(EnergyStorageUtil.isEnergyStorage(stack));

		item.setStoredEnergy(stack, 10);
		assertEquals(10, item.getStoredEnergy(stack));
	}

	@Test
	public void testGetStackEnergy() {
		ItemStack stack = new ItemStack(item);
		ItemVariant variant = ItemVariant.of(stack);

		assertEquals(0L, SimpleEnergyItem.getStoredEnergyUnchecked(stack));
		assertEquals(0L, SimpleEnergyItem.getStoredEnergyUnchecked(variant));
		assertNull(stack.get(EnergyStorage.ENERGY_COMPONENT));
		assertNull(variant.getComponents().get(EnergyStorage.ENERGY_COMPONENT));

		SimpleEnergyItem.setStoredEnergyUnchecked(stack, 1000L);
		variant = ItemVariant.of(stack);

		assertEquals(1000L, SimpleEnergyItem.getStoredEnergyUnchecked(stack));
		assertEquals(1000L, SimpleEnergyItem.getStoredEnergyUnchecked(variant));
		assertNotNull(stack.get(EnergyStorage.ENERGY_COMPONENT));
		assertNotNull(variant.getComponents().get(EnergyStorage.ENERGY_COMPONENT));

		SimpleEnergyItem.setStoredEnergyUnchecked(stack, 0L);
		variant = ItemVariant.of(stack);

		assertEquals(0L, SimpleEnergyItem.getStoredEnergyUnchecked(stack));
		assertEquals(0L, SimpleEnergyItem.getStoredEnergyUnchecked(variant));
		assertNull(stack.get(EnergyStorage.ENERGY_COMPONENT));
		assertNull(variant.getComponents().get(EnergyStorage.ENERGY_COMPONENT));
	}
}
```

### src/test/java/team/reborn/energy/test/TestBatteryItem.java

```java
package team.reborn.energy.test;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import team.reborn.energy.api.base.SimpleEnergyItem;

public class TestBatteryItem extends Item implements SimpleEnergyItem {
	private final long capacity, maxInput, maxOutput;

	public TestBatteryItem(long capacity, long maxInput, long maxOutput) {
		super(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("energy_test", "battery"))));
		this.capacity = capacity;
		this.maxInput = maxInput;
		this.maxOutput = maxOutput;
	}

	@Override
	public long getEnergyCapacity(ItemStack stack) {
		return capacity;
	}

	@Override
	public long getEnergyMaxInput(ItemStack stack) {
		return maxInput;
	}

	@Override
	public long getEnergyMaxOutput(ItemStack stack) {
		return maxOutput;
	}
}
```

### src/test/resources/fabric.mod.json

```json
{
  "schemaVersion": 1,
  "id": "team_reborn_energy_testmod",
  "version": "1.0.0",
  "name": "Energy testmod",
  "description": "The testmod of the Energy API created and used by Team Reborn",
  "authors": [
    "Team Reborn",
    "modmuss50"
  ],
  "contact": {
    "homepage": "https://github.com/TechReborn/Energy",
    "sources": "https://github.com/TechReborn/Energy",
    "issues": "https://github.com/TechReborn/Energy/issues"
  },
  "license": "MIT",
  "environment": "*",
  "icon": "assets/team_reborn_energy/icon.png",
  "depends": {
    "team_reborn_energy": "*"
  },
  "custom": {
    "modmenu:api": true
  },
  "entrypoints": {
    "main": [
      "team.reborn.energy.test.EnergyTests"
    ]
  }
}
```

## Skipped files

- `gradle/wrapper/gradle-wrapper.jar` — binary file
- `src/main/resources/assets/team_reborn_energy/icon.png` — binary file
