gradle build
sudo docker build -t apiportaldemo .
sudo docker run -d --network=host apiportaldemo
