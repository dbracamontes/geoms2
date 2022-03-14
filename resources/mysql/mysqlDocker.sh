docker run -d --name='mysql' --env="MYSQL_ROOT_PASSWORD=daniel.51" --publish 6603:3306 --volume=D:\mysql mysql:5.7


docker-machine env
192.168.99.100


docker run -d --name='geoDb' --env="MYSQL_ROOT_PASSWORD=daniel.51" --publish 6603:3306 -v :/var/lib/mysql mysql:5.7
docker run -it --name='geoDb' --env="MYSQL_ROOT_PASSWORD=daniel.51" --publish 6603:3306 -v geoMysqlData:/var/lib/mysql mysql:5.7


docker run -it --name='geoDb' --env="MYSQL_ROOT_PASSWORD=daniel.51" --publish 6603:3306 -v /volume/mysql/conf:/etc/mysql/conf.d -v /volume/mysql/data:/var/lib/mysql mysql:5.7

docker run -it --name='geoDb' --env="MYSQL_ROOT_PASSWORD=daniel.51" --publish 6603:3306 -v /volume/mysql/conf:/etc/mysql/conf.d mysql:5.7


docker run -d --name='geoDb' --env="MYSQL_ROOT_PASSWORD=daniel.51" --publish 6603:3306 -volumes-from geodb mysql:5.7

docker exec -ti ad7f383189f6 mysql -uroot -p

 ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'daniel.51';
 
 
 
 
 
 
 docker run -d --name geo-mongo \
    -e MONGO_INITDB_ROOT_USERNAME=root \
    -e MONGO_INITDB_ROOT_PASSWORD=daniel.51 \
	-v geoMongoData:/data/db \
	--publish 27017:27017 \
    mongo:latest