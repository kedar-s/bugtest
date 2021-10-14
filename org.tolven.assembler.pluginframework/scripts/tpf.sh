#!/bin/bash

. ./tpfenv.sh
java -Djavax.net.ssl.keyStore= -Djavax.net.ssl.keyStorePassword= -Djavax.net.ssl.trustStore= -Djavax.net.ssl.trustStorePassword= -Dsun.lang.ClassLoader.allowArraySyntax=true -jar ../pluginLib/tpf-boot.jar $*
