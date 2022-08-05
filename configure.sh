SUDO_PATH=$(command -v sudo)

sudo() {
  if [[ "$SUDO_PATH" == "" || "$TERMUX_VERSION" != "" ]]; then
    eval "$@"
  else
    eval "$SUDO_PATH $@"
  fi
}

if [[ "$(command -v git)" == "" ]]; then
  echo "Package git not found! installing git..."
  sudo apt install git -y > /dev/null
fi

if [[ "$(command -v ar)" == "" ]]; then
  echo "Package binutils not found! installing binutils..."
  sudo apt install binutils -y > /dev/null
fi

DIR=$(pwd)
export PREFIX=$DIR

git clone https://github.com/Cogmasters/concord
cd concord

make

make install

cd $DIR

make clean all
