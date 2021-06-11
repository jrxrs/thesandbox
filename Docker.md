# Docker

Don't forget that in Docker, Containers are just running versions of Images.

## Simple Commands

```bash
docker pull <image_name>
```
Pull an image down from the container registry. **Note**: images pulled from a container registry are stored under ```/var/lib/docker/image/overlay2/imagedb/content/sha256```, the SHAs you see in this folder are the ones displayed in the output of ```docker images``` (see below). Also see ```docker inspect```.

```bash
docker images
```
List all of your local images.

```bash
docker inspect <image_name>
```
This command prints the JSON file which describes a container image. This is a lightweight version of cat'ing the files under ```/var/lib/docker/image/overlay2/imagedb/content/sha256``` mentioned above. This command will also show you the IP Address of the container.

```bash
docker build --tag <tag_name>
```
Build a docker image from a Dockerfile and annotate it with the specified tag name.

```bash
docker history <tag_name>
```
Show what happened to your image while it was being built (including information about the size of the image at each layer/stage).

```bash
docker run -it <image_name> /bin/bash
```
This runs a container and attaches to stdin, /bin/bash is the processed which is opened and ```-it``` is the interactive flag.
**Note**: to detach from a container without stopping it press: CTRL+P followed by CTRL+Q, then can then use the ```attach``` command to re-attach later.

```bash
docker ps
```
Lists the currently running containers. Add ```-a``` to list containers which have been stopped.

```bash
docker start <container_identifier>
```
If you wanted to start one of the stopped containers list above then use the command above.

```bash
docker attach <container_identifier>
```
The ```start``` command will restart your container but it will not attach to it automatically like ```run -it``` did, so use ```attach``` instead. Note that you an use either the container ID or the container name as an argument here.

```bash
docker stop <container_identifier>
```
Stop a running container.

```bash
docker rm <container_identifier>
```
Clean up any stopped containers by removing them.

```bash
docker container prune -f
```
Purge all stopped containers.

```bash
docker rmi <image_name>
```
Delete a docker image that you've built locally or pulled from a container registry.

```bash
docker image prune -af
```
Purge all docker images that you've previously deleted.

```bash
docker volume ls
```
List all of your persistent volumes.

```bash
docker volume inspect <volume_name>
```
View the location of a volume you have added to a container (note: Docker manages these volumes for you).

```bash
docker tag <original_repository>:<tag> <new_repository>:<new_tag>
```
This applies a new tag to an existing image, note that the repository names can be the same if you like. The resulting image will have the same IMAGE ID as the original image just a different set of tags. Note that you can just include the desired tag name in the build command e.g. ```docker build -t tag_demo:v2 .```

```bash
docker push ...
```
Used to push an image to a container registry.

```bash
docker info
```
This will output system-wide information about Docker.

## ```run```

