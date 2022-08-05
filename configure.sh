if [[ "$(command -v git)" == "" ]]; then
  echo "Package git not found! installing git..."
  sudo apt install git -y > /dev/null
fi

if [[ "$(command -v ar)" == "" ]]; then
  echo "Package binutils not found! installing binutils..."
  sudo apt install binutils -y > /dev/null
fi

sudo apt install curl libcurl*

DIR=$(pwd)
export PREFIX=$DIR

git clone https://github.com/Cogmasters/concord
cd concord

make

make install

cd $DIR

make clean all
