#/bin/bash

echo "-----------------------------------------------------------------------------------"
echo "Building install package in $1/finance-batches"
echo "-----------------------------------------------------------------------------------"

DIR_BASE=$1
DIR_TARGET=${DIR_BASE}/target
DIR_PACKAGE=${DIR_TARGET}/finance-batches

if [ ! -d ${DIR_TARGET} ]; then
	echo "There is no TARGET dir"
	exit -1
fi

if [ ! -d ${DIR_TARGET}/dependency-jars ]; then
	echo "There is no TARGET/dependency-jars dir"
	exit -2
fi

if [ -d ${DIR_PACKAGE} ]; then
   rm -rf ${DIR_PACKAGE}
fi

mkdir -p ${DIR_PACKAGE}
mkdir -p ${DIR_PACKAGE}/bin
mkdir -p ${DIR_PACKAGE}/lib
mkdir -p ${DIR_PACKAGE}/logs

cp ${DIR_BASE}/tools/quotes.sh ${DIR_PACKAGE}/bin
cp ${DIR_TARGET}/dependency-jars/* ${DIR_PACKAGE}/lib/
cp ${DIR_TARGET}/*.jar ${DIR_PACKAGE}/lib

chmod +x ${DIR_PACKAGE}/bin/quotes.sh

cd ${DIR_TARGET}
zip -r finance-batches finance-batches/*
tar cvzf finance-batches.tar.gz finance-batches/*
