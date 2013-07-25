#!/bin/bash

set -e

USER=$1
HOST=$2

if [ $# -ne 2 ]; then
  echo usage: scripts/install.sh USER HOST
  exit -1
fi;

if [ ! -e "dist/freewheelers.zip" ]; then
  echo "cannot find dist/freewheelers.zip to deploy"
  exit -1
fi;

ssh ${USER}@${HOST} /bin/bash << EOF
cd /tmp
echo "-----------------------tmp size"
du -sh
echo "-----------------------freewheelers size"
du -sh freewheelers_backup
echo "-----------------------rm back up"
rm -rf freewheelers_backup
 
BACK_UP=$(ls -r | grep '2013-[0-9]*-[0-9]*-[a-zA-Z0-9]*' | head -n1)
echo "moving $BACK_UP for back up"
mv -f $BACK_UP freewheelers_backup
ls | grep '2013-[0-9]*-[0-9]*-[a-zA-Z0-9]*' | xargs rm -rf
EOF

scp dist/freewheelers.zip ${USER}@${HOST}:/tmp

ssh ${USER}@${HOST} /bin/bash << EOF

TIMESTAMP=\$(date +"%Y-%m-%d-%HH%MM%Ss")
mkdir ~/itemImages
mkdir -p /tmp/\$TIMESTAMP
mv /tmp/freewheelers.zip /tmp/\$TIMESTAMP
cd /tmp/\$TIMESTAMP 
unzip freewheelers.zip
ln -s ~/itemImages src/main/webapp/images/items
sh stop-server.sh
sh db/create.sh
sh ./flyway/flyway.sh migrate
sh db/insert.sh
nohup sh start-server.sh > server.out 2> server.err < /dev/null
EOF

