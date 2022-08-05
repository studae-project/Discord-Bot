apt-get install -y libcurl4-openssl-dev

if [[ "$(command -v git)" == "" ]]; then
  echo "Package git not found! installing git..."
  apt install git -y > /dev/null
fi

if [[ "$(command -v ar)" == "" ]]; then
  echo "Package binutils not found! installing binutils..."
  apt install binutils -y > /dev/null
fi

DIR=$(pwd)
export PREFIX=/usr/local

git clone https://github.com/Cogmasters/concord
cd concord

make all install

cd $DIR
