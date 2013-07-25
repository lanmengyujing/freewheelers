#!/usr/bin/env bash
#
# Copyright (C) 2010-2011 the original author or authors.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#         http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

# Save current directory
OLDDIR=`pwd`

# resolve links - $0 may be a softlink
PRG="$0"

while [ -h "$PRG" ] ; do
  ls=`ls -ld "$PRG"`
  link=`expr "$ls" : '.*-> \(.*\)$'`
  if expr "$link" : '/.*' > /dev/null; then
    PRG="$link"
  else
    PRG=`dirname "$PRG"`/"$link"
  fi
done

# Set the current directory to the installation directory
INSTALLDIR=`dirname $PRG`
cd "$INSTALLDIR"

# Use JAVA_HOME if it is set
if [ -z $JAVA_HOME ]; then
 JAVA_CMD=java
else
 JAVA_CMD=$JAVA_HOME/bin/java
fi

# Detect the width of the console
CONSOLE_WIDTH=200

$JAVA_CMD -cp bin/flyway-commandline-2.1.1.jar:bin/flyway-core-2.1.1.jar:bin/spring-jdbc-3.1.0.RELEASE.jar:bin/commons-logging-1.1.1.jar:bin/spring-beans-3.1.0.RELEASE.jar:bin/spring-core-3.1.0.RELEASE.jar:bin/spring-context-3.1.0.RELEASE.jar:bin/aopalliance-1.0.jar:bin/spring-tx-3.1.0.RELEASE.jar:bin/mysql-connector-java-5.1.14.jar com.googlecode.flyway.commandline.Main $@ -consoleWidth=$CONSOLE_WIDTH

# Save the exit code
JAVA_EXIT_CODE=$?

# Restore current directory
cd "$OLDDIR"

# Exit using the same code returned from Java
exit $JAVA_EXIT_CODE
