# Docker

## Simple Commands

```bash
docker pull <image_name>
```
Pull an image down from the container registry.

```bash
docker images
```
List all of your local images.

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
Purge all the containers you've previously deleted.

```bash
docker rmi <image_name>
```
Delete a docker image that you've built locally or pulled from a container registry.

```bash
docker image prune -af
```
Purge all docker images that you've previously deleted.

## ```run```

* ```-it``` - iteractive mode
* ```-d``` - detach - run the container in the background but print its' ID
* ```rm``` - remove - automatically delete a container as soon as it is stopped
* ```--name``` - name - give the container a name (if you don't specify one then Docker will give the container a random name for you)
* ```-p 8080:8080``` - port - configure port forwarding, this basically says map port 8080 from the docker host through to port 8080 of this container
* ```-v``` - volume - mount a volume to your contianer, e.g. ```-v ${PWD}:/myvol``` would share a folder on your host machine with the container
* ```-c "commands"``` - a list of commands to be executed inside your container
* ```-w <dir>``` - set the working directory, the allows you to omit leading paths from your volume, e.g. 
  * ```docker run --rm -v ${PWD}:/files klutchell/rar a /files/myrar.rar /files/myfile.txt``` can become
  * ```docker run --rm -v ${PWD}:/files -w files klutchell/rar a myrar.rar myfile.txt```

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
