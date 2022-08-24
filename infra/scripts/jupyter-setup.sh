python3.9 -m venv venv
source venv/bin/activate

sudo apt install mysql-server -y
sudo apt install libmysqlclient-dev -y
sudo apt install build-essential -y
sudo apt-get install libxml2-dev libxslt1-dev -y
pip install wheel
pip install -r requirements.txt
pip install scikit-surprise
jupyter notebook --generate-config

# THIS IS NOT THE CURRENT PASSWORD
# WON'T WORK EVEN IF YOU TRY 😛
# ipython
# from notebook.auth import passwd; passwd()
# makurlypassword
# 'argon2:$argon2id$v=19$m=10240,t=10,p=8$Ei828NtiK8J4WJRsee0PAQ$q7xalVCdxUv+1GNLreQka35ao817f+tOlB89Kn2C7ns'