* ```-it``` - iteractive mode with tty mode (making it act like a standard terminal)
* ```-d``` - detach - run the container in the background but print its' ID
* ```rm``` - remove - automatically delete a container as soon as it is stopped
* ```--name``` - name - give the container a name (if you don't specify one then Docker will give the container a random name for you)
* ```-p 8080:8080``` - port - configure port forwarding, this basically says map port 8080 from the docker host through to port 8080 of this container, i.e. ```host_port:container_port```
* ```-P``` - Publish all exposed ports to random ports
* ```-v``` - volume - mount a volume to your contianer, e.g. ```-v ${PWD}:/myvol``` would share a folder on your host machine with the container
* ```--mount type=volume,src="logs",dst=/logs``` - mount a volume to your container
* ```--mount type=bind,src="/var/demo/logs",dst=/logs``` - mount a bind volume to your container
* ```--mount type=tmpfs,dst=/logs``` - mount a tmpfs volume to your container (everything is in-memory here so there is no source required)
* ```-c "commands"``` - a list of commands to be executed inside your container
* ```-w <dir>``` - set the working directory, the allows you to omit leading paths from your volume, e.g. 
  * ```docker run --rm -v ${PWD}:/files klutchell/rar a /files/myrar.rar /files/myfile.txt``` can become
  * ```docker run --rm -v ${PWD}:/files -w files klutchell/rar a myrar.rar myfile.txt```
* ```--network=?``` - specify the network you would like your container to join

## Running Commands without elevating to root permissions

1. Verify the docker group exists by searching for it in the groups file: ```grep docker /etc/group````
2. If you don't see a line beginning with "docker:", you will need to add the group yourself by entering: ```sudo groupadd docker```
3. Add your user to the docker group: ```sudo gpasswd -a $USER docker```
4. You can login again to have your groups updated by entering: ```newgrp docker```
5. Verify that your user can successfully issue Docker commands by entering: ```docker info```
*Note*: if you don't see the system-wide Docker information, you may need to restart the Docker daemon by entering ```sudo systemctl restart docker```

## Docker Namespaces and Control Groups

* The pid namespace: Process isolation
* The net namespace: Managing network interfaces
* The ipc namespace: Managing acces to IPC resources (IPC: InterProcess Communication)
* The mnt namespace: Managing filesystem mount points
* The uts namespace: Isolating kernal and version identifiers (UTS: Unit Timesharing System)

These namespaces are how Docker archives high levels of process isolation. It also uses Control Groups to limit how much of a system a particular process can use, this prevents one process hogging everything, the groups are:

* Resource limiting: groups can be set to not exceed a configured memory limit
* Prioritisation: some groups may get a larger share of CPU utilisation or disk I/O throughput
* Accounting: measures a group's resource usage
* Control: freezing groups of processes

## UnionFS

The Union Filesystem is how Docker keeps images slime and lightweight, each image starts with a base image into which changes are merged, this prevents duplication etc. 
UnionFS also allows Read/Write control so that braches can be read-only or read-write. 

## Installation

I used the following guide to install Docker on CentOS: https://docs.docker.com/engine/install/centos/

## The Dockerfile

Reference: https://docs.docker.com/engine/reference/builder/

## Dockerfile Alternatives

Instead of using the Dockerfile to create an image it's also possible to use the ```commit``` command to modify an exiting image and do something like install python. An example of doing this would be running the following commands (note that you must be the root user):

```bash
sudo -i
docker run -it ubuntu /bin/bash
which python
apt update
apt full-upgrade
apt install python
which python
python --version
exit
# You should now be back on your VM, still as root
docker ps -a
docker commit --change='CMD ["python", "-c", "import this"]' <CONTAINER_ID> <TAG_NAME>
```

Note the ```--change``` argument passed to the ```commit``` command, this alters the default command that is run when the container starts (originally it was ```/bin/bash``` for ubuntu), it is also possble to change other properties of the image at this time.

## Port Mapping

Ports can be exposed in the Dockerfile used to build your image using the ```EXPOSE``` command, e.g. ```EXPOSE 8080```.
When running a container from an image which has a port exposed you can use the ```-P``` flag to ```docker run``` in order to bind all exposed ports to a random port on your host, executing ```docker ps -a``` will tell you which port was chosen (if you used ```-P / --publish-all```), see ```-p``` above if you want to specifiy exactly which port to bind on the localhost.

## Networking

Docker has 3 default networks which can be viewed with the ```docker network ls``` command, e.g.

```bash
[root@localhost ~]# docker network ls
NETWORK ID     NAME      DRIVER    SCOPE
7fb140cb2444   bridge    bridge    local
4dda932d2c4b   host      host      local
f8347dc1cb1a   none      null      local
```

### The bridge network
If you start a container but do not specify a network then the ```bridge``` network will be used. While using the ```bridge``` network all containers can see each other as well as reach out to the internet. Customising the default networks isn't possible but you can create you own networks if you require customisation. 

### The host network
The host network runs with no isolation between the localhost and the containers, therefore if you start a container which exposes port 8080 then it will bind to port 8080 on your localhost. In order to specify the network use the ```--network=host``` flag to the ```docker run``` command. If you ```docker inspect``` a container which was started on the ```host``` network then you will see that it does not have an IP Address.

### The none network
Using the none network will mean that your container doesn't have any network connectivty at all (just the loopback interface), neither to other containers or the outside world.

## Volumes

Volumes are one of the three mechanisms that Docker provides to allow for persistent stoage (the others are Bind Mounts and In-memory Storage (tmpfs)).

Volumes are the preferred way to persist data in Docker containers and services. Some use cases for volumes include:
* Sharing data among multiple running containers. If you don't explicitly create it, a volume is created the first time it is mounted into a container. When that container stops or is removed, the volume still exists. Multiple containers can mount the same volume simultaneously, either read-write or read-only. Volumes are only removed when you explicitly remove them.
* When the Docker host is not guaranteed to have a given directory or file structure. Volumes help you decouple the configuration of the Docker host from the container runtime.
* When you want to store your container's data on a remote host or a cloud provider, rather than locally.
* When you need to be able to back up, restore, or migrate data from one Docker host to another, volumes are a better choice. You can stop containers using the volume, then back up the volume's directory (such as ```/var/lib/docker/volumes/<volume-name>```).

To mount a volume in your container use the ```--mount``` option to run (see ```run``` above).

You can view all your volumes using the ```docker volume ls``` command (see above).

### Bind Mounts
In general, you should use volumes where possible. Bind mounts are appropriate for the following types of use case:
* Sharing configuration files from the host machine to containers. This is how Docker provides DNS resolution to containers by default, by mounting ```/etc/resolv.conf``` from the host machine into each container.
* Sharing source code or build artifacts between a development environment on the Docker host and a container. For instance, you may mount a Maven ```target/``` directory into a container, and each time you build the Maven project on the Docker host, the container gets access to the rebuilt artifacts. If you use Docker for development this way, your production Dockerfile would copy the production-ready artifacts directly into the image, rather than relying on a bind mount.
* When the file or directory structure of the Docker host is guaranteed to be consistent with the bind mounts the containers require.

To mount a bind volume in your container use the ```--mount``` option to run (see ```run``` above).

### tmpfs Mounts
```tmpfs``` mounts are best used for cases when you do not want the data to persist either on the host machine or within the container. This may be for security reasons or to protect the performance of the container when your application needs to write a large volume of non-persistent state data. This type of volume doesn't even persist between invocations of the same container, e.g. run a new container, detach and stop it, then restart and reattach - in this case the tmpfs volume will have been cleaned up when you stopped so you'll start from scratch again when you restart. This is great for sensitive things like passwords or access tokens etc.

To mount a tmpfs volume in your container use the ```--mount``` option to run (see ```run``` above).

## Tagging

The ```latest``` is always implied if you don't specify a tag.

In Docker an image can have more than one tag, e.g. a version number and a version name like ubuntu does. 
Docker has a couple of type of tag, in the ```build``` command you can specify a ```-t <tag_name>``` to label your new image, if you don't specify a version then it will default to ```latest``` as above. You can then apply further tags to that image using the ```docker tag``` command (see above).

## Pushing an Image

You can push a docker image using the ```docker push``` command. By default you will push to Docker Hub (for which you'll need a account set up).
