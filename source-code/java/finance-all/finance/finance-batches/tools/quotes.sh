#!/bin/bash

BASEDIR="$( cd "$( dirname "$0" )" && pwd )"
HOMEDIR=${BASEDIR}/..
LIBDIR=${HOMEDIR}/lib

THE_CLASSPATH=
for i in `ls ${LIBDIR}/*.jar`
do
  THE_CLASSPATH=${THE_CLASSPATH}:${i}
done

java -cp ".:${THE_CLASSPATH}" br.com.singularideas.labs.finance.batches.QuotesBatch $@